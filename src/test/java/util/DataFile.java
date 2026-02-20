package util;

import java.io.FileInputStream;
import java.util.Properties;

public class DataFile {
	
	// File names
	public static final String ConfigFile = System.getProperty("user.dir")+"/src/test/java/config/config.properties";
	public static final String SectionAConfigFile = System.getProperty("user.dir")+"/src/test/java/config/config_sectionA_data.properties";
	public static final String MasterDataFile = System.getProperty("user.dir")+"/test_data/MasterDataFile.xlsx";
	public static final String reportSheet = System.getProperty("user.dir")+"/reports/report";
	
	// Static initializer to load properties from config file
	private static Properties sectionAProperties = new Properties();
	
	static {
		try {
			FileInputStream file = new FileInputStream(SectionAConfigFile);
			sectionAProperties.load(file);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	public static final String ReferralDateValue = sectionAProperties.getProperty("referralDate", "07/07/2023");
	
	public static final String ReferralEntryDateValue = Reusables.getNextMonthDate();
	
	public static final String ReferralAssessmentSourceValue = sectionAProperties.getProperty("referralAssessmentSource", "1");
	
	public static final String AssessmentOrganizationNameValue = sectionAProperties.getProperty("assessmentOrganizationName", "1");
	
	public static final String ReasonForAssessmentValue = sectionAProperties.getProperty("reasonForAssessment", "1");
	
	public static final String FirstNameValue = sectionAProperties.getProperty("firstName", "John");
	
	public static final String LastNameValue = sectionAProperties.getProperty("lastName", "Doe");
	
	public static final String GenderValue = sectionAProperties.getProperty("gender", "1");
	
	public static final String GenderIdentityValue = sectionAProperties.getProperty("genderIdentity", "1");
	
	public static final String BirthdateValue = sectionAProperties.getProperty("birthdate", "01/01/1993");
	
	public static final String SocialSecurityNumberValue = sectionAProperties.getProperty("socialSecurityNumber", "111111111");
	
	public static final String StreetAddress1Value = sectionAProperties.getProperty("streetAddress1", "test");
	public static final String City1Value = sectionAProperties.getProperty("city1", "test");
	public static final String ZipCode1Value = sectionAProperties.getProperty("zipCode1", "11111");
	public static final String CountyValue = sectionAProperties.getProperty("county", "1");
	
	// Alert names
	public static final String noErrorsReportedAlert = sectionAProperties.getProperty("noErrorsReportedAlert", "No errors reported in the fields");
	public static final String informationSavedAlert = sectionAProperties.getProperty("informationSavedAlert", "Information Saved");
	public static final String informationUpdatedAlert = sectionAProperties.getProperty("informationUpdatedAlert", "Information Updated");
	public static final String incompleteAssessmentAlert = sectionAProperties.getProperty("incompleteAssessmentAlert", "Please complete all the sections before completing the application");
	public static final String noErrorReportedAlert = sectionAProperties.getProperty("noErrorReportedAlert", "No error reported from Section A to S");
	
	

}
