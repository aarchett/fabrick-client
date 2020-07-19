package aa.example.fabrickclient.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DestinationAccount {

	private String iban;
	private String bicCode;
	
	public DestinationAccount(String iban) {
		this.iban = iban;
	}
	
	@JsonProperty("accountCode")
	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	
}
