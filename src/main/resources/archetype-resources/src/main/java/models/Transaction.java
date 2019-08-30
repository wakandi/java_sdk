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
	private String Message;
	public final String getMessage()
	{
		return Message;
	}
	public final void setMessage(String value)
	{
		Message = value;
	}

	private String OperationName;
	public final String getOperationName()
	{
		return OperationName;
	}
	public final void setOperationName(String value)
	{
		OperationName = value;
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

	private String To;

	public final String getTo()
	{
		return To;
	}
	public final void setTo(String value)
	{
		To = value;
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

	private String From;
	public final String getFrom()
	{
		return From;
	}
	public final void setFrom(String value)
	{
		From = value;
	}
	private List<TransFee> Fees;
	public final List<TransFee> getFees()
	{
		return Fees;
	}
	public final void setFees(List<TransFee> value)
	{
		Fees = value;
	}
}