package com.ledgefarm.core;
import java.text.ParseException;  
import java.text.SimpleDateFormat;
import com.google.gson.JsonObject;
import java.util.*;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;  
public class TransactionService extends LedgefarmService
{
	public TransactionService(String token)
	{
		super(token);
	}
	public List<Transaction> getAll(int limit, int offset) throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "transaction?limit=" + limit + "&offset=" + offset);
		return this.mapToListObject(responseObject);
	}
	
	public List<Transaction> getByWallet(String wallet, int limit, int offset) throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "transaction?limit=" + limit + "&offset=" + offset + "&wallet=" +wallet);
		return this.mapToListObject(responseObject);
	}
	public List<Transaction> getByToken(String token, int limit, int offset) throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "transaction?limit=" + limit + "&offset=" + offset + "&token=" +token);
		return this.mapToListObject(responseObject);
	}
	
	public List<Transaction> get(String transactionId) throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "transaction?id=" + transactionId);
		return this.mapToListObject(responseObject);
	}

	private List<Transaction> mapToListObject(JsonObject jsonObject)  throws LedgefarmException
	{
		boolean success =  jsonObject.get("success").getAsBoolean();
		if(success)
		{
		final ArrayList<Transaction> transactions = new ArrayList<>();
		
	   JsonArray jarray = jsonObject.getAsJsonArray("data");
	   for (int i = 0; i < jarray.size(); i++) {
		JsonObject object = jarray.get(i).getAsJsonObject();
		Transaction transaction = new Transaction();
		final ArrayList<Fee> transfees = new ArrayList<>();

		transaction.setTransactionId(object.get("transactionId").getAsString());
		//transaction.setTokenTransactionId(object.get("tokenTransactionId").getAsString());
		transaction.setOperation(object.get("operation").getAsString());
		transaction.setAmount(object.get("amount").getAsDouble());

		String dateStr = object.get("timestamp").getAsString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date birthDate = sdf.parse(dateStr);
		transaction.setTimestamp(birthDate);

		//transaction.setTimestamp(object.get("isBlocked").getAsString());
		transaction.setToWallet(object.get("toWallet").getAsString());
		transaction.setToken(object.get("token").getAsString());
		transaction.setParticipantInvoking(object.get("participantInvoking").getAsString());
		transaction.setFromWallet(object.get("fromWallet").getAsString());
		JsonArray assetArray = object.getAsJsonArray("fee");
		for(int j=0;j<assetArray.size();j++)
		{
			JsonObject feeobject = assetArray.get(i).getAsJsonObject();
			Fee transfee = new Fee();
			transfee.setAmount(feeobject.get("amount").getAsDouble());
			transfee.setWallet(feeobject.get("wallet").getAsString());
			transfee.setMemo(feeobject.get("memo").getAsString());
			transfees.add(asset);
		}
		transaction.setAssets(assets);
		transactions.add(transaction);
	   	}
	   	return transactions;
		}
	JsonObject object  = jsonObject.getAsJsonObject("error");
	throw new LedgefarmException(object.get("message").getAsString(),object.get("error").getAsString());
	}

}