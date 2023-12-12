package util;

public class IOMapping {
	
	// Inputs
	private static String[] capsCardioInputs = {"J2c","J2e","J3","J8a","J8b"};
	
	private static String[] scaleDRSInputs = {"iE1a","iE1b","iE1c","iE1d","iE1e","iE1f","iE1g"};
	
	private static String[] capsFallsInputs = {"J1"};
	
	private static String[] cAddInputs = {"J8a","J8b"};
	
	private static String[] sCommInputs = {"iD1","iD2"};
	
	private static String[] capsDehydrationInputs = {"C3a","C3b","C3c","C4","J2c","J2k","J2l","J2n","J2q","K2a","K2b","K2c"};
	
	private static String[] capsPainInputs = {"iJ5a","iJ5b"};
	
	private static String[] capsDeliriumInputs = {"iC3a","iC3b","iC3c","iC4"};
	
	private static String[] scaleABSInputs = {"E3b","E3c","E3d","E3f"};
	
	private static String[] scaleClinicianRatedMoodInputs = {"iE1e","iE1f","iE1i","iE1k"}; // Not available
	
	private static String[] scaleSelfReportMoodInputs = {"E2a","E2b","E2c"};
	
	private static String[] scaleADLHierarchyInputs = {"G2b","G2f","G2h","G2j"};
	
	private static String[] scaleIADLCapacityHierarchyInputs = {"G1ab","G1bb","G1cb","G1db", "G1gb"};
	
	private static String[] scaleFallsInputs = {"J1"};
	
	private static String[] scalePainInputs = {"J5a","J5b"};
	
	private static String[] scaleCPSInputs = {"iC1","iC2a","iD1","iG2a"};
	
	private static String[] capsPressureUlcerInputs = {"G2g","G2i","H1","H2","L1","L2","L3","N2k","N2m"};
	
	private static String[] scalePressureUlcerRatingInputs = {"G2e","G2i","H3","J3","J5a","K2a","L2"};
	
	private static String[] capsFeedingTubeInputs = {"iC1","iK3"};
	
	private static String[] capsBehaviorInputs = {"E3a","E3b","E3c","E3d","E3f","E3e"};
	
	private static String[] scaleDeafblindSeverityIndexInputs = {"D3","D4"};
	
	private static String[] scaleCompositeMoodInputs = {"iE1e","iE1f","iE1i","iE1k","E2a","E2b","E2c"};
	
	private static String[] capsPhysicalActivityPromotionInputs = {"G1fa","G2f","G6","G7"};
	
	private static String[] scaleCognitivePerformanceV2Inputs = {"iC1","iC2a","iD1","iG1cb","iG1db","iG2e"};
	
	private static String[] capsCommunicationInputs = {"iC1","iD1","iD2"};
	
	private static String[] scaleADLSFInputs = {"G2b","G2f","G2h","G2j"};
	
	private static String[] scaleADLLFInputs = {"G2b", "G2c","G2d","G2f","G2h","G2i","G2j"};
	
	private static String[] scalePain1Inputs = {"J5a","J5b"};
	
	private static String[] scaleBMIInputs = {"K1a","K1b"};
	
	private static String[] scaleFunctionalHierarchyInputs = {"G2b","G2f","G2h","G2j","G1ab","G1bb","G1cb","G1db", "G1gb"};
	
	private static String[] capsNutritionInputs = {"J6c","K1a","K1b"};
	
	private static String[] capsMoodInputs = {"iE1a","iE1b","iE1c","iE1d","iE1e","iE1f","iE1g"};
	
	private static String[] capsInformalSupportInputs = {"B7a","F4","G1ab","G1bb","G1gb", "G1hb", "P1a1"};
	
	private static String[] capsUrinaryIncontinenceInputs = {"iC1","iG2e","iG6","iH1","iH2", "iI1a", "iI1q","iJ2l","iN2l"};
	
	private static String[] capsIADLImprovementInputs = {"iC1","iC2a","iD1","iG1ab","iG1bb","iG1gb","iG1hb","iG2a","iG2b","iG2f","iG2h","iG2j","iG5a","iG5b","iG6"};
	
	private static String[] capsBowelInputs = {"iB10","iC1","iC3a","iC3b","iC3c","iC4","iG2h","iG2i","iG2j","iG5b","iH1","iH3","iI1a","iI1q"};
	
	private static String[] capsSocialFunctionInputs = {"iC1","iC2a","iD1","iD2","iF1d","iF3","iF4","iG2a"};
	
	private static String[] capsCognitiveInputs = {"iB10","iC1","iC2a","iC3a","iC3b","iC3c","iC4","iC5","iD1","iD2","iE1e","iE1h","iE3a","iE3c","iG2a","iI1c","iI1d","iJ6c"};
	
	private static String[] capsInstitutionalRiskInputs = {"iB5a","iC1","iC2a","iD1","iD2","iE3a","iE3b","iE3c","iE3d","iE3e","iE3f","iG2b","iG2f","iG2g","iG3","iG4b","iG6","iH1","iI1c","iJ1"};
	
	private static String[] scaleCareInputs = {"iC1","iC2a","iD1","iE1a","iE1b","iE1c","iE1d","iE1e","iE1f","iE1g","iG2j","iP1a1","iP1b1","iP2b","iP3"};
	
	private static String[] capsEnvironmentalInputs = {"iE1a","iE1b","iE1c","iE1d","iE1e","iE1f","iE1g","iG1fb","iG4a","iJ2d","iJ2g","iJ2h","iJ2i","iJ6a","iJ7","iQ1a","iQ1b","iQ1c","iQ1e"};
	
	private static String[] capsPreventionInputs = {"iA2","iN1a","iN1b","iN1c","iN1d","iN1e","iN1f","iN1g","iN1h","iN4c"};
	
	private static String[] scaleDivertInputs = {"iE1a","iE1b","iE1c","iE1d","iE1e","iE1f","iE1g","iG5b","iG6","iH2","iI1j","iI1k","iI1l","iI1m","iI1r","iI1s","iI1u","iJ1","iJ2c","iJ2e","iJ3","iJ8a",
			"iJ8b","iK2a","iK2b","iL3","iN2e","iN4a","iN4b"};
	
	private static String[] scaleCHESSInputs = {"iC5","iG6","iJ2n","iJ2u","iJ3","iJ6c","iK2a", "iK2b", "iK2c","iK2d"};
	
	private static String[] capsAbusiveRelationshipInputs = {"iB7a","iE1a","iE1b","iE1c","iE1d","iE1e","iE1f","iE1g","iE1i","iE1j","iF1d","iF1e","iF1f","iF2","iJ2t","iJ6a","iJ7a","iK1a","iK1b","iK2a","iK2c","iM2","iP2b"};
	
	private static String[] capsADLInputs = {"iB10","iC1","iC2a","iC3a","iC3c","iC4","iC5","iD1","iG2a","iG2b","iG2f","iG2h","iG2j","iG6","iI1a","iI1r","iJ1","iJ6b","iJ6c","iN3ea","iN4a"};
	
	private static String[] scaleRUG3aR3hInputs = {"iC1","iC2a","iD1","iE3a","iE3b","iE3c","iE3d","iE3e","iE3f","iG1aa","iG1da","iG1ea","iG2a","iG2g","iG2h","iG2i","iG2j","iI1e","iI1f","iI1i","iI1r",
			"iI1u","iJ2h","iJ2i","iJ2j","iJ2n","iJ2r","iJ2s","iJ6c","iK2a","iK2b","iK3","iL1","iL4","iL5","iL7","iN2a","iN2b","iN2d","iN2e","iN2f","iN2g","iN2h","iN2i","iN2j","iN2k","iN2n","iN3eb","iN3fb","iN3gb"};
	
	private static String[] scaleRUG3aNR3hInputs = {"iC1","iC2a","iD1","iE3a","iE3b","iE3c","iE3d","iE3e","iE3f","iG1aa","iG1da","iG1ea","iG2a","iG2g","iG2h","iG2i","iG2j","iI1e","iI1f","iI1i","iI1r",
			"iI1u","iJ2h","iJ2i","iJ2j","iJ2n","iJ2r","iJ2s","iJ6c","iK2a","iK2b","iK3","iL1","iL4","iL5","iL7","iN2a","iN2b","iN2d","iN2e","iN2f","iN2g","iN2h","iN2i","iN2j","iN2k","iN2n","iN3eb","iN3fb","iN3gb"};
	
	private static String[] scaleAgeInputs = {"iA3","iB2"};
	
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
	
	private static String scaleClinicianRatedMoodOutput = "Clinician Rated Mood Scale"; // Not available
	
	private static String scaleSelfReportMoodOutput = "Self Report Mood Scale"; // Not available
	
	private static String scaleADLHierarchyOutput = "sADL hierarchy scale";
	
	private static String scaleIADLCapacityHierarchyOutput = "IADL Capacity Hierarchy Scale";
	
	private static String scaleFallsOutput = "xFalls"; // Not available
	
	private static String scalePainOutput = "sPAIN scale";
	
	private static String scaleCPSOutput = "sCPS scale";
	
	private static String capsPressureUlcerOutput = "Pressure Ulcer CAP";
	
	private static String scalePressureUlcerRatingOutput = "sPUR Scale";
	
	private static String capsFeedingTubeOutput = "Feeding Tube CAP";
	
	private static String capsBehaviorOutput = "Behavior CAP";
	
	private static String scaleDeafblindSeverityIndexOutput = "Deafblind Severity Index";
	
	private static String scaleCompositeMoodOutput = "Composite Mood Scale"; // Not available
	
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
	
	private static String capsInformalSupportOutput = "Informal Suppport CAP";
	
	private static String capsUrinaryIncontinenceOutput = "Urinary Incontinence CAP";
	
	private static String capsIADLImprovementOutput = "IADL IMPROVEMENT CAP";
	
	private static String capsBowelOutput = "Bowel CAP";
	
	private static String capsSocialFunctionOutput = "Social Function CAP";
	
	private static String capsCognitiveOutput = "Cognitive CAP";
	
	private static String capsInstitutionalRiskOutput = "Institutional Risk CAP";
	
	private static String scaleCareOutput = "Care Algorithm Scale"; // Not available
	
	private static String capsEnvironmentalOutput = "Environmental CAP";
	
	private static String capsPreventionOutput = "Prevention CAP";
	
	private static String scaleDivertOutput = "Divert Scale";
	
	private static String scaleCHESSOutput = "CHESS Scale";
	
	private static String capsAbusiveRelationshipOutput = "Abusive Relationship CAP";
	
	private static String capsADLOutput = "ADL CAP";

	private static String scaleRUG3aR3hOutput = "aR3H";
	
	private static String scaleRUG3aNR3hOutput = "aNR3H";
	
	private static String scaleAgeOutput = "sAGE";
	
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
	
	public static String[] getInformalSupportCapInputs(){return capsInformalSupportInputs;}
	
	public static String getInformalSupportCapOutput(){return capsInformalSupportOutput;}
	
	public static String[] getUrinaryIncontinenceCapInputs(){return capsUrinaryIncontinenceInputs;}
	
	public static String getUrinaryIncontinenceCapOutput(){return capsUrinaryIncontinenceOutput;}
	
	public static String[] getIADLImprovementCapInputs(){return capsIADLImprovementInputs;}
	
	public static String getIADLImprovementCapOutput(){return capsIADLImprovementOutput;}
	
	public static String[] getBowelCapInputs(){return capsBowelInputs;}
	
	public static String getBowelCapOutput(){return capsBowelOutput;}
	
	public static String[] getSocialFunctionInputs(){return capsSocialFunctionInputs;}
	
	public static String getSocialFunctionOutput(){return capsSocialFunctionOutput;}
	
	public static String[] getCognitiveInputs(){return capsCognitiveInputs;}
	
	public static String getCognitiveOutput(){return capsCognitiveOutput;}
	
	public static String[] getInstitutionalRiskCapInputs(){return capsInstitutionalRiskInputs;}
	
	public static String getInstitutionalRiskCapOutput(){return capsInstitutionalRiskOutput;}
	
	public static String[] getCareScaleInputs(){return scaleCareInputs;}
	
	public static String getCareScaleOutput(){return scaleCareOutput;}
	
	public static String[] getEnvironmentalCapsInputs(){return capsEnvironmentalInputs;}
	
	public static String getEnvironmentalCapsOutput(){return capsEnvironmentalOutput;}
	
	public static String[] getPreventionCapsInputs(){return capsPreventionInputs;}
	
	public static String getPreventionCapsOutput(){return capsPreventionOutput;}
	
	public static String[] getDivertScaleInputs(){return scaleDivertInputs;}
	
	public static String getDivertScaleOutput(){return scaleDivertOutput;}
	
	public static String[] getCHESSScaleInputs(){return scaleCHESSInputs;}
	
	public static String getCHESSScaleOutput(){return scaleCHESSOutput;}
	
	public static String[] getAbusiveRelationshipCapInputs(){return capsAbusiveRelationshipInputs;}
	
	public static String getAbusiveRelationshipCapOutput(){return capsAbusiveRelationshipOutput;}
	
	public static String[] getADLCapInputs(){return capsADLInputs;}
	
	public static String getADLCapOutput(){return capsADLOutput;}
	
	public static String[] getRUG3aNR3hInputs(){return scaleRUG3aNR3hInputs;}
	
	public static String getRUG3aNR3hOutput(){return scaleRUG3aNR3hOutput;}
	
	public static String[] getRUG3aR3hInputs(){return scaleRUG3aR3hInputs;}
	
	public static String getRUG3aR3hOutput(){return scaleRUG3aR3hOutput;}
	
	public static String[] getAgeScaleInputs(){return scaleAgeInputs;}
	
	public static String getAgeScaleOutput(){return scaleAgeOutput;}
}
