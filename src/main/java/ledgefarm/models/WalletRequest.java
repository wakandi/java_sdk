package ledgefarm.models;

public class WalletRequest {
	
	private String Wallet;
	private boolean Blocked;
	private String Name;
	private String Email;
	private String CountryCode;
	private String Phone;
	private boolean IsPublic;

	public final String getWallet() {
		return Wallet;
	}

	public final void setWallet(String value) {
		Wallet = value;
	}
	
	public final boolean getBlocked() {
		return Blocked;
	}

	public final void setBlocked(boolean value) {
		Blocked = value;
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

	public boolean getIsPublic() {
		return IsPublic;
	}

	public void setIsPublic(boolean isPublic) {
		IsPublic = isPublic;
	}
}
