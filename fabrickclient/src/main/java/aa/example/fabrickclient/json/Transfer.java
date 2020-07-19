package aa.example.fabrickclient.json;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transfer {

	private Creditor creditor;
	private String description;
	private int amount;
	private String currency;
	
	public Transfer(Creditor creditor, String description, int amount, String currency) {
		super();
		this.creditor = creditor;
		this.description = description;
		this.amount = amount;
		this.currency = currency;
	}
	
	public Creditor getCreditor() {
		return creditor;
	}
	public void setCreditor(Creditor creditor) {
		this.creditor = creditor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "Transfer [creditor=" + creditor + ", description=" + description
				+ ", amount=" + amount + ", currency=" + currency + "]";
	}
}
