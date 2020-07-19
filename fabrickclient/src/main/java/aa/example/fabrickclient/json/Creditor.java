package aa.example.fabrickclient.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Creditor {
	private String name;
	private DestinationAccount account;
	
	public Creditor(String name, DestinationAccount account) {
		super();
		this.name = name;
		this.account = account;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DestinationAccount getAccount() {
		return account;
	}
	public void setAccount(DestinationAccount account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "Creditor [name=" + name + ", account=" + account + "]";
	}
}
