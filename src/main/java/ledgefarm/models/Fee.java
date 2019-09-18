package ledgefarm.models;

public class Fee {
	private String ToWallet;

	public final String getToWallet() {
		return ToWallet;
	}

	public final void setToWallet(String value) {
		ToWallet = value;
	}

	private Double Amount = new Double(0);

	public final Double getAmount() {
		return Amount;
	}

	public final void setAmount(Double value) {
		Amount = value;
	}

	private String Memo;

	public final String getMemo() {
		return Memo;
	}

	public final void setMemo(String value) {
		Memo = value;
	}
}