package binance.telegram.impl;

import java.util.Arrays;
import java.util.stream.Collectors;

import binance.telegram.BinanceCallback;
import binance.telegram.BinanceJavaClient;
import binance.telegram.BinanceSocketListener;
import binance.telegram.DataProcessor;
import binance.telegram.RequestsBuilder;
import binance.telegram.domain.CommonDomain;
import binance.telegram.domain.KlineData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class BinanceJavaClientImpl implements BinanceJavaClient{
	
	private static final String BASE_URL = "wss://stream.binance.com:9443/ws/";

	private final OkHttpClient client;	
	
	public BinanceJavaClientImpl(OkHttpClient client){
		this.client = client;
	}

	@Override
	public AutoCloseable klineCandlestick(String symbols,int interval) {
		// TODO Auto-generated method stub	
		
		String url = Arrays.stream(symbols.split(","))
				.map(s -> String.format("%s@kline_%sm",s,interval))
				.collect(Collectors.joining("/"));

		return this.createConnection(url,new BinanceSocketListener<>(KlineData.class));
	}

	@Override
	public AutoCloseable createConnection(String url, BinanceSocketListener<?> listner) {
		// TODO Auto-generated method stub

		WebSocket webSocket = this.client.newWebSocket(RequestsBuilder.webSocketBuilder(String.format("%s%s",BASE_URL,url)), listner);
		
		return () -> {
			webSocket.close(1000, null);
		};
	}
	

	
}
