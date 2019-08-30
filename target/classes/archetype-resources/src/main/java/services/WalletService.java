package com.ledgefarm.core;

import java.io.IOException;
import java.util.*;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonArray;
public class WalletService extends LedgefarmService
{
	public WalletService(String token)
	{
		super(token);
	}

	public Wallet create(String username) throws IOException,LedgefarmException
	{
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("username",username);
		JsonObject responseObject = super.sendHttpPost(dataObj, "wallet");
		JsonObject object  = this.validateResponse(responseObject);
		Wallet wallet = new Wallet();
		wallet.setAccessToken(object.get("accesstoken").getAsString());
		wallet.setUsername(object.get("username").getAsString());
		return wallet;
	}

	public List<Wallet> get(String username) throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "user/" + username);
		return this.mapToListObject(responseObject);
	}

	public List<Wallet> getAll(int limit, int offset) throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "user?limit=" + limit + "&offset=" + offset);
		return this.mapToListObject(responseObject);
	}

	public Wallet block(String userId) throws IOException,LedgefarmException
	{
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("username",userId);
		dataObj.addProperty("blockStatus",new Boolean(true));
		JsonObject responseObject = super.sendHttpPut(dataObj, "wallet");
		JsonObject object  = this.validateResponse(responseObject);
		Wallet wallet = new Wallet();
		wallet.setIsBlocked(object.get("blockStatus").getAsBoolean());
		return wallet;
	}
	public Wallet unblock(String userId) throws IOException,LedgefarmException
	{
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("username",userId);
		dataObj.addProperty("blockStatus",new Boolean(false));
		JsonObject responseObject = super.sendHttpPut(dataObj, "wallet");
		JsonObject object  = this.validateResponse(responseObject);
		Wallet wallet = new Wallet();
		wallet.setIsBlocked(object.get("blockStatus").getAsBoolean());
		return wallet;
	}
	private JsonObject validateResponse(JsonObject jsonObject) throws LedgefarmException
	{
		boolean success =  jsonObject.get("success").getAsBoolean();
		if(success)
		{
			return jsonObject.getAsJsonObject("data");
		}
		JsonObject object  = jsonObject.getAsJsonObject("error");
		throw new LedgefarmException(object.get("message").getAsString());
	}

	private List<Wallet> mapToListObject(JsonObject jsonObject)  throws LedgefarmException
	{
		boolean success =  jsonObject.get("success").getAsBoolean();
		if(success)
		{
		final ArrayList<Wallet> wallets = new ArrayList<>();
		
	   JsonArray jarray = jsonObject.getAsJsonArray("data");
	   for (int i = 0; i < jarray.size(); i++) {
		final ArrayList<Asset> assets = new ArrayList<>();
		JsonObject object = jarray.get(i).getAsJsonObject();
		Wallet wallet = new Wallet();
		wallet.setIsBlocked(object.get("isBlocked").getAsBoolean());
		wallet.setUsername(object.get("username").getAsString());
		JsonArray assetArray = object.getAsJsonArray("tokens");
		for(int j=0;j<assetArray.size();j++)
		{
			JsonObject feeobject = assetArray.get(i).getAsJsonObject();
			Asset asset = new Asset();
			asset.setName(feeobject.get("asset").getAsString());
			asset.setBalance(feeobject.get("balance").getAsDouble());
			asset.setNetbalance(feeobject.get("netBalance").getAsDouble());
			assets.add(asset);
		}
		wallet.setAssets(assets);
		wallets.add(wallet);
	   	}
		JsonObject jobject = jarray.get(0).getAsJsonObject();
	   	return wallets;
		}
	JsonObject object  = jsonObject.getAsJsonObject("error");
	throw new LedgefarmException(object.get("message").getAsString(),object.get("errorCode").getAsString());
	}
}