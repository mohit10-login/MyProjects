package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import BaseLibrary.BaseVariables;

public class initiateResources {
	
	public static String getBaseResourcesFolder(String name)
	{
		String BaseFolder=null;
		if(name.length()==0)
			BaseFolder=System.getProperty("user.dir")+"\\bin\\Util\\";
		else
			BaseFolder=System.getProperty("user.dir")+"\\bin\\Util\\" + name;
		return BaseFolder;
	}
	
	public static void setConfigProperties() throws IOException
	{
		Properties property=new Properties();
		property.load(new FileInputStream(initiateResources.getBaseResourcesFolder("config.properties")));
		BaseVariables.Product= property.getProperty("PRODUCT").trim();
		BaseVariables.Envirnment=property.getProperty("EXECUTION_ENVIRNMENT").trim();
		BaseVariables.browser=property.getProperty("BROWSER").trim();
	}
}
