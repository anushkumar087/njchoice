package util;

public class DataFile {
	
	// File names
	public static final String ConfigFile = System.getProperty("user.dir")+"/src/test/java/config/config.properties";
	public static final String MasterDataFile = System.getProperty("user.dir")+"/test_data/MasterDataFile.xlsx";
	
	
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
