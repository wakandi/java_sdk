package ledgefarm.models;

import com.google.gson.annotations.SerializedName;

public class Fee {
	@SerializedName("toWallet")
	private String ToWallet;

	public final String getToWallet() {
		return ToWallet;
	}

	public final void setToWallet(String value) {
		ToWallet = value;
	}
	@SerializedName("amount")
	private Double Amount;

	public final Double getAmount() {
		return Amount;
	}

	public final void setAmount(Double value) {
		Amount = value;
	}
	@SerializedName("memo")
	private String Memo;

	public final String getMemo() {
		return Memo;
	}

	public final void setMemo(String value) {
		Memo = value;
	}
}