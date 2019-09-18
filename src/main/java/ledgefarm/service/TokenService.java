package ledgefarm.service;

import com.google.gson.JsonObject;
import java.util.*;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import ledgefarm.exceptions.LedgefarmException;
import ledgefarm.models.*;

import com.google.gson.JsonArray;

public class TokenService extends LedgefarmService {
	public TokenService(String accessKey) {
		super(accessKey);
	}

	public Transaction issue(String toWallet, String token, double amount, List<Fee> fee)
			throws IOException, LedgefarmException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("toWallet", toWallet);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/issue");
		return this.mapToObject(responseObject);
	}

	public Transaction issue(String toWallet, String token, double amount, String transactionNumber, String paymentMode,
			List<Fee> fee) throws IOException, LedgefarmException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("toWallet", toWallet);
		dataObj.addProperty("transactionNumber", transactionNumber);
		dataObj.addProperty("paymentMode", paymentMode);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/issue");
		return this.mapToObject(responseObject);
	}

	public final Transaction transfer(String toWallet, String token, double amount, List<Fee> fee)
			throws IOException, LedgefarmException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("toWallet", toWallet);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/transfer");
		return this.mapToObject(responseObject);
	}

	public final Transaction withdraw(String fromWallet, String token, double amount, List<Fee> fee)
			throws IOException, LedgefarmException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("fromWallet", fromWallet);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/withdraw");
		return this.mapToObject(responseObject);
	}

	public final Transaction request(String fromWallet, String token, double amount)
			throws IOException, LedgefarmException {
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("amount", new Double(amount));
		dataObj.addProperty("token", token);
		dataObj.addProperty("fromWallet", fromWallet);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/request");
		return this.mapToObject(responseObject);
	}

	public Transaction accept(String tokenRequestId, List<Fee> fee) throws IOException, LedgefarmException {
		JsonElement jsonTree = new JsonParser().parse(super.gson.toJson(fee));
		JsonObject dataObj = new JsonObject();
		dataObj.add("fee", jsonTree);
		dataObj.addProperty("tokenRequestId", tokenRequestId);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/request/accept");
		return this.mapToObject(responseObject);
	}

	public Transaction reject(String tokenRequestId) throws IOException, LedgefarmException {
		JsonObject dataObj = new JsonObject();
		dataObj.addProperty("tokenRequestId", tokenRequestId);
		JsonObject responseObject = super.sendHttpPost(dataObj, "token/request/reject");
		return this.mapToObject(responseObject);
	}

	private Transaction mapToObject(JsonObject jsonObject) throws LedgefarmException {
		boolean success = jsonObject.get("success").getAsBoolean();
		if (success) {
			JsonObject object = jsonObject.getAsJsonObject("data");
			Transaction transaction = new Transaction();
			transaction.setTransactionId(object.get("transactionId").getAsString());
			transaction.setMessage(object.get("message").getAsString());
			return transaction;
		}
		JsonObject object = jsonObject.getAsJsonObject("error");
		throw new LedgefarmException(object.get("message").getAsString(), object.get("error").getAsString());
	}
}