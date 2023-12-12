package util;

public class DataFile {
	
	// File names
	public static final String ConfigFile = System.getProperty("user.dir")+"/src/test/java/config/config.properties";
	public static final String MasterDataFile = System.getProperty("user.dir")+"/test_data/MasterDataFile.xlsx";
	public static final String reportSheet = System.getProperty("user.dir")+"/reports/report";
	
//	public static final String ScaleDepressionSDRS_DataFile=System.getProperty("user.dir")+"/test_data/ScaleDepressionSDRS_data.xlsx";
//	public static final String capsCardioRespiratory_DataFile=System.getProperty("user.dir")+"/test_data/CapsCardioRespiratory_data.xlsx";
//	public static final String ScaleSmokingAndDrinkingy_DataFile=System.getProperty("user.dir")+"/test_data/ScaleSmokingAndDrinking_data.xlsx";
//	public static final String CapsFalls_DataFile = System.getProperty("user.dir")+"/test_data/CapsFalls_data.xlsx";
//	public static final String ScaleCommunication_DataFile = System.getProperty("user.dir")+"/test_data/ScaleCommunication_data.xlsx";
//	public static final String CapsDehydration_DataFile = System.getProperty("user.dir")+"/test_data/CapsDehydration_data.xlsx";
//	public static final String CapsPain_DataFile = System.getProperty("user.dir")+"/test_data/CapsPain_data.xlsx";
//	public static final String CapsDelirium_DataFile = System.getProperty("user.dir")+"/test_data/CapsDelirium_data.xlsx";
//	public static final String ScaleAggressiveBehavior_DataFile = System.getProperty("user.dir")+"/test_data/ScaleAggressiveBehavior_data.xlsx";
//	public static final String ScaleClinicianRatedMood_DataFile = System.getProperty("user.dir")+"/test_data/ScaleClinicianRatedMood_data.xlsx";
//	public static final String ScaleSelfReportMood_DataFile = System.getProperty("user.dir")+"/test_data/ScaleSelfReportMood_data.xlsx";
//	public static final String ScaleIADLCapacityHierarchy_DataFile = System.getProperty("user.dir")+"/test_data/ScaleIADLCapacityHierarchy_data.xlsx";
//	public static final String ScaleFalls_DataFile = System.getProperty("user.dir")+"/test_data/ScaleFalls_data.xlsx";
//	public static final String ScalePain_DataFile = System.getProperty("user.dir")+"/test_data/ScalePain_data.xlsx";
//	public static final String ScaleCognitivePerformance_DataFile = System.getProperty("user.dir")+"/test_data/ScaleCognitivePerformance_data.xlsx";
//	public static final String CapsPressureUlcer_DataFile = System.getProperty("user.dir")+"/test_data/CapsPressureUlcer_data.xlsx";
//	public static final String ScalePressureUlcerRating_DataFile = System.getProperty("user.dir")+"/test_data/ScalePressureUlcerRating_data.xlsx";
//	public static final String CapsFeedingTube_DataFile = System.getProperty("user.dir")+"/test_data/CapsFeedingTube_data.xlsx";
//	public static final String CapsBehavior_DataFile = System.getProperty("user.dir")+"/test_data/CapsBehavior_data.xlsx";
//	public static final String ScaleDeafblindSeverityIndex_DataFile = System.getProperty("user.dir")+"/test_data/ScaleDeafblindSeverityIndex_data.xlsx";
//	public static final String ScaleCompositeMood_DataFile = System.getProperty("user.dir")+"/test_data/ScaleCompositeMood_data.xlsx";
//	public static final String CapsPhysicalActivityPromotion_DataFile = System.getProperty("user.dir")+"/test_data/CapsPhysicalActivityPromotion_data.xlsx";
//	public static final String ScaleCognitivePerformanceV2_DataFile = System.getProperty("user.dir")+"/test_data/ScaleCognitivePerformanceV2_data.xlsx";
//	public static final String ScaleADLHierarchy_DataFile = System.getProperty("user.dir")+"/test_data/ScaleADLHierarchy_data.xlsx";
//	public static final String CapsCommunication_DataFile = System.getProperty("user.dir")+"/test_data/CapsCommunication_data.xlsx";
//	public static final String ScaleADLSF_DataFile = System.getProperty("user.dir")+"/test_data/ScaleADLSF_data.xlsx";
//	public static final String ScaleADLLF_DataFile = System.getProperty("user.dir")+"/test_data/ScaleADLLF_data.xlsx";
//	public static final String ScalePain1_DataFile = System.getProperty("user.dir")+"/test_data/ScalePain1_data.xlsx";
//	public static final String ScaleBMI_DataFile = System.getProperty("user.dir")+"/test_data/ScaleBMI_data.xlsx";
//	public static final String ScaleFunctionalHierarchy_DataFile = System.getProperty("user.dir")+"/test_data/ScaleFunctionalHierarchy_data.xlsx";
//	public static final String CapsNutrition_DataFile = System.getProperty("user.dir")+"/test_data/CapsNutrition_data.xlsx";
//	public static final String CapsMood_DataFile = System.getProperty("user.dir")+"/test_data/CapsMood_data.xlsx";
//	public static final String CapsInformalSupport_DataFile = System.getProperty("user.dir")+"/test_data/CapsInformalSupport_data.xlsx";
//	public static final String CapsUrinaryIncontinence_DataFile = System.getProperty("user.dir")+"/test_data/CapsUrinaryIncontinence_data.xlsx";
//	public static final String CapsIADLImprovement_DataFile = System.getProperty("user.dir")+"/test_data/CapsIADLImprovement_data.xlsx";
//	public static final String CapsBowel_DataFile = System.getProperty("user.dir")+"/test_data/CapsBowel_data.xlsx";
//	public static final String CapsSocialFunction_DataFile = System.getProperty("user.dir")+"/test_data/CapsSocialFunction_data.xlsx";
//	public static final String CapsCognitive_DataFile = System.getProperty("user.dir")+"/test_data/CapsCognitive_data.xlsx";
//	public static final String CapsInstitutionalRisk_DataFile = System.getProperty("user.dir")+"/test_data/CapsInstitutionalRisk_data.xlsx";
//	public static final String ScaleCare_DataFile = System.getProperty("user.dir")+"/test_data/ScaleCare_data.xlsx";
//	public static final String CapsEnvironmental_DataFile = System.getProperty("user.dir")+"/test_data/CapsEnvironmental_data.xlsx";
//	public static final String CapsPrevention_DataFile = System.getProperty("user.dir")+"/test_data/CapsPrevention_data.xlsx";
//	public static final String ScaleDivert_DataFile = System.getProperty("user.dir")+"/test_data/ScaleDivert_data.xlsx";
//	public static final String ScaleCHESS_DataFile = System.getProperty("user.dir")+"/test_data/ScaleCHESS_data.xlsx";
//	public static final String CapsAbusiveRelationship_DataFile = System.getProperty("user.dir")+"/test_data/CapsAbusiveRelationship_data.xlsx";
//	public static final String CapsADL_DataFile = System.getProperty("user.dir")+"/test_data/CapsADL_data.xlsx";
//	public static final String ScaleRUG3aNR3h_DataFile = System.getProperty("user.dir")+"/test_data/ScaleRUG3aNR3h_data.xlsx";
//	public static final String ScaleRUG3aR3h_DataFile = System.getProperty("user.dir")+"/test_data/ScaleRUG3aR3h_data.xlsx";
	
	public static final String master_DataFile = System.getProperty("user.dir")+"/test_data/Master_Data.xlsx";
	
	public static final String ScaleDepressionSDRS_DataFile=master_DataFile;
	public static final String capsCardioRespiratory_DataFile=master_DataFile;
	public static final String ScaleSmokingAndDrinkingy_DataFile=master_DataFile;
	public static final String CapsFalls_DataFile = master_DataFile;
	public static final String ScaleCommunication_DataFile = master_DataFile;
	public static final String CapsDehydration_DataFile = master_DataFile;
	public static final String CapsPain_DataFile = master_DataFile;
	public static final String CapsDelirium_DataFile = master_DataFile;
	public static final String ScaleAggressiveBehavior_DataFile = master_DataFile;
	public static final String ScaleClinicianRatedMood_DataFile = master_DataFile;
	public static final String ScaleSelfReportMood_DataFile = master_DataFile;
	public static final String ScaleIADLCapacityHierarchy_DataFile = master_DataFile;
	public static final String ScaleFalls_DataFile = master_DataFile;
	public static final String ScalePain_DataFile = master_DataFile;
	public static final String ScaleCognitivePerformance_DataFile = master_DataFile;
	public static final String CapsPressureUlcer_DataFile = master_DataFile;
	public static final String ScalePressureUlcerRating_DataFile = master_DataFile;
	public static final String CapsFeedingTube_DataFile = master_DataFile;
	public static final String CapsBehavior_DataFile = master_DataFile;
	public static final String ScaleDeafblindSeverityIndex_DataFile = master_DataFile;
	public static final String ScaleCompositeMood_DataFile = master_DataFile;
	public static final String CapsPhysicalActivityPromotion_DataFile = master_DataFile;
	public static final String ScaleCognitivePerformanceV2_DataFile = master_DataFile;
	public static final String ScaleADLHierarchy_DataFile = master_DataFile;
	public static final String CapsCommunication_DataFile = master_DataFile;
	public static final String ScaleADLSF_DataFile = master_DataFile;
	public static final String ScaleADLLF_DataFile = master_DataFile;
	public static final String ScalePain1_DataFile = master_DataFile;
	public static final String ScaleBMI_DataFile = master_DataFile;
	public static final String ScaleFunctionalHierarchy_DataFile = master_DataFile;
	public static final String CapsNutrition_DataFile = master_DataFile;
	public static final String CapsMood_DataFile = master_DataFile;
	public static final String CapsInformalSupport_DataFile = master_DataFile;
	public static final String CapsUrinaryIncontinence_DataFile = master_DataFile;
	public static final String CapsIADLImprovement_DataFile = master_DataFile;
	public static final String CapsBowel_DataFile = master_DataFile;
	public static final String CapsSocialFunction_DataFile = master_DataFile;
	public static final String CapsCognitive_DataFile = master_DataFile;
	public static final String CapsInstitutionalRisk_DataFile = master_DataFile;
	public static final String ScaleCare_DataFile = master_DataFile;
	public static final String CapsEnvironmental_DataFile = master_DataFile;
	public static final String CapsPrevention_DataFile = master_DataFile;
	public static final String ScaleDivert_DataFile = master_DataFile;
	public static final String ScaleCHESS_DataFile = master_DataFile;
	public static final String CapsAbusiveRelationship_DataFile = master_DataFile;
	public static final String CapsADL_DataFile = master_DataFile;
	public static final String ScaleRUG3aNR3h_DataFile = master_DataFile;
	public static final String ScaleRUG3aR3h_DataFile = master_DataFile;
	public static final String ScaleAge_DataFile = master_DataFile;
	
	// Section A - Referral page data
	public static final String ReferralDateValue = "07/07/2023";
	
	public static final String ReferralEntryDateValue = Reusables.getNextMonthDate();
	
	public static final String ReferralAssessmentSourceValue = "1";
	
	public static final String AssessmentOrganizationNameValue = "1";
	
	public static final String ReasonForAssessmentValue = "1";
	
	public static final String FirstNameValue = "Anush";
	
	public static final String LastNameValue = "Malli";
	
	public static final String GenderValue = "1";
	
	public static final String GenderIdentityValue = "1";
	
	public static final String BirthdateValue = "01/01/1993";
	
	public static final String SocialSecurityNumberValue = "111111111";
	
	public static final String StreetAddress1Value = "test";
	public static final String City1Value = "test";
	public static final String ZipCode1Value = "11111";
	public static final String CountyValue = "1";
	
	// Alert names
	public static final String noErrorsReportedAlert = "No errors reported in the fields";
	public static final String informationSavedAlert = "Information Saved";
	public static final String informationUpdatedAlert = "Information Updated";
	
	

}
