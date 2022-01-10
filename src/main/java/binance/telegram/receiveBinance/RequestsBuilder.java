package binance.telegram.receiveBinance;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestsBuilder {
	
//	private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
	
	public RequestsBuilder() {
	
	}
	
	//only get 
	public static Request restApiBuilder(String url) {
		
		return new Request.Builder().url(url)
				.get()
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.build();
		
	}

	public static Request webSocketBuilder(String url) {
		
		return new Request.Builder().url(url).build();
		
	}
}

