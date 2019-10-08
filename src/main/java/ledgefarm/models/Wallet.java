package ledgefarm.models;

import java.util.*;

import com.google.gson.annotations.SerializedName;

public class Wallet {
	
	 @SerializedName("wallet")
	private String WalletName;

	public final String getWallet() {
		return WalletName;
	}

	public final void setWallet(String value) {
		WalletName = value;
	}
	@SerializedName("accessKey")
	private String AccessKey;

	public final String getAccessKey() {
		return AccessKey;
	}

	public final void setAccessKey(String value) {
		AccessKey = value;
	}
	 @SerializedName("blocked")
	private boolean Blocked;

	public final boolean getBlocked() {
		return Blocked;
	}

	public final void setBlocked(boolean value) {
		Blocked = value;
	}

	 @SerializedName("tokens")
	private List<Token> Tokens;

	public final List<Token> getTokens() {
		return Tokens;
	}

	public final void setTokens(List<Token> value) {
		Tokens = value;
	}
}