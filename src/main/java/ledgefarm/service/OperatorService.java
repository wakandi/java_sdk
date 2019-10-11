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
import ledgefarm.models.Token;
import ledgefarm.models.Wallet;

public class OperatorService  extends LedgefarmService {
	public OperatorService(String accessKey) {
		super(accessKey);
	}
	
	public List<Token> get(String token) throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
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
	
	public List<Token> getTokenOwned() throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "global/token/owned");
		return this.mapToTokenObject(responseObject);
	}
	
	public List<Token> getTokenIssued() throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "global/token/Issued");
		return this.mapToTokenObject(responseObject);
	}
}
