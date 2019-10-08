package ledgefarm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ledgefarm.utils.ReadConfigFile;

public class LedgefarmService {

	private String _accessKey;
	private String _apiKey;
	private String _apiUrl;
	private String _certPassphrase;
	private String _certPath;
	Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

	public LedgefarmService(String accessKey) {
		this._accessKey = accessKey;
		this.getConfiguration();
	}

	public JsonObject sendHttpPost(JsonObject object, String requestUrl) throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, KeyManagementException, UnrecoverableKeyException {
		 
	      //Building the CloseableHttpClient
	      CloseableHttpClient httpclient = getHttpClient();
	      
		CloseableHttpResponse execute = null;
		JsonObject jsonObject = null;
		String requestJson = new Gson().toJson(object);

		try {
			HttpPost httpPost = new HttpPost(this._apiUrl + requestUrl);
			StringEntity entity = new StringEntity(requestJson, "UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			httpPost.setHeader("apiKey", this._apiKey);
			httpPost.setHeader("accessKey", this._accessKey);
			execute = httpclient.execute(httpPost);
			jsonObject = this.deseralize(execute);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			httpclient.close();
		}
		return jsonObject;
	}
	
	public JsonObject sendFileHttpPost(HttpEntity object, String requestUrl) throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException, KeyManagementException, UnrecoverableKeyException {
		 
	      //Building the CloseableHttpClient
	      CloseableHttpClient httpclient = getHttpClient();
	      
		CloseableHttpResponse execute = null;
		JsonObject jsonObject = null;
		try {
			HttpPost httpPost = new HttpPost(this._apiUrl + requestUrl);
			httpPost.setEntity(object);
			httpPost.setHeader("apiKey", this._apiKey);
			httpPost.setHeader("accessKey", this._accessKey);
			execute = httpclient.execute(httpPost);
			jsonObject = this.deseralize(execute);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			httpclient.close();
		}
		return jsonObject;
	}

	public JsonObject sendHttpPut(JsonObject object, String requestUrl) throws IOException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
		CloseableHttpResponse execute = null;
		JsonObject jsonObject = null;
		String requestJson = "{}";
		if(object!=null)
		{
			requestJson = new Gson().toJson(object);
		}
		CloseableHttpClient httpclient =  getHttpClient();
		try {
			HttpPut httpPut = new HttpPut(this._apiUrl + requestUrl);
			StringEntity entity = new StringEntity(requestJson, "UTF-8");
			entity.setContentType("application/json");
			httpPut.setEntity(entity);
			httpPut.setHeader("apiKey", this._apiKey);
			httpPut.setHeader("accessKey", this._accessKey);
			execute = httpclient.execute(httpPut);
			jsonObject = this.deseralize(execute);
		} catch (IOException e) {
		} finally {
			httpclient.close();
		}
		return jsonObject;
	}

	public JsonObject sendHttpGet(JsonObject object, String requestUrl) throws IOException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
		CloseableHttpResponse execute = null;
		JsonObject jsonObject = null;
		CloseableHttpClient httpClient =  getHttpClient();
		try {
			HttpGet httpGet = new HttpGet(this._apiUrl + requestUrl);
			httpGet.setHeader("apiKey", this._apiKey);
			httpGet.setHeader("accessKey", this._accessKey);
			execute = httpClient.execute(httpGet);
			jsonObject = this.deseralize(execute);
		} catch (IOException e) {
		} finally {
			httpClient.close();
		}
		return jsonObject;
	}

	public JsonArray sendFileHttpGet(JsonObject object, String requestUrl) throws IOException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
		CloseableHttpResponse execute = null;
		JsonArray jsonObject = null;
		CloseableHttpClient httpClient =  getHttpClient();
		try {
			HttpGet httpGet = new HttpGet(this._apiUrl + requestUrl);
			httpGet.setHeader("apiKey", this._apiKey);
			httpGet.setHeader("accessKey", this._accessKey);
			execute = httpClient.execute(httpGet);
			String responseStr = EntityUtils.toString(execute.getEntity());
			return new JsonParser().parse(responseStr).getAsJsonArray();
		} catch (IOException e) {
		} finally {
			httpClient.close();
		}
		return jsonObject;
	}
	
	
	private JsonObject deseralize(final CloseableHttpResponse httpResponse) throws IOException {
		JsonObject jObject = null;
		try {
			String responseStr = EntityUtils.toString(httpResponse.getEntity());
			if (responseStr != null && !responseStr.isEmpty()) {
				jObject = new JsonParser().parse(responseStr).getAsJsonObject();
			}
		} catch (IOException e) {
		}

		return jObject;
	}

	private void getConfiguration() {
		try {
			ReadConfigFile readConfigFile = new ReadConfigFile();
			this._apiKey = readConfigFile.getObject("apikey");
			this._apiUrl = readConfigFile.getObject("apiurl");
			this._certPassphrase= readConfigFile.getObject("certPassphrase");
			this._certPath= readConfigFile.getObject("certPath");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	private CloseableHttpClient getHttpClient() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException {
		
		KeyStore trustStore  = KeyStore.getInstance("PKCS12");

    	FileInputStream instream = new FileInputStream(new File(Paths.get(System.getProperty("user.dir"),this._certPath).toString()));
        try {
        	trustStore.load(instream, this._certPassphrase.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
        		.loadKeyMaterial(trustStore, this._certPassphrase.toCharArray())
                .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                .build();

        // Allow both TLSv1, TLSv2 protocols
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1", "TLSv1.2" },
                null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        CloseableHttpClient httpClient = HttpClients.custom()
        		.setSSLSocketFactory(sslsf)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).
                setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
                {
                    public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
                    {
                        return true;
                    }
                }).build()).build();
        
        
        
		
		return httpClient;
		
	}
}
