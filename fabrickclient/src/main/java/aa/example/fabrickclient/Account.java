package aa.example.fabrickclient;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import aa.example.fabrickclient.json.Balance;
import aa.example.fabrickclient.json.Transaction;
import aa.example.fabrickclient.json.Transfer;

public class Account {

	private static final String HOST = "https://sandbox.platfr.io/";
	private int accountId;
	
	Logger logger = LoggerFactory.getLogger(Account.class);
	
	public Account (int accountId) {
		this.accountId = accountId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	private Balance getBalanceFromAPI(RestTemplate restTemplate)
			throws JsonProcessingException, JsonMappingException {
		// make an HTTP GET request with headers
		String uri = HOST
				+ "api/gbs/banking/v4.0/accounts/"
				+ getAccountId()
				+ "/balance";
		
	    HttpEntity<String> request = Utils.getHttpEntity();
	    logger.info(request.toString());
	    
		//RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
	    Balance balance = null;
		if (result.getStatusCode() == HttpStatus.OK) {
			logger.info("Request Successful.");
			logger.info(result.getBody());
		    ObjectMapper mapper = new ObjectMapper();
		    JsonNode tree = mapper.readTree(result.getBody());
		    JsonNode node = tree.at("/payload");
		    balance = mapper.treeToValue(node, Balance.class);
		    logger.info(balance.toString());
		} else {
			logger.info("Request Failed");
			logger.info(result.getStatusCode().toString());
		}
		
		return balance;
	}
	
	public Balance getBalanceFromAPI()
			throws JsonProcessingException, JsonMappingException {
		return getBalanceFromAPI(new RestTemplate());
	}
	
	private List<Transaction> getTransactions(RestTemplate restTemplate, String fromDay, String toDay)
			throws JsonProcessingException, JsonMappingException {
		// make an HTTP GET request with headers
		String uri = HOST
				+ "api/gbs/banking/v4.0/accounts/"
				+ getAccountId()
				+ "/transactions?fromAccountingDate="
				+ fromDay
				+ "&&toAccountingDate="
				+ toDay;
		
		List<Transaction> transactionList = Collections.emptyList();
		
		
	    HttpEntity<String> request = Utils.getHttpEntity();
	    logger.info(request.toString());
	    
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
		if (result.getStatusCode() == HttpStatus.OK) {
			logger.info("Request Successful.");
			logger.info(result.getBody());
		    ObjectMapper mapper = new ObjectMapper();
		    JsonNode tree = mapper.readTree(result.getBody());
		    JsonNode node = tree.at("/payload/list");
		    transactionList = mapper.readValue(node.toString(), new TypeReference<List<Transaction>>() {});
		    logger.info(transactionList.toString());
		} else {
			logger.info("Request Failed");
			logger.info(result.getStatusCode().toString());
		}
		
		return transactionList;	
	}

	public List<Transaction> getTransactions(String fromDay, String toDay)
			throws JsonProcessingException, JsonMappingException {
		return getTransactions(new RestTemplate(), fromDay, toDay);
	}
	
	private ResponseEntity<String> transferCash(RestTemplate restTemplate, Transfer outgoingTransfer)
			throws JsonProcessingException {
		// make an HTTP POST request with headers
		String uri = HOST
				+ "api/gbs/banking/v4.0/accounts/"
				+ getAccountId()
				+ "/payments/money-transfers";
		
		ObjectMapper objectMapper = new ObjectMapper();	
		String jsonInput = objectMapper.writeValueAsString(outgoingTransfer);
		
		HttpEntity<String> request = Utils.getHttpEntity(jsonInput);

		logger.info("uri:" +uri);
		logger.info("json:\n" +jsonInput);
		logger.info("httpEntity:\n" +request);
	    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
	    printResult(result);
	    return result;
	}
	
	public ResponseEntity<String> transferCash(Transfer outgoingTransfer) 
			throws JsonProcessingException {
		return transferCash(new RestTemplate(), outgoingTransfer);
	}
	
	private ResponseEntity<String> checkIban(RestTemplate restTemplate)
			throws JsonProcessingException, JsonMappingException {
		// make an HTTP POST request with headers
		String uri = HOST
				+ "api/gbs/banking/v3.0/accounts/utils/iban/check-format";
				
		String jsonInput = "{\"iban\":\"IT07M0326849130052XX380XX18\"}";
		HttpEntity<String> request = Utils.getHttpEntity(jsonInput);

	    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
	    printResult(result);
		return result;
	}
	
	public ResponseEntity<String> checkIban()
			throws JsonProcessingException, JsonMappingException {
		return checkIban(new RestTemplate());
	}
	
	private void printResult(ResponseEntity<String> result) {
		if (result.getStatusCode() == HttpStatus.OK) {
		    logger.info("Request Successful.");
		    logger.info(result.getBody());
		} else {
		    logger.info("Request Failed");
		    logger.info(result.getStatusCode().toString());
		}
	}
}
