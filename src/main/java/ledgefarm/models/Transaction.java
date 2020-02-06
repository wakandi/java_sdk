package ledgefarm.models;

import java.util.*;

import com.google.gson.annotations.SerializedName;

public class Transaction {
	 @SerializedName("transactionId")
	private String TransactionId;

	public final String getTransactionId() {
		return TransactionId;
	}

	public final void setTransactionId(String value) {
		TransactionId = value;
	}
	 @SerializedName("tokenRequestId")
	private String TokenRequestId;

	public final String getTokenRequestId() {
		return TokenRequestId;
	}

	public final void setTokenRequestId(String value) {
		TokenRequestId = value;
	}
	 @SerializedName("message")
	private String Message;

	public final String getMessage() {
		return Message;
	}

	public final void setMessage(String value) {
		Message = value;
	}
	@SerializedName("operation")
	private String Operation;

	public final String getOperation() {
		return Operation;
	}

	public final void setOperation(String value) {
		Operation = value;
	}
	@SerializedName("amount")
	private Double Amount;

	public final Double getAmount() {
		return Amount;
	}

	public final void setAmount(Double value) {
		Amount = value;
	}
	@SerializedName("timestamp")
	private Date Timestamp;

	public final Date getTimestamp() {
		return Timestamp;
	}

	public final void setTimestamp(Date value) {
		Timestamp = value;
	}
	@SerializedName("toWallet")
	private String ToWallet;

	public final String getToWallet() {
		return ToWallet;
	}

	public final void setToWallet(String value) {
		ToWallet = value;
	}
	@SerializedName("token")
	private String Token;

	public final String getToken() {
		return Token;
	}

	public final void setToken(String value) {
		Token = value;
	}
	@SerializedName("participantInvoking")
	private String ParticipantInvoking;

	public final String getParticipantInvoking() {
		return ParticipantInvoking;
	}

	public final void setParticipantInvoking(String value) {
		ParticipantInvoking = value;
	}
	@SerializedName("fromWallet")
	private String FromWallet;

	public final String getFromWallet() {
		return FromWallet;
	}

	public final void setFromWallet(String value) {
		FromWallet = value;
	}

	@SerializedName("fees")
	private List<Fee> Fees;

	public final List<Fee> getFees() {
		return Fees;
	}

	public final void setFees(List<Fee> value) {
		Fees = value;
	}
}