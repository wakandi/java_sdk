package com.ledgefarm.core;

public class Transaction
{
	private String TransactionId;
	public final String getTransactionId()
	{
		return TransactionId;
	}
	public final void setTransactionId(String value)
	{
		TransactionId = value;
	}

	private String TokenRequestId;
	public final String getTokenRequestId()
	{
		return TokenRequestId;
	}
	public final void setTokenRequestId(String value)
	{
		TokenRequestId = value;
	}

	private String Message;
	public final String getMessage()
	{
		return Message;
	}
	public final void setMessage(String value)
	{
		Message = value;
	}

	private String Operation;
	public final String getOperation()
	{
		return Operation;
	}
	public final void Operation(String value)
	{
		Operation = value;
	}

	private Double Amount;

	public final String getAmount()
	{
		return Amount;
	}
	public final void setAmount(String value)
	{
		Amount = value;
	}

	private Date Timestamp;

	public final String getTimestamp()
	{
		return Timestamp;
	}
	public final void setTimestamp(Date value)
	{
		Timestamp = value;
	}

	private String ToWallet;

	public final String getToWallet()
	{
		return ToWallet;
	}
	public final void setToWallet(String value)
	{
		ToWallet = value;
	}

	private String Token;

	public final String getToken()
	{
		return Token;
	}
	public final void setToken(String value)
	{
		Token = value;
	}

	private String ParticipantInvoking;

	public final String getParticipantInvoking()
	{
		return ParticipantInvoking;
	}
	public final void setParticipantInvoking(String value)
	{
		ParticipantInvoking = value;
	}

	private String FromWallet;
	public final String getFromWallet()
	{
		return FromWallet;
	}
	public final void setFromWallet(String value)
	{
		FromWallet = value;
	}
	private List<Fee> Fees;
	public final List<Fee> getFees()
	{
		return Fees;
	}
	public final void setFees(List<Fee> value)
	{
		Fees = value;
	}
}