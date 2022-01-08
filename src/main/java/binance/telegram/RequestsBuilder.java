package binance.telegram;

import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestsBuilder {
	
	public RequestsBuilder() {
	
	}

	public static final Request webSocketBuilder(String url) {
		
		return new Request.Builder().url(url).build();
		
	}
}

