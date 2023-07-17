package util;

import java.text.SimpleDateFormat;
import java.util.Date;  

public class Reusables {

	@SuppressWarnings("deprecation")
	public static String getNextMonthDate()
	{
		Date date = new Date();
		date.setDate(01);
		if(date.getMonth()==12)
			date.setMonth(1);
		else
			date.setMonth(date.getMonth()+1);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String strDate= formatter.format(date);  
		return strDate;
	}
}
