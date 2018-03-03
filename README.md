# CopyMus webservices

## Deployment

- `./mvnw clean package`
- Create `~/copymus` folder and copy inside:
	- `target/copymus-webservices-X.X.X.jar`
	- `src/main/resources/application.properties`
	- `copymus.service`
- Check user and folders inside `copymus.service` and install as `systemd` service

## Security

All services are secured using an API key. Currently there is only one key registered, it can be changed in `application.properties`:
```
spring.security.user.password=API_KEY
```

## OpenAPI documentation

Swagger UI is available at `/swagger-ui/index.html`. The API key can be specified by pressing the `Authorize` button.
