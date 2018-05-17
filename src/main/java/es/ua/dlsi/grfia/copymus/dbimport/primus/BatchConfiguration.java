package es.ua.dlsi.grfia.copymus.dbimport.primus;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import es.ua.dlsi.grfia.copymus.models.Score;

@Configuration
@EnableBatchProcessing
@Profile("primusimport")
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	// tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Score> reader() {
        FlatFileItemReader<Score> reader = new FlatFileItemReader<Score>();
        reader.setResource(new ClassPathResource("primus.txt"));
        reader.setLineMapper(new DefaultLineMapper<Score>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "pdf" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Score>() {{
                setTargetType(Score.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ScoreItemProcessor processor() {
        return new ScoreItemProcessor();
    }

    @Bean
    public ScoreItemWriter writer() {
        return new ScoreItemWriter();
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importPrimusJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Score, Score> chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
}
