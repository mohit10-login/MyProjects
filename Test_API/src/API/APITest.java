package API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gargoylesoftware.htmlunit.util.StringUtils;


public class APITest extends HTTPMethods{
	
	public static void main(String args[]) throws ClientProtocolException, IOException, JSONException
	{
		//APITest obj=new APITest();
		//obj.getResponseFromGetMethods();
		//obj.PostJson();
		//HTTPMethods obj1=new HTTPMethods();
		//obj1.Put("http://api-qa-companysearch.azurewebsites.net/companies/v2/logo?companyID=9ecb81e1-0b65-4611-9646-f28a3d8cd4f9", , true);
		//obj1.PostJson("http://test-auth.livecareer.com/oauth/token", "grant_type=client_credentials&client_id=LCCWT&client_secret=anVzdCBkbyBpdCE=&response_type=token", false);
		//System.out.println(obj1.GenerateAccessToken("QA"));
		//InitiateResources obj=new InitiateResources();
		//obj.initiateBaseVariables();
		
		String APIURL="http://api-companysearch.livecareer.com/GetLogo?companyAccessName=";
		File src=new File("C:\\Users\\mohit.sharma2\\Desktop\\Jobscompanies.xlsx");
		FileInputStream fis =new FileInputStream(src);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("Data");
		String request;
		String response;
		JSONObject APIResponse;
		ArrayList<Object> list;
		String accessname;
//		File outputsheet=new File("C:\\Users\\mohit.sharma2\\Desktop\\JobsCompaniesWithoutLogo.xlsx");
//		FileInputStream outfis =new FileInputStream(outputsheet);
//		XSSFWorkbook outworkbook=new XSSFWorkbook(outfis);
//		XSSFSheet outsheet=outworkbook.getSheet("AccessName");
		
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			
			accessname=sheet.getRow(i).getCell(0).getStringCellValue().toString();
			System.out.println(accessname);
			request=APIURL+accessname;
			list=HTTPMethods.getInstance().Get(request, false);
			APIResponse=(JSONObject) list.get(1);
			response=APIResponse.getString("LogoUrl");
			
			if(response.contains("defaultcompanylogo"))
			{
				sheet.getRow(i).createCell(5).setCellValue(accessname);
				FileOutputStream output=new FileOutputStream("C:\\Users\\mohit.sharma2\\Desktop\\Jobscompanies.xlsx");
				workbook.write(output);
			}
		}
		workbook.close();
	//	outworkbook.close();
		System.out.println("End");
	}
	
	public void getResponseFromGetMethods() throws ClientProtocolException, IOException, JSONException
	{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet getRequest=new HttpGet("http://api-qa-companysearch.azurewebsites.net/companies/v2/companies/CompanyAccessName?param_value=walmartmohit");
		
		getRequest.setHeader("Authorization", "bearer 1aYOLSdszUlUAxtL_uJo1Gx9PbBwyD5JSCcvxSSMIy1lMZzRtlArIbAW9GrPIAbBd6tq5_NRxGW24nafS-iowmbCm-ZRAuWfqdugZQG1i9dDz4R1MCoE_VHXxtVeHJ3mYPbbukhhZcfc5eZdhQCIp0UqftCdq5Lv7EcvTppeb-L63MThV-aaPw3aFqD_HYcGwj2o7NIzs9kayiOJTf1khlK-UXrDBxr3diso7d4jd4jxLK8zaBfHve8FQ9Je8f_h");
		getRequest.addHeader("Accept", "application/json");
		getRequest.addHeader("Content-type", "application/json");
		
		CloseableHttpResponse Response=null;
		try{
		Response=client.execute(getRequest);
		}
		catch(Exception e)
		{
			Response=client.execute(getRequest);
		}
		
		System.out.println("Response Code of Get Requst is = "+ Response.getStatusLine().getStatusCode());
		
		if(Response.getStatusLine().getStatusCode()!= 200)
		{
			throw new RuntimeException("Failed : HTTP error code : " + Response.getStatusLine().getStatusCode());
		}
		
		String resp=readResponse(Response);
		JSONObject jsonresponse=new JSONObject(resp);
		JSONArray jarray=jsonresponse.getJSONArray("IndustriesAccessNames");
		System.out.println(jarray.toString());
		System.out.println(jarray.getString(jarray.length()-1));
		System.out.println(jsonresponse.get("OwlerID"));
		
		//BufferedReader br=new BufferedReader(new InputStreamReader(Response.getEntity().getContent();
		
		client.close();
		
	}
	
	public void PostJson() throws ClientProtocolException, IOException, JSONException
	{
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPost post=new HttpPost("http://api-qa-companysearch.azurewebsites.net/ValidateCompanies");
		
		
		
		String json="{\"Values\": [\"walmart\"]}";
		StringEntity entitiy=new StringEntity(json);
		
		post.setEntity(entitiy);
		post.addHeader("Accept", "application/json");
		post.addHeader("Content-type", "application/json");
		
		CloseableHttpResponse response=client.execute(post);
		
		System.out.println("Response Code of Request is = "+response.getStatusLine().getStatusCode());
		
		String resp = readResponse(response);
		
		JSONObject jsonresp = new JSONObject(resp);
		System.out.println(jsonresp.toString());
		
		JSONArray jArray = jsonresp.getJSONArray("Companies");
		System.out.println(jArray.getJSONObject(0).getString("Name"));
		System.out.println(jArray.getJSONObject(0).getString("AccessName"));
		
		System.out.println(response.getEntity().getContent());
		
		client.close();
		
		
	}
	
	public static String readResponse(HttpResponse response) {
        try {
            InputStream ips = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String s;
            while (true) {
                s = buf.readLine();
                if (s == null || s.length() == 0)
                    break;
                sb.append(s);
            }
            return sb.toString();

        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }
	
//	public void generateAccessToken() {
//		try {
//		String dataToPost = "grant_type=client_credentials&client_id=LCCWT&client_secret=anVzdCBkbyBpdCE=&response_type=token";
//		HashMap<String, String> headers = new HashMap<String, String>();
//		ResponseBean response = HttpService.post(AUTH_URL, headers, dataToPost);
//		String respString = response.message;
//		JSONObject respJson = new JSONObject(respString);
//		String access_token = "Bearer " + respJson.get("access_token");
//		System.out.println("Access Token: " + access_token);
//		if (access_token.equals(null)) {
//		System.exit(0);
//		}
//		} catch (Exception e) {
//		e.printStackTrace();
//		System.exit(0);
//		}
//		}
	
}
