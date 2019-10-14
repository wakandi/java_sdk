package ledgefarm.models;

import com.google.gson.annotations.SerializedName;

public class GlobalToken {
	@SerializedName("amount")
	private Double Amount;
	
	public final Double getAmount() {
		return Amount;
	}
	
	public final void setAmount(Double amount) {
		Amount = amount;
	}
	
	@SerializedName("operatorName")
	private String OperatorName;

	public String getOperatorName() {
		return OperatorName;
	}

	public void setOperatorName(String operatorName) {
		OperatorName = operatorName;
	}
}
