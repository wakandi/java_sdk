package ledgefarm.models;

public class Token {
	private String Name;

	public final String getName() {
		return Name;
	}

	public final void setName(String value) {
		Name = value;
	}

	private Double Balance;

	public final Double getBalance() {
		return Balance;
	}

	public final void setBalance(Double value) {
		Balance = value;
	}

	private Double Netbalance;

	public final Double getNetbalance() {
		return Netbalance;
	}

	public final void setNetbalance(Double value) {
		Netbalance = value;
	}

}