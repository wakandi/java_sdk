package ledgefarm.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ledgefarm.exceptions.LedgefarmException;
import ledgefarm.models.Transaction;

public class TransactionService extends LedgefarmService {
	public TransactionService(String token) {
		super(token);
	}
	
	public TransactionService(String accessKey, String apiKey, String apiUrl, String certPath, String certPassphrase) {
		super(accessKey, apiKey, apiUrl, certPath, certPassphrase);
	}
	
	public List<Transaction> getAll(int limit, int offset) throws IOException,LedgefarmException, ParseException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "transaction?limit=" + limit + "&offset=" + offset);
		return this.mapToListObject(responseObject);
	}

	public List<Transaction> getAllByWallet(String wallet, int limit, int offset)
			throws IOException, LedgefarmException, ParseException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
		JsonObject responseObject = super.sendHttpGet(null,
				"transaction?limit=" + limit + "&offset=" + offset + "&wallet=" + wallet);
		return this.mapToListObject(responseObject);
	}

	public List<Transaction> getAllByToken(String token, int limit, int offset)
			throws IOException,LedgefarmException, ParseException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null,
				"transaction?limit=" + limit + "&offset=" + offset + "&token=" + token);
		return this.mapToListObject(responseObject);
	}

	public List<Transaction> get(String transactionId) throws IOException,LedgefarmException, ParseException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException  {
		JsonObject responseObject = super.sendHttpGet(null, "transaction?id=" + transactionId);
		return this.mapToListObject(responseObject);
	}

	private List<Transaction> mapToListObject(JsonObject jsonObject) throws LedgefarmException, ParseException {
		boolean success = jsonObject.get("success").getAsBoolean();
		if (success) {
			final ArrayList<Transaction> transactions = new ArrayList<>();
			JsonArray jarray = jsonObject.getAsJsonArray("data");
			for(JsonElement obj : jarray) {
			      Transaction transaction = gson.fromJson(obj, Transaction.class);      
			      transactions.add(transaction);
			    }
			return transactions;
		}
		return null;
	}

}