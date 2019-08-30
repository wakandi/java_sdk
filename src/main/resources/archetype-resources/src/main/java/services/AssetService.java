package com.ledgefarm.core;
import com.google.gson.JsonObject;
import java.util.*;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
public class AssetService extends LedgefarmService
{


	public AssetService(String token)
	{
		super(token);
	}

	public Transaction issue(String toUserId, String asset, double amount, List<Fee> fee)  throws IOException,LedgefarmException
	{
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount",new Double(amount));
		dataObj.addProperty("currency",asset);
		dataObj.add("fee",jsonTree);
		dataObj.addProperty("to",toUserId);
		JsonObject responseObject =  super.sendHttpPost(dataObj, "token/issue");
		return this.mapToObject(responseObject);		
	}

	public final Transaction transfer(String toUserId, String asset, double amount, List<Fee> fee)  throws IOException,LedgefarmException
	{
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount",new Double(amount));
		dataObj.addProperty("currency",asset);
		dataObj.add("fee",jsonTree);
		dataObj.addProperty("to",toUserId);
		JsonObject responseObject =  super.sendHttpPost(dataObj, "token/transfer");
		return this.mapToObject(responseObject);
	}

	public final Transaction withdraw(String fromUserId, String asset, double amount, List<Fee> fee)  throws IOException,LedgefarmException
	{
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount",new Double(amount));
		dataObj.addProperty("currency",asset);
		dataObj.add("fee",jsonTree);
		dataObj.addProperty("from",fromUserId);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/withdraw");
		return this.mapToObject(responseObject);
	}

	public final Transaction request(String fromUserId, String asset, double amount)  throws IOException,LedgefarmException
	{
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount",new Double(amount));
		dataObj.addProperty("currency",asset);
		dataObj.addProperty("from",fromUserId);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/request");
		boolean success =  responseObject.get("success").getAsBoolean();
		if(success){
		JsonObject object  = responseObject.getAsJsonObject("data");
		Transaction transaction = new Transaction();
		transaction.setMessage(object.get("consent").getAsString());
		return transaction;
		}
		JsonObject object  = responseObject.getAsJsonObject("error");
		throw new LedgefarmException(object.get("message").getAsString(),object.get("errorCode").getAsString());
	}
	public List<Asset> get() throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "asset/supply");
		return this.mapToListObject(responseObject);
	}

	public List<Asset> get(String assetName) throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "asset/supply/" + assetName);
		return this.mapToListObject(responseObject);
	}


	private Transaction mapToObject(JsonObject jsonObject) throws LedgefarmException
	{
		boolean success =  jsonObject.get("success").getAsBoolean();
		if(success){
		JsonObject object  = jsonObject.getAsJsonObject("data");
		Transaction transaction = new Transaction();
		transaction.setTransactionId(object.get("transactionId").getAsString());
		transaction.setMessage(object.get("message").getAsString());
		return transaction;
		}
		JsonObject object  = jsonObject.getAsJsonObject("error");
		throw new LedgefarmException(object.get("message").getAsString(),object.get("errorCode").getAsString());
	  }

	private List<Asset> mapToListObject(JsonObject jsonObject)  throws LedgefarmException
	{
		boolean success =  jsonObject.get("success").getAsBoolean();
		if(success)
		{
		final List<Asset> assets = new ArrayList<>();
	   JsonArray jarray = jsonObject.getAsJsonArray("data");
	   for (int i = 0; i < jarray.size(); i++) {
		JsonObject object = jarray.get(i).getAsJsonObject();
		Asset asset = new Asset();
		asset.setName(object.get("assetName").getAsString());
		asset.setBalance(object.get("totalSupply").getAsDouble());
		assets.add(asset);
		   }
		
	   	return assets;
		}
	JsonObject object  = jsonObject.getAsJsonObject("error");
	throw new LedgefarmException(object.get("message").getAsString(),object.get("errorCode").getAsString());
	}
}