package ledgefarm.models;

import java.util.*;

import com.google.gson.annotations.SerializedName;

public class Wallet {
	
	@SerializedName("wallet")
	private String WalletName;

	@SerializedName("accessKey")
	private String AccessKey;
	
	@SerializedName("blocked")
	private boolean Blocked;
	
	@SerializedName("tokens")
	private List<Token> Tokens;
	
	@SerializedName("name")
	private String Name;
	
	@SerializedName("email")
	private String Email;
	
	@SerializedName("countryCode")
	private String CountryCode;
	
	@SerializedName("phone")
	private String Phone;
	
	@SerializedName("isPublic")
	private String IsPublic;
	
	@SerializedName("operator")
	private Operator operator;

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public final String getWallet() {
		return WalletName;
	}

	public final void setWallet(String value) {
		WalletName = value;
	}

	public final String getAccessKey() {
		return AccessKey;
	}

	public final void setAccessKey(String value) {
		AccessKey = value;
	}
	
	public final boolean getBlocked() {
		return Blocked;
	}

	public final void setBlocked(boolean value) {
		Blocked = value;
	}

	public final List<Token> getTokens() {
		return Tokens;
	}

	public final void setTokens(List<Token> value) {
		Tokens = value;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getIsPublic() {
		return IsPublic;
	}

	public void setIsPublic(String isPublic) {
		IsPublic = isPublic;
	}
}