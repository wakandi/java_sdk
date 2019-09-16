package com.ledgefarm.core;

import java.io.IOException;
import java.util.*;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonArray;
public class WalletService extends LedgefarmService
{
	public WalletService(String accessKey)
	{
		super(accessKey);
	}

	public Wallet create(String walletName) throws IOException,LedgefarmException
	{
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("walletName",walletName);
		JsonObject responseObject = super.sendHttpPost(dataObj, "wallet");
		JsonObject object  = this.validateResponse(responseObject);
		Wallet wallet = new Wallet();
		wallet.setAccessKey(object.get("accessKey").getAsString());
		wallet.setWalletName(object.get("walletName").getAsString());
		return wallet;
	}

	public List<Wallet> get(String walletName) throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "user/" + walletName);
		return this.mapToListObject(responseObject);
	}

	public List<Wallet> getAll(int limit, int offset) throws IOException,LedgefarmException
	{
		JsonObject responseObject = super.sendHttpGet(null, "user?limit=" + limit + "&offset=" + offset);
		return this.mapToListObject(responseObject);
	}

	public Wallet block(String walletName) throws IOException,LedgefarmException
	{
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("walletName",walletName);
		dataObj.addProperty("blocked",new Boolean(true));
		JsonObject responseObject = super.sendHttpPut(dataObj, "wallet");
		JsonObject object  = this.validateResponse(responseObject);
		Wallet wallet = new Wallet();
		wallet.setBlocked(object.get("blocked").getAsBoolean());
		return wallet;
	}
	public Wallet unblock(String walletName) throws IOException,LedgefarmException
	{
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("walletName",walletName);
		dataObj.addProperty("blocked",new Boolean(false));
		JsonObject responseObject = super.sendHttpPut(dataObj, "wallet");
		JsonObject object  = this.validateResponse(responseObject);
		Wallet wallet = new Wallet();
		wallet.setBlocked(object.get("blocked").getAsBoolean());
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
		final ArrayList<Token> tokens = new ArrayList<>();
		JsonObject object = jarray.get(i).getAsJsonObject();
		Wallet wallet = new Wallet();
		wallet.setBlocked(object.get("blocked").getAsBoolean());
		wallet.setWalletName(object.get("walletName").getAsString());
		JsonArray assetArray = object.getAsJsonArray("tokens");
		for(int j=0;j<assetArray.size();j++)
		{
			JsonObject feeobject = assetArray.get(i).getAsJsonObject();
			Token token = new Token();
			token.setName(feeobject.get("token").getAsString());
			token.setBalance(feeobject.get("balance").getAsDouble());
			token.setNetbalance(feeobject.get("netBalance").getAsDouble());
			tokens.add(token);
		}
		wallet.setTokens(tokens);
		wallets.add(wallet);
	   	}
		JsonObject jobject = jarray.get(0).getAsJsonObject();
	   	return wallets;
		}
	JsonObject object  = jsonObject.getAsJsonObject("error");
	throw new LedgefarmException(object.get("message").getAsString(),object.get("error").getAsString());
	}
}