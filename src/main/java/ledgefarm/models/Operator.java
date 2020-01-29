package ledgefarm.models;

import com.google.gson.annotations.SerializedName;

public class Operator {

	@SerializedName("country")
	private String Country;

	@SerializedName("domainName")
	private String DomainName;
	
	@SerializedName("logo")
	private String Logo;
	
	@SerializedName("organizationName")
	private String OrganizationName;

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getDomainName() {
		return DomainName;
	}

	public void setDomainName(String domainName) {
		DomainName = domainName;
	}
	
	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public String getOrganizationName() {
		return OrganizationName;
	}

	public void setOrganizationName(String organizationName) {
		OrganizationName = organizationName;
	}
}
