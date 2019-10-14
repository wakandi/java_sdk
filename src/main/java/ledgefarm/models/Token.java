package ledgefarm.models;

import com.google.gson.annotations.SerializedName;

public class Token {
	@SerializedName("token")
	private String Name;

	public final String getName() {
		return Name;
	}

	public final void setName(String value) {
		Name = value;
	}
	@SerializedName("balance")
	private Double Balance;

	public final Double getBalance() {
		return Balance;
	}

	public final void setBalance(Double value) {
		Balance = value;
	}
	@SerializedName("netBalance")
	private Double Netbalance;

	public final Double getNetbalance() {
		return Netbalance;
	}

	public final void setNetbalance(Double value) {
		Netbalance = value;
	}

	@SerializedName("totalSupply")
	private Double TotalSupply;

	public final Double getTotalSupply() {
		return TotalSupply;
	}

	public final void setTotalSupply(Double value) {
		TotalSupply = value;
	}
}