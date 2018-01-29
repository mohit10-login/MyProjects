package API;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONException;

import BaseLibrary.BaseVariables;

public class InitiateResources extends HTTPMethods{
	public static InitiateResources instance;
	
	public static InitiateResources getInstance()
	{
		if(instance==null)
		{
			instance=new InitiateResources();
		}
		return instance;
	}
	
	public void initiateBaseVariables() throws FileNotFoundException, IOException, JSONException
	{
		Properties prop=new Properties();
		prop.load(new FileInputStream(".\\bin\\Utils\\Test.properties"));
		String Envirnment=prop.getProperty("EXECUTION_ENVIRNMENT").trim();
		BaseVariables.getInstance().setBearerTocken(getInstance().GenerateAccessToken(Envirnment));
		
		BaseVariables.getInstance().setBaseURL(prop.getProperty("APIURL").trim());
		
		BaseVariables.getInstance().setProduct(prop.getProperty("PRODUCT").trim());
		System.out.println(BaseVariables.getInstance().getBearerTocken());
	}
	
}
