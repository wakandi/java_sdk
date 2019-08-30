package com.ledgefarm.core;

import java.util.*;

public class TransFee
{

        private Double Amount;
        public final Double getAmount()
	{
		return Amount;
	}
	public final void setAmount(Double value)
	{
		Amount = value;
    }
    
        private String Wallet;
        public final String getWallet()
	{
		return Wallet;
	}
	public final void setWallet(String value)
	{
		Wallet = value;
    }
    
        public String Msg;
        public final String getMsg()
	{
		return Msg;
	}
	public final void setMsg(String value)
	{
		Msg = value;
	}
}