package util;

public class IOMapping {
	
	// Inputs
	private static String[] capsCardioInputs = {"J2c","J2e","J3","J8a","J8b"};
	
	private static String[] scaleDRSInputs = {"E1a","E1b","E1c","E1d","E1e","E1f","E1g"};
	
	private static String[] capsFallsInputs = {"J1"};
	
	private static String[] cAddInputs = {"J8a","J8b"};
	
	private static String[] sCommInputs = {"D1","D2"};
	
	private static String[] capsDehydrationInputs = {"C3a","C3b","C3c","C4","J2c","J2k","J2l","J2n","J2q","K2a","K2b","K2c"};
	
	private static String[] capsPainInputs = {"J5a","J5b"};
	
	private static String[] capsDeliriumInputs = {"C3a","C3b","C3c","C4"};
	
	private static String[] scaleABSInputs = {"E3b","E3c","E3d","E3e"};
	
	private static String[] scaleClinicianRatedMoodInputs = {"E1e","E1f","E1i","E1k"};
	
	private static String[] scaleSelfReportMoodInputs = {"E2a","E2b","E2c"};
	
	private static String[] scaleADLHierarchyInputs = {"G2b","G2f","G2h","G2j"};
	
	private static String[] scaleIADLCapacityHierarchyInputs = {"G1ab","G1bb","G1cb","G1db", "G1gb"};
	
	private static String[] scaleFallsInputs = {"J1"};
	
	private static String[] scalePainInputs = {"J5a","J5b"};
	
	private static String[] scaleCPSInputs = {"C1","C2a","D1","G2a"};
	
	private static String[] capsPressureUlcerInputs = {"G2g","G2i","H1","H2","L1","L2","L3","N2k","N2m"};
	
	private static String[] scalePressureUlcerRatingInputs = {"G2e","G2i","H3","J3","J5a","K2a","L2"};
	
	private static String[] capsFeedingTubeInputs = {"iC1","iK3"};
	
	private static String[] capsBehaviorInputs = {"E3a","E3b","E3c","E3d","E3e","E3f"};
	
	private static String[] scaleDeafblindSeverityIndexInputs = {"D3","D4"};
	
	private static String[] scaleCompositeMoodInputs = {"E1e","E1f","E1i","E1k","E2a","E2b","E2c"};
	
	private static String[] capsPhysicalActivityPromotionInputs = {"G1fa","G2f","G6","G7"};
	
	private static String[] scaleCognitivePerformanceV2Inputs = {"C1","C2a","D1","G1cb","G1db","G2e"};
	
	private static String[] capsCommunicationInputs = {"C1","D1","D2"};
	
	private static String[] scaleADLSFInputs = {"G2b","G2f","G2h","G2j"};
	
	private static String[] scaleADLLFInputs = {"G2b", "G2c","G2d","G2f","G2h","G2i","G2j"};
	
	private static String[] scalePain1Inputs = {"J5a","J5b"};
	
	private static String[] scaleBMIInputs = {"K1a","K1b"};
	
	private static String[] scaleFunctionalHierarchyInputs = {"G2b","G2f","G2h","G2j","G1ab","G1bb","G1cb","G1db", "G1gb"};
	
	private static String[] capsNutritionInputs = {"K1a","K1b","J6c"};
	
	private static String[] capsMoodInputs = {"E1a","E1b","E1c","E1d","E1e","E1f","E1g"};
	
	
	// Outputs
	
	private static String capsCardioOutput = "Cardio-respiratory CAP";
	
	private static String scaleDRSOutput = "sDRS Depression";
	
	private static String cAddOutput = "Smoking and Drinking CAP";
	
	private static String capsFallsOutput = "Falls CAP";
	
	private static String sCommOutput = "Communication scale";
	
	private static String capsDehydrationOutput = "Dehydration CAP";
	
	private static String capsPainOutput = "Pain CAP";
	
	private static String capsDeliriumOutput = "Delirium CAP";
	
	private static String scaleABSOutput = "Aggressive Behaviour Scale";
	
	private static String scaleClinicianRatedMoodOutput = "Clinician Rated Mood Scale";
	
	private static String scaleSelfReportMoodOutput = "Self Report Mood Scale";
	
	private static String scaleADLHierarchyOutput = "sADL hierarchy scale";
	
	private static String scaleIADLCapacityHierarchyOutput = "IADL Capacity Hierarchy Scale";
	
	private static String scaleFallsOutput = "xFalls";
	
	private static String scalePainOutput = "sPAIN scale";
	
	private static String scaleCPSOutput = "sCPS scale";
	
	private static String capsPressureUlcerOutput = "Pressure Ulcer CAP";
	
	private static String scalePressureUlcerRatingOutput = "sPUR Scale";
	
	private static String capsFeedingTubeOutput = "Feeding Tube CAP";
	
	private static String capsBehaviorOutput = "Behavior CAP";
	
	private static String scaleDeafblindSeverityIndexOutput = "Deafblind Severity Index";
	
	private static String scaleCompositeMoodOutput = "Composite Mood Scale";
	
	private static String capsPhysicalActivityPromotionOutput = "Physical Activity Promotion CAP";
	
	private static String scaleCognitivePerformanceV2Output = "sCPS2 scale";
	
	private static String capsCommunicationOutput = "Communication CAP";
	
	private static String scaleADLSFOutput = "sADLSF";
	
	private static String scaleADLLFOutput = "sADLLF";
	
	private static String scalePain1Output = "SPAIN1 scale";
	
	private static String scaleBMIOutput = "Body Mass Index";
	
	private static String scaleFunctionalHierarchyOutput = "Functional Hierarchy";
	
	private static String capsNutritionOutput = "Nutrition CAP";
	
	private static String capsMoodOutput = "Mood CAP";
	
	// Input and output Mapping Getters
	
	public static String[] getCapsCardioInputs(){return capsCardioInputs;}
	
	public static String getCapsCardioOutput(){return capsCardioOutput;}
	
	public static String[] getScaleDRSInputs(){return scaleDRSInputs;}
	
	public static String getScaleDRSOutput(){return scaleDRSOutput;}
	
	public static String[] getCAddInputs(){return cAddInputs;}
	
	public static String getCAddOutput(){return cAddOutput;}
	
	public static String[] getCapsFallsInputs(){return capsFallsInputs;}
	
	public static String getCapsFallsOutput(){return capsFallsOutput;}
	
	public static String[] getSCommInputs(){return sCommInputs;}
	
	public static String getSCommOutput(){return sCommOutput;}
	
	public static String[] getCapsDehydrationInputs(){return capsDehydrationInputs;}
	
	public static String getCapsDehydrationOutput(){return capsDehydrationOutput;}
	
	public static String[] getCapsPainInputs(){return capsPainInputs;}
	
	public static String getCapsPainOutput(){return capsPainOutput;}
	
	public static String[] getDeliriumInputs(){return capsDeliriumInputs;}
	
	public static String getDeliriumOutput(){return capsDeliriumOutput;}
	
	public static String[] getABSInputs(){return scaleABSInputs;}
	
	public static String getABSOutput(){return scaleABSOutput;}
	
	public static String[] getClinicianRatedMoodInputs(){return scaleClinicianRatedMoodInputs;}
	
	public static String getClinicianRatedMoodOutput(){return scaleClinicianRatedMoodOutput;}
	
	public static String[] getSelfReportMoodInputs(){return scaleSelfReportMoodInputs;}
	
	public static String getSelfReportMoodOutput(){return scaleSelfReportMoodOutput;}
	
	public static String[] getADLHierarchyInputs(){return scaleADLHierarchyInputs;}
	
	public static String getADLHierarchyOutput(){return scaleADLHierarchyOutput;}
	
	public static String[] getIADLCapacityHierarchyInputs(){return scaleIADLCapacityHierarchyInputs;}
	
	public static String getIADLCapacityHierarchyOutput(){return scaleIADLCapacityHierarchyOutput;}
	
	public static String[] getScaleFallsInputs(){return scaleFallsInputs;}
	
	public static String getScaleFallsOutput(){return scaleFallsOutput;}
	
	public static String[] getScalePainInputs(){return scalePainInputs;}
	
	public static String getScalePainOutput(){return scalePainOutput;}
	
	public static String[] getScaleCPSInputs(){return scaleCPSInputs;}
	
	public static String getScaleCPSOutput(){return scaleCPSOutput;}
	
	public static String[] getPressureUlcerInputs(){return capsPressureUlcerInputs;}
	
	public static String getPressureUlcerOutput(){return capsPressureUlcerOutput;}
	
	public static String[] getPressureUlcerRatingScaleInputs(){return scalePressureUlcerRatingInputs;}
	
	public static String getPressureUlcerRatingScaleOutput(){return scalePressureUlcerRatingOutput;}
	
	public static String[] getFeedingTubeInputs(){return capsFeedingTubeInputs;}
	
	public static String getFeedingTubeOutput(){return capsFeedingTubeOutput;}
	
	public static String[] getCapsBehaviorInputs(){return capsBehaviorInputs;}
	
	public static String getCapsBehaviorOutput(){return capsBehaviorOutput;}
	
	public static String[] getScaleDeafblindSeverityIndexInputs(){return scaleDeafblindSeverityIndexInputs;}
	
	public static String getScaleDeafblindSeverityIndexOutput(){return scaleDeafblindSeverityIndexOutput;}
	
	public static String[] getScaleCompositeMoodInputs(){return scaleCompositeMoodInputs;}
	
	public static String getScaleCompositeMoodOutput(){return scaleCompositeMoodOutput;}
	
	public static String[] getPhysicalActivityPromotionInputs(){return capsPhysicalActivityPromotionInputs;}
	
	public static String getPhysicalActivityPromotionOutput(){return capsPhysicalActivityPromotionOutput;}
	
	public static String[] getCognitivePerformanceV2Inputs(){return scaleCognitivePerformanceV2Inputs;}
	
	public static String getCognitivePerformanceV2Output(){return scaleCognitivePerformanceV2Output;}
	
	public static String[] getCommunicationCapsInputs(){return capsCommunicationInputs;}
	
	public static String getCommunicationCapsOutput(){return capsCommunicationOutput;}
	
	public static String[] getADLSFScaleInputs(){return scaleADLSFInputs;}
	
	public static String getADLSFScaleOutput(){return scaleADLSFOutput;}
	
	public static String[] getADLLFScaleInputs(){return scaleADLLFInputs;}
	
	public static String getADLLFScaleOutput(){return scaleADLLFOutput;}
	
	public static String[] getPain1ScaleInputs(){return scalePain1Inputs;}
	
	public static String getPain1ScaleOutput(){return scalePain1Output;}
	
	public static String[] getBMIScaleInputs(){return scaleBMIInputs;}
	
	public static String getBMIScaleOutput(){return scaleBMIOutput;}
	
	public static String[] getFunctionalHierarchyScaleInputs(){return scaleFunctionalHierarchyInputs;}
	
	public static String getFunctionalHierarchyScaleOutput(){return scaleFunctionalHierarchyOutput;}
	
	public static String[] getNutritionCapInputs(){return capsNutritionInputs;}
	
	public static String getNutritionCapOutput(){return capsNutritionOutput;}
	
	public static String[] getMoodCapInputs(){return capsMoodInputs;}
	
	public static String getMoodCapOutput(){return capsMoodOutput;}

}
