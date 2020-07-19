package aa.example.fabrickclient;

import java.util.TimeZone;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Utils {

	public static HttpEntity<String> getHttpEntity(String jsonInput) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
		headers.set("Auth-Schema", "S2S");
		headers.set("X-Time-Zone", TimeZone.getDefault().getID());
		return new HttpEntity<String>(jsonInput, headers);
	}
	
	public static HttpEntity<String> getHttpEntity() {
		return getHttpEntity(null);
	}
}
