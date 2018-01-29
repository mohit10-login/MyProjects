package BaseLibrary;

public class Constants {
	public static final String QAAuthServiceURL="http://test-auth.livecareer.com/oauth/token";
	public static final String ProdAuthServiceURL="http://auth.livecareer.com/oauth/token";
	public static final String QAAuthServiceBody="grant_type=client_credentials&client_id=LCCWT&client_secret=anVzdCBkbyBpdCE=&response_type=token";
	public static final String ProdAuthServiceBody="grant_type=client_credentials&client_id=LCAUS&client_secret=bGl2ZWNhcmVlciByb2NrcyE=&response_type=token";
	
	public static final String GetCompanyMethodeURL="http://api-qa-companysearch.azurewebsites.net/companies/v2/companies/";
}
