package aa.example.fabrickclient.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionType {
	/*
	 * Tried to get the GBS_TRANSACTION_TYPE from API like this:
	 * 
GET /api/fabrick/utils/v4.0/enumerations/GBS_TRANSACTION_TYPE/values?lang=%22EN%22 HTTP/1.1
HOST: sandbox.platfr.io
content-type: 'application/json',application/json
api-key: 'FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP',FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP
auth-schema: S2S

	* but I got the following error 403 Forbidden :
	* 
{
  "status" : "KO",
  "errors" :  [
		{
			"code" : "GTW003",
			"description" : "Api key not found",
			"params" : ""
		}
	],
  "payload": {}
}

	*/
	
	private String enumeration;
	private String value;
	
	public String getEnumeration() {
		return enumeration;
	}
	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "TransactionType [enumeration=" + enumeration + ", value=" + value + "]";
	}
	
}
