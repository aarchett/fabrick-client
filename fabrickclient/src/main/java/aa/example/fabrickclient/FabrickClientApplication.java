package aa.example.fabrickclient;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FabrickClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabrickClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {

		return args -> {
//			Account account = new Account(14537780);
//
//			try {
//				Balance balance = account.getBalanceFromAPI(restTemplate);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			try {
//				List<Transaction> transactions = account.getTransactions(restTemplate, "2019-01-01", "2019-12-01");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			System.out.println("transferCash, started --------------------");
//			DestinationAccount creditorAccount = new DestinationAccount("IT23A0336844430152923804660");
//			creditorAccount.setBicCode("SELBIT2BXXX");
//			Creditor creditor = new Creditor("John Doe", creditorAccount);
//			Transfer transfer = new Transfer(creditor,"Payment invoice 75/2017", 800, "EUR");
//			try {
//				ResponseEntity<String> response = account.transferCash(transfer);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("transferCash, ended --------------------------------------");
//
//			try {
//				account.checkIban(restTemplate);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		};
	}

}
