package ledgefarm.service;

import java.io.IOException;
import java.net.URLEncoder;
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
import ledgefarm.models.*;

public class WalletService extends LedgefarmService {
	public WalletService(String accessKey) {
		super(accessKey);
	}
	
	public WalletService(String accessKey, String apiKey, String apiUrl, String certPath, String certPassphrase) {
		super(accessKey, apiKey, apiUrl, certPath, certPassphrase);
	}
	
	public Wallet create(WalletRequest walletRequest) throws IOException, LedgefarmException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("walletName", walletRequest.getWallet());
		dataObj.addProperty("name", walletRequest.getName());
		dataObj.addProperty("email", walletRequest.getEmail());
		dataObj.addProperty("countryCode", walletRequest.getCountryCode());
		dataObj.addProperty("phone", walletRequest.getPhone());
		dataObj.addProperty("isPublic", walletRequest.getIsPublic());
		JsonObject responseObject = super.sendHttpPost(dataObj, "wallet");
		return this.validateResponse(responseObject);

	}

	public List<Wallet> get(String wallet) throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "wallet/" + wallet);
		return this.mapToListObject(responseObject);
	}

	public List<Wallet> getAll(int limit, int offset) throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "wallet?limit=" + limit + "&offset=" + offset);
		return this.mapToListObject(responseObject);
	}
	
	public Wallet update(WalletRequest walletRequest) throws IOException, LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("wallet", walletRequest.getWallet());
		dataObj.addProperty("name", walletRequest.getName());
		dataObj.addProperty("email", walletRequest.getEmail());
		dataObj.addProperty("countryCode", walletRequest.getCountryCode());
		dataObj.addProperty("phone", walletRequest.getPhone());
		dataObj.addProperty("isPublic", walletRequest.getIsPublic());
		dataObj.addProperty("blocked", walletRequest.getBlocked());
		JsonObject responseObject = super.sendHttpPut(dataObj, "wallet");
		return  this.validateResponse(responseObject);
	}

	public List<Wallet> search(String search, String countryCode) throws IOException,LedgefarmException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject;
		if (countryCode != null) {
			responseObject = super.sendHttpGet(null, "wallet/search?search=" + search + "&countryCode=" + URLEncoder.encode(countryCode, "UTF-8"));	
		} else {
			responseObject = super.sendHttpGet(null, "wallet/search?search=" + search);
		}
		
		return this.mapToListObject(responseObject);
	}

	private Wallet validateResponse(JsonObject jsonObject) throws LedgefarmException {
		if (jsonObject != null) {
			boolean success = jsonObject.get("success").getAsBoolean();
			if (success) {
				JsonElement obj = gson.fromJson(jsonObject.getAsJsonObject("data").toString(), JsonElement.class);
				return gson.fromJson(obj, Wallet.class);
			}	
		} 
		throw new LedgefarmException("Service is unavailable", "SERVICE_UNAVAILABLE");
	}


	private List<Wallet> mapToListObject(JsonObject jsonObject) throws LedgefarmException {
		boolean success = jsonObject.get("success").getAsBoolean();
		if (success) {
			final ArrayList<Wallet> wallets = new ArrayList<>();
			JsonArray jarray = jsonObject.getAsJsonArray("data");
			for(JsonElement obj : jarray) {
				Wallet wallet = gson.fromJson(obj, Wallet.class);
				wallets.add(wallet);
			}
			return wallets;
		}
		return null;
	}
}