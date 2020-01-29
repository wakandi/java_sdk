package ledgefarm.models;

import com.google.gson.annotations.SerializedName;

public class BatchTransferRequest {

	@SerializedName("amount")
	private double Amount;

	@SerializedName("toWallet")
	private String ToWallet;
	
	@SerializedName("memo")
	private String Memo;

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public String getToWallet() {
		return ToWallet;
	}

	public void setToWallet(String toWallet) {
		ToWallet = toWallet;
	}

	public String getMemo() {
		return Memo;
	}

	public void setMemo(String memo) {
		Memo = memo;
	}
}
