package com.ledgefarm.core;

import java.util.*;

public class Wallet
{
	private String WalletName;
	public final String getWalletName()
	{
		return WalletName;
	}
	public final void setWalletName(String value)
	{
		WalletName = value;
	}
	private String AccessKey;
	public final String getAccessKey()
	{
		return AccessKey;
	}
	public final void setAccessKey(String value)
	{
		AccessKey = value;
	}
	private boolean Blocked;
	public final boolean getBlocked()
	{
		return Blocked;
	}
	public final void setBlocked(boolean value)
	{
		Blocked = value;
	}
	
	private List<Token> Tokens;
	public final List<Token> getTokens()
	{
		return Tokens;
	}
	public final void setTokens(List<Token> value)
	{
		Tokens = value;
	}
}