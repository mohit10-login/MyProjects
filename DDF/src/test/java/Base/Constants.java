package Base;

import java.io.File;

public class Constants {
	private static final String  configfilepath=System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Config.properties";
	private static final String  productfilepath=System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Product.properties";
	
	public static File getConfigFilePath()
	{
		File configfile=new File(configfilepath);
		return configfile;
	}
	
	public static File getProductFilePath()
	{
		File productfile=new File(productfilepath);
		return productfile;
	}
}
