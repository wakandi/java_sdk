package ledgefarm.service;

import java.io.IOException;
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

import ledgefarm.exceptions.LedgefarmException;
import ledgefarm.models.GlobalToken;
import ledgefarm.models.Token;
import ledgefarm.models.Wallet;

public class OperatorService  extends LedgefarmService {
	public OperatorService(String accessKey) {
		super(accessKey);
	}
	
	public List<Token> get() throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "global/wallet");
		return this.mapToTokenObject(responseObject);
	}
	
	private List<Token> mapToTokenObject(JsonObject jsonObject) throws LedgefarmException {
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
		JsonObject object = jsonObject.getAsJsonObject("error");
		throw new LedgefarmException(object.get("message").getAsString(), object.get("error").getAsString());
	}
	
	public List<GlobalToken> ownedToken() throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "global/token/owned");
		return this.mapToGlobalTokenObject(responseObject);
	}
	
	public List<GlobalToken> issuedToken() throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "global/token/Issued");
		return this.mapToGlobalTokenObject(responseObject);
	}
	
	private List<GlobalToken> mapToGlobalTokenObject(JsonObject jsonObject) throws LedgefarmException {
		boolean success = jsonObject.get("success").getAsBoolean();
		if (success) {
			final ArrayList<GlobalToken> globalTokens = new ArrayList<>();
			JsonArray jarray = jsonObject.getAsJsonArray("data");
			for(JsonElement obj : jarray) {
				GlobalToken globalToken = gson.fromJson(obj, GlobalToken.class);      
				globalTokens.add(globalToken);
			}
			return globalTokens;
		}
		JsonObject object = jsonObject.getAsJsonObject("error");
		throw new LedgefarmException(object.get("message").getAsString(), object.get("error").getAsString());
	}
}
