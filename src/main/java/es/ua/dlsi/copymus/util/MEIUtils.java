package es.ua.dlsi.copymus.util;

import java.io.File;
import java.util.List;

import meico.mei.Mei;
import meico.midi.Midi;
import meico.msm.Msm;

public class MEIUtils {

	// Code fragments taken from Meico app: https://github.com/cemfi/meico/blob/master/src/meico/app/Main.java
	public static String mei2midi(String meiPath) throws Exception {
		File meiFile;
		meiFile = new File(meiPath);
		meiFile = new File(meiFile.getCanonicalPath());
		
		// Reads MEI file
		Mei mei;
		mei = new Mei(meiFile);
		if (mei.isEmpty())
			throw new Exception("MEI file could not be loaded");
		
		// Converts MEI to intermediate MSM format
		List<Msm> msms = mei.exportMsm(720, false, false, true); // resolution=720, dontUseChannel10=false, ignoreExpansions=false, cleanup=true
		if (msms.isEmpty())
			throw new Exception("MSM data could not be created");

		// Expands score resolving repetitions, dacapi, etc.
		// Assume there is just 1 <body> in the MEI file
		msms.get(0).resolveSequencingMaps();
		
		// Transforms MSM to MIDI
		Midi midi = msms.get(0).exportMidi(); // default values, tempo=120, generateProgramChanges=true
		midi.writeMidi(); // Saves MIDI file to disk using the same name as the MEI file and changing the extension to .mid
		
		return midi.getFile().getAbsolutePath();
	}
}
