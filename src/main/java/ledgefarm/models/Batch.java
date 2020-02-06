package ledgefarm.models;

import com.google.gson.annotations.SerializedName;

public class Batch {
	@SerializedName("referenceId")
	private String ReferenceId;

	public final String getReferenceId() {
		return ReferenceId;
	}

	public final void setReferenceId(String value) {
		ReferenceId = value;
	}
	
	@SerializedName("batchId")
	private String BatchId;

	public final String getBatchId() {
		return BatchId;
	}

	public final void setBatchId(String value) {
		BatchId = value;
	}
}
