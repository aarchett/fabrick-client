package aa.example.fabrickclient;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;

import aa.example.fabrickclient.json.Balance;
import aa.example.fabrickclient.json.Creditor;
import aa.example.fabrickclient.json.DestinationAccount;
import aa.example.fabrickclient.json.Transaction;
import aa.example.fabrickclient.json.Transfer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FabrickclientApplicationTests {
	
	private Account account;

	@BeforeEach
	public void init() {
		account = new Account(14537780);
	}
	
	@AfterEach
	public void cleanUp() {
		account = null;
	}

	@Test
	void testGetBalanceFromAPI() {
		System.out.println("testGetBalanceFromAPI, started --------------------");
		Balance balance = null;
		try {
			balance = account.getBalanceFromAPI();
			assertTrue(balance.getAvailableBalance() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(balance);
		assertTrue(balance.getAvailableBalance() > 0);
		System.out.println("testGetBalanceFromAPI, ended --------------------------------------");
	}

	@Test
	void testGetTransactions() {
		System.out.println("testGetTransactions, started --------------------");
		List<Transaction> transactions = null;
		try {
			transactions = account.getTransactions("2019-01-01","2019-12-01");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(transactions);
		assertTrue(transactions.size() > 0);
		System.out.println("testGetTransactions, ended --------------------------------------");
	}
	
	@Test
	void testCheckIban() {
		System.out.println("testCheckIban, started --------------------");
		ResponseEntity<String> response = null;
		try {
			response = account.checkIban();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(response.getStatusCode().is2xxSuccessful());
		System.out.println("testCheckIban, ended --------------------------------------");
	}
	
	@Test
	void testTransferCash() {
		System.out.println("testTransferCash, started --------------------");
		DestinationAccount creditorAccount = new DestinationAccount("IT23A0336844430152923804660");
		Creditor creditor = new Creditor("John Doe", creditorAccount);
		Transfer transfer = new Transfer(creditor,"Payment invoice 75/2017", 800, "EUR");
		ResponseEntity<String> response = null;
		try {
			response = account.transferCash(transfer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(response);
		assertTrue(response.getStatusCode().isError());
		System.out.println("testTransferCash, ended --------------------------------------");
	}
	

}
