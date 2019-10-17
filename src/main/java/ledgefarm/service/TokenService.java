package ledgefarm.service;

import java.io.IOException;
import java.security.KeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ledgefarm.exceptions.LedgefarmException;
import ledgefarm.models.*;

public class TokenService extends LedgefarmService {
	public TokenService(String accessKey) {
		super(accessKey);
	}
	
	public TokenService(String accessKey, String apiKey, String apiUrl, String certPath, String certPassphrase) {
		super(accessKey, apiKey, apiUrl, certPath, certPassphrase);
	}
	
	public Transaction issue(String toWallet, String token, double amount, List<Fee> fee)
			throws IOException, LedgefarmException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("toWallet", toWallet); 
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/issue");
		return this.mapToTransactionObject(responseObject);
	}

	public Transaction issue(String toWallet, String token, double amount, String transactionId,
			List<Fee> fee) throws IOException, LedgefarmException, KeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("toWallet", toWallet);
		dataObj.addProperty("transactionId", transactionId);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/issue");
		return this.mapToTransactionObject(responseObject);
	}

	public final Transaction transfer(String toWallet, String token, double amount, List<Fee> fee)
			throws IOException, LedgefarmException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("toWallet", toWallet);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/transfer");
		return this.mapToTransactionObject(responseObject);
	}

	public final Transaction withdraw(String fromWallet, String token, double amount, List<Fee> fee)
			throws IOException, LedgefarmException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("fromWallet", fromWallet);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/withdraw");
		return this.mapToTransactionObject(responseObject);
	}

	public final Transaction request(String fromWallet, String token, double amount)
			throws IOException, LedgefarmException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.addProperty("fromWallet", fromWallet);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/request");
		return this.mapToTransactionObject(responseObject);
	}

	public Transaction accept(String tokenRequestId, List<Fee> fee) 
			throws IOException, LedgefarmException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("tokenRequestId", tokenRequestId);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/request/accept");
		return this.mapToTransactionObject(responseObject);
	}

	public Transaction reject(String tokenRequestId) 
			throws IOException, LedgefarmException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("tokenRequestId", tokenRequestId);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/request/reject");
		return this.mapToTransactionObject(responseObject);
	}
	
	public Transaction feeSettle() throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpPut(null,"token/credit/settle");
		return this.mapToTransactionObject(responseObject);
	}

	public List<Token> get(String token) throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "token/"+token);
		return this.mapToTokenObject(responseObject);
	}
	
	private List<Token>  mapToTokenObject(JsonObject jsonObject) throws LedgefarmException {
		boolean success = jsonObject.get("success").getAsBoolean();
		if (success) {
			final ArrayList<Token> tokens = new ArrayList<>();
			JsonArray jarray = jsonObject.getAsJsonArray("data");
			for(JsonElement obj : jarray) {
			      Token token = gson.fromJson(obj, Token.class);      
			      tokens.add(token);
			    }
			return tokens;
		}
		return null;
	}
	
	private Transaction mapToTransactionObject(JsonObject jsonObject) throws LedgefarmException {
		boolean success = jsonObject.get("success").getAsBoolean();
		if (success) {
			JsonElement obj = gson.fromJson(jsonObject.getAsJsonObject("data").toString(), JsonElement.class);
			return gson.fromJson(obj, Transaction.class);
		}
		return null;
	}
}