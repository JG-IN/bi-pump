package binance.telegram;

import com.google.gson.Gson;

import binance.telegram.domain.CommonDomain;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class BinanceSocketListener<T extends CommonDomain> extends WebSocketListener{
	
	private DataProcessor<T> dataProcessor;
	
	public BinanceSocketListener(Class<T> type) {
		// TODO Auto-generated constructor stub
		this.dataProcessor = new DataProcessor<T>(type);
	}

	@Override
	public void onClosed(WebSocket webSocket, int code, String reason) {
		// TODO Auto-generated method stub
		super.onClosed(webSocket, code, reason);
	}

	@Override
	public void onClosing(WebSocket webSocket, int code, String reason) {
		// TODO Auto-generated method stub
		super.onClosing(webSocket, code, reason);
	}

	@Override
	public void onFailure(WebSocket webSocket, Throwable t, Response response) {
		// TODO Auto-generated method stub
		super.onFailure(webSocket, t, response);
		System.out.println(t);
		System.out.println("fail_msg : "+response.toString());
	}

	@Override
	public void onMessage(WebSocket webSocket, ByteString bytes) {
		// TODO Auto-generated method stub			
		System.out.println("text_byte : "+bytes);
		
	}

	@Override
	public void onMessage(WebSocket webSocket, String text) {
		// TODO Auto-generated method stub
;
		dataProcessor.run(text);
		
		
	
	}

	@Override
	public void onOpen(WebSocket webSocket, Response response) {
		// TODO Auto-generated method stub
		System.out.println("open : "+response);
	}

	
	
	
}
