package binance.telegram.receiveBinance.impl;

import com.google.gson.Gson;

import binance.telegram.conditionProcess.DataProcessor;
import binance.telegram.conditionProcess.domain.CommonDomain;
import binance.telegram.receiveBinance.BinanceCallback;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class BinanceSocketListener extends WebSocketListener{
	

	private final BinanceCallback callback;
	
	public BinanceSocketListener(BinanceCallback callback) {
		// TODO Auto-generated constructor stub
		this.callback = callback;
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

		this.callback.onReceive(text);

	}

	@Override
	public void onOpen(WebSocket webSocket, Response response) {
		// TODO Auto-generated method stub
		System.out.println("open : "+response);
	}

	
	
	
}
