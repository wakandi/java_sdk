package com.ledgefarm.core;
import java.math.*;

public class Fee
{
	private String to;
	public final String getTo()
	{
		return to;
	}
	public final void setTo(String value)
	{
		to = value;
	}

	private Double amount = new Double(0);
	public final Double getAmount()
	{
		return amount;
	}
	public final void setAmount(Double value)
	{
		amount = value;
	}

	private String name;
	public final String getName()
	{
		return name;
	}
	public final void setName(String value)
	{
		name = value;
	}
}