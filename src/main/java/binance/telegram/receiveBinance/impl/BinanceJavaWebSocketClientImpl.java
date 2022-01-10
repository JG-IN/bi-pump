package binance.telegram.receiveBinance.impl;

import java.util.Arrays;
import java.util.stream.Collectors;

import binance.telegram.conditionProcess.domain.CommonDomain;
import binance.telegram.conditionProcess.domain.KlineData;
import binance.telegram.receiveBinance.BinanceCallback;
import binance.telegram.receiveBinance.BinanceWebsocketJavaClient;
import binance.telegram.receiveBinance.RequestsBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class BinanceJavaWebSocketClientImpl implements BinanceWebsocketJavaClient{
	
	private static final String BASE_URL = "wss://stream.binance.com:9443/ws/";

	private final OkHttpClient client;	
	
	public BinanceJavaWebSocketClientImpl(OkHttpClient client){
		this.client = client;
	}

	@Override
	public AutoCloseable klineCandlestick(String symbols,int interval,BinanceCallback callback) {
		// TODO Auto-generated method stub	
		
		String url = Arrays.stream(symbols.split(","))
				.map(s -> String.format("%s@kline_%sm",s,interval))
				.collect(Collectors.joining("/"));

		return this.createConnection(url,new BinanceSocketListener(callback));
	}

	@Override
	public AutoCloseable createConnection(String url, BinanceSocketListener listner) {
		// TODO Auto-generated method stub

		WebSocket webSocket = this.client.newWebSocket(RequestsBuilder.webSocketBuilder(String.format("%s%s",BASE_URL,url)), listner);
		
		return () -> {
			webSocket.close(1000, null);
		};
	}
	

	
}
