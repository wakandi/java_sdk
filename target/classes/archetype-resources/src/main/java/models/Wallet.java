package com.ledgefarm.core;

import java.util.*;

public class Wallet
{
	private String Username;
	public final String getUsername()
	{
		return Username;
	}
	public final void setUsername(String value)
	{
		Username = value;
	}
	private String AccessToken;
	public final String getAccessToken()
	{
		return AccessToken;
	}
	public final void setAccessToken(String value)
	{
		AccessToken = value;
	}
	private boolean IsBlocked;
	public final boolean getIsBlocked()
	{
		return IsBlocked;
	}
	public final void setIsBlocked(boolean value)
	{
		IsBlocked = value;
	}
	private boolean BlockStatus;
	public final boolean getBlockStatus()
	{
		return BlockStatus;
	}
	public final void setBlockStatus(boolean value)
	{
		BlockStatus = value;
	}
	private List<Asset> Assets;
	public final List<Asset> getAssets()
	{
		return Assets;
	}
	public final void setAssets(List<Asset> value)
	{
		Assets = value;
	}
}