package BaseLibrary;

public class BaseVariables {
	private static BaseVariables instance;
	private String bearerTocken;
	private String BaseURL;
	private String Product;
	
	private BaseVariables()
	{
		
	}
	
	public static BaseVariables getInstance()
	{
		if(instance==null)
		{
			instance=new BaseVariables();
		}
		return instance;
	}
	
	public String getBearerTocken()
	{
		return this.bearerTocken;
	}
	public void setBearerTocken(String tocken)
	{
		this.bearerTocken=tocken;
	}
	
	public String getBaseURL()
	{
		return this.BaseURL;
	}
	public void setBaseURL(String URL)
	{
		this.BaseURL=URL;
	}
	public String getProduct()
	{
		return this.Product;
	}
	public void setProduct(String product)
	{
		this.Product=product;
	}
}
