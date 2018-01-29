package API;

import BaseLibrary.*;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.text.StyleConstants.CharacterConstants;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.http.HttpClient;

public class HTTPMethods {
	public static HTTPMethods Instance;
	private JSONObject JsonResponse;

	public HTTPMethods() {
	
	}

	public static HTTPMethods getInstance() {
		if (Instance == null) {
			Instance = new HTTPMethods();
		}
		return Instance;
	}

	// This Method accept GetAPIMethode URL and Authorization as an True or
	// False, and return and ArrayList having Status Code at 0 index and API
	// JsonResponse at 1 index
	public ArrayList<Object> Get(String APIURL, boolean Authorization)
			throws ClientProtocolException, IOException, JSONException {
		this.JsonResponse=null;
		CloseableHttpClient client=HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(APIURL);
		ArrayList<Object> output = new ArrayList<Object>();
		if (Authorization) {
			getRequest.setHeader("Authorization", BaseVariables.getInstance().getBearerTocken());
		}
		getRequest.addHeader("Accept", "application/json");
		getRequest.addHeader("Content-type", "application/json");

		CloseableHttpResponse HTTPResponse = client.execute(getRequest);

		// System.out.println("Response Code of Get Requst is = "+
		// HTTPResponse.getStatusLine().getStatusCode());
		if (HTTPResponse.getStatusLine().getStatusCode() == 200) {
			String APIResponse = HTTPMethods.ReadResponseFromHTTPResponse(HTTPResponse);
			this.JsonResponse = new JSONObject(APIResponse);
			output.add(0, HTTPResponse.getStatusLine().getStatusCode());
			output.add(1, this.JsonResponse);
			HTTPResponse.close();
			return output;
		} else {
			output.add(0, HTTPResponse.getStatusLine().getStatusCode());
			HTTPResponse.close();
			return output;
		}
		
	}

	// This Method accept PostAPIMethode URL and Authorization as an True or
	// False, and return and ArrayList having Status Code at 0 index and API
	// JsonResponse at 1 index
	public ArrayList<Object> Post(String APIURL, String JsonDataToPost, boolean Authorization)
			throws ClientProtocolException, IOException, JSONException {
		this.JsonResponse=null;
		CloseableHttpClient client=HttpClients.createDefault();
		ArrayList<Object> output = new ArrayList<Object>();
		HttpPost postRequest = new HttpPost(APIURL);
		StringEntity entitiy = new StringEntity(JsonDataToPost);

		if (Authorization) {
			postRequest.setHeader("Authorization", BaseVariables.getInstance().getBearerTocken());
		}
		postRequest.addHeader("Accept", "application/json");
		postRequest.addHeader("Content-type", "application/json");
		postRequest.setEntity(entitiy);

		CloseableHttpResponse HTTPResponse = client.execute(postRequest);

		// System.out.println("Response Code of Request is =
		// "+HTTPResponse.getStatusLine().getStatusCode());
		if (HTTPResponse.getStatusLine().getStatusCode() == 200) {
			String APIResponse = ReadResponseFromHTTPResponse(HTTPResponse);
			this.JsonResponse = new JSONObject(APIResponse);
			output.add(0, HTTPResponse.getStatusLine().getStatusCode());
			output.add(1, this.JsonResponse);
			HTTPResponse.close();
			return output;
		} else {
			output.add(0, HTTPResponse.getStatusLine().getStatusCode());
			HTTPResponse.close();
			return output;
		}
	}

	public ArrayList<Object> Put(String APIURL, String JsonDataToPost, boolean Authorization)
			throws ClientProtocolException, IOException, JSONException {
		this.JsonResponse=null;
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPut putRequest = new HttpPut(APIURL);
		ArrayList<Object> output = new ArrayList<Object>();
		StringEntity entity = new StringEntity(JsonDataToPost);

		if (Authorization) {
			putRequest.setHeader("Authorization", BaseVariables.getInstance().getBearerTocken());
		}
		putRequest.setEntity(entity);
		putRequest.addHeader("Accept", "application/json");
		putRequest.addHeader("Content-type", "application/json");

		CloseableHttpResponse HTTPResponse = client.execute(putRequest);

		if (HTTPResponse.getStatusLine().getStatusCode() == 200) {
			String APIResponse = ReadResponseFromHTTPResponse(HTTPResponse);
			try{
				this.JsonResponse = new JSONObject(APIResponse);	
			}
			catch(Exception e)
			{
				
			}
			output.add(0, HTTPResponse.getStatusLine().getStatusCode());
			if(this.JsonResponse!=null)
				output.add(1, this.JsonResponse);
			HTTPResponse.close();
			return output;
		} else {
			output.add(0, HTTPResponse.getStatusLine().getStatusCode());
			HTTPResponse.close();
			return output;
		}
	}

	public ArrayList<Object> Delete(String APIURL, boolean Authorization) throws ClientProtocolException, IOException, JSONException{
		CloseableHttpResponse HTTPResponse=null;
		this.JsonResponse=null;
		ArrayList<Object> output = new ArrayList<Object>();
		try {
			
			CloseableHttpClient client=HttpClients.createDefault();
			
			HttpDelete deleteRequest = new HttpDelete(APIURL);
			if (Authorization) {
				deleteRequest.setHeader("Authorization", BaseVariables.getInstance().getBearerTocken());
			}
			//deleteRequest.setHeader("Authorization", "bearer hC-b2RhghLbTuKdy-CBna5_XCVs0-n8JuGf4ATfHXwBlcGXUkN-7nFl7JQ1a-LbZILmKuMwoxOYw_rVevo8BwDD9_v8zVYHQVgfT2kxFQYuuN8G-Abe9BlaIVHELZ5BUaEdGnSQnIwtZpP046bfSRtk8IZblyH1XV-n-VRZox6o2zg-QTZfRYIt6jy51JttH4tzvaUY8iSFX9gDDmcAvHIuDPfyl9aUBmFUFKjtgeWFNCwfuGo5nOrJLQDCEfvkmRkgjyxlnn2FJfFaiuTVhP6CMijui4_E85NLjG4vDYlzEk1_eLem6DH0MoxD_5bka");
			deleteRequest.addHeader("Accept", "application/json");
			deleteRequest.addHeader("Content-type", "application/json");
			try{
			HTTPResponse=client.execute(deleteRequest);
			}
			catch(Exception e)
			{
			HTTPResponse=client.execute(deleteRequest);
			}
			
			if (HTTPResponse.getStatusLine().getStatusCode() == 200) {
				String APIResponse = ReadResponseFromHTTPResponse(HTTPResponse);
				this.JsonResponse = new JSONObject(APIResponse);
				output.add(0, HTTPResponse.getStatusLine().getStatusCode());
				if(this.JsonResponse!=null)
					output.add(1, this.JsonResponse);
				HTTPResponse.close();
				return output;
			} else {
				output.add(0, HTTPResponse.getStatusLine().getStatusCode());
				HTTPResponse.close();
				return output;
			}
		} catch (Exception e) {
			output.add(0, HTTPResponse.getStatusLine().getStatusCode());
			return output;
		}		
	}

	public static String ReadResponseFromHTTPResponse(HttpResponse response) {
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
			// e.printStackTrace();
		}
		return null;
	}

	public String GenerateAccessToken(String Envirnment) throws ClientProtocolException, IOException, JSONException {
		this.JsonResponse=null;
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPost PostRequest;
		String Body;
		if (Envirnment.equalsIgnoreCase("QA")) {
			PostRequest = new HttpPost(Constants.QAAuthServiceURL);
			Body = Constants.QAAuthServiceBody;
		} else if (Envirnment.equalsIgnoreCase("Reg") || Envirnment.equalsIgnoreCase("Regression")) {
			PostRequest = new HttpPost(Constants.QAAuthServiceURL);
			Body = Constants.QAAuthServiceBody;
		} else if (Envirnment.equalsIgnoreCase("STG") || Envirnment.equalsIgnoreCase("Staging")) {
			PostRequest = new HttpPost(Constants.ProdAuthServiceURL);
			Body = Constants.ProdAuthServiceBody;
		} else if (Envirnment.equalsIgnoreCase("Prod") || Envirnment.equalsIgnoreCase("Production")) {
			PostRequest = new HttpPost(Constants.ProdAuthServiceURL);
			Body = Constants.ProdAuthServiceBody;
		} else
			return null;

		StringEntity Entity = new StringEntity(Body);

		PostRequest.setEntity(Entity);
		PostRequest.addHeader("Accept", "application/json");
		PostRequest.addHeader("Content-type", "application/json");

		
		CloseableHttpResponse HTTPResponse = client.execute(PostRequest);

		String APIResponse = HTTPMethods.ReadResponseFromHTTPResponse(HTTPResponse);

		this.JsonResponse = new JSONObject(APIResponse);
		HTTPResponse.close();
		return "bearer " + this.JsonResponse.get("access_token");
	}

}
