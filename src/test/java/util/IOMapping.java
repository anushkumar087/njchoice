package util;

public class IOMapping {
	
	private static String[] capsCardioInputs = {"J2c","J2e","J3","J8a","J8b"};
	
	private static String capsCardioOutput = "Cardio-respiratory CAP";
	
	private static String[] scaleDRSInputs = {"E1a","E1b","E1c","E1d","E1e","E1f","E1g"};
	
	private static String scaleDRSOutput = "sDRS Depression";
	
	private static String[] cAddInputs = {"J8a","J8b"};
	
	private static String cAddOutput = "Smoking and Drinking CAP";
	
	public static String[] getCapsCardioInputs(){return capsCardioInputs;}
	
	public static String getCapsCardioOutput(){return capsCardioOutput;}
	
	public static String[] getScaleDRSInputs(){return scaleDRSInputs;}
	
	public static String getScaleDRSOutput(){return scaleDRSOutput;}
	
	public static String[] getCAddInputs(){return cAddInputs;}
	
	public static String getCAddOutput(){return cAddOutput;}

}
