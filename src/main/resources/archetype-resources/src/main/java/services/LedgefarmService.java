package com.ledgefarm.core;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.GsonBuilder;


public class LedgefarmService {

    private String _token;
	private String _apiKey;
	private String _apiUrl;
	Gson gson = new GsonBuilder()
    .enableComplexMapKeySerialization()
    .create();
    public LedgefarmService(String token) {
        this._token = token;
        this.getConfiguration();
    }

  //  @Override
    public JsonObject sendHttpPost(JsonObject object, String requestUrl) throws IOException  {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse execute = null;
        JsonObject jsonObject =null;
        String requestJson = new Gson().toJson(object);

        try {
            HttpPost httpPost = new HttpPost(this._apiUrl + requestUrl);
            StringEntity entity = new StringEntity(requestJson, "UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            httpPost.setHeader("api-key", this._apiKey);
            httpPost.setHeader("accessKey", this._token);
            execute = httpclient.execute(httpPost);
            jsonObject = this.deseralize(execute);
        } catch (IOException e) {
        }
        finally{
            httpclient.close();
        }
        return jsonObject;
    }

    //@Override
    public JsonObject sendHttpPut(JsonObject object, String requestUrl) throws IOException  {
        CloseableHttpResponse execute = null;
        JsonObject jsonObject =null;
        String requestJson = new Gson().toJson(object);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpPut httpPut = new HttpPut(this._apiUrl + requestUrl);
            StringEntity entity = new StringEntity(requestJson, "UTF-8");
            entity.setContentType("application/json");
            httpPut.setEntity(entity);
            httpPut.setHeader("api-key", this._apiKey);
			httpPut.setHeader("accessKey", this._token);
            execute = httpclient.execute(httpPut);
            jsonObject = this.deseralize(execute);
        } catch (IOException e) {
        }
        finally{
            httpclient.close();
        }
        return jsonObject;
    }

  //  @Override
    public JsonObject sendHttpGet(JsonObject object, String requestUrl) throws IOException {
        CloseableHttpResponse execute = null;
        JsonObject jsonObject =null;
        CloseableHttpClient httpClient =  HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(this._apiUrl + requestUrl);
            httpGet.setHeader("api-key", this._apiKey);
            httpGet.setHeader("accessKey", this._token);
            execute = httpClient.execute(httpGet);
            jsonObject = this.deseralize(execute);
        } catch (IOException e) {
        }finally{
            httpClient.close();
        }
        return jsonObject;
    }

    private JsonObject deseralize(final CloseableHttpResponse httpResponse) throws IOException
    {
        JsonObject jObject = null;
                try {
                    String responseStr =  EntityUtils.toString(httpResponse.getEntity());
                    if (responseStr != null && !responseStr.isEmpty()) {
                        jObject=  new JsonParser().parse(responseStr).getAsJsonObject();
                    }
                } catch (IOException e) {
                }

            return jObject;
    }

    private void getConfiguration() 
	{ 
		//JSONParser parser = new JSONParser();
 try{
        this._apiKey = ReadConfigFile.getObject("apikey");
        this._apiUrl = ReadConfigFile.getObject("apiurl");
 }catch (IOException e) {
		
    e.printStackTrace();
  }
	
	}
}
