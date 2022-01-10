package binance.telegram;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import com.google.gson.Gson;

import binance.telegram.conditionProcess.domain.KlineData;
import binance.telegram.receiveBinance.BinanceCallback;
import binance.telegram.receiveBinance.BinanceJavaRestClient;
import binance.telegram.receiveBinance.BinanceWebsocketJavaClient;
import binance.telegram.receiveBinance.domain.ExchangeInfoSymbolDomain;
import binance.telegram.receiveBinance.impl.BinanceJavaRestClientImpl;
import binance.telegram.receiveBinance.impl.BinanceJavaWebSocketClientImpl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class Main {
	
	public static void main(String args[]) throws Exception {

		
		OkHttpClient client = new OkHttpClient.Builder().pingInterval(120, TimeUnit.SECONDS).build();
		
		BinanceWebsocketJavaClient wclient= new BinanceJavaWebSocketClientImpl(client);			
		
		BinanceJavaRestClient rclient = new BinanceJavaRestClientImpl(client);
		
		ExchangeInfoSymbolDomain exchangeInfoSymbolDomain = new Gson().fromJson(rclient.getSymbols(), ExchangeInfoSymbolDomain.class);		

		List<ExchangeInfoSymbolDomain.Symbol> symbols =  exchangeInfoSymbolDomain.getFilterdSymbols();
		
		String sysmbols = symbols.stream().map(s -> s.symbol.toLowerCase()).limit(20).collect(Collectors.joining(","));
		
		AutoCloseable a = wclient.klineCandlestick(sysmbols, 3,(text) -> {System.out.println(text);});
		
//		System.out.println((Float.MAX_VALUE-1000)/2);
//		System.out.println((Double.MAX_VALUE-1000)/3);
//		System.out.println((Double.MAX_VALUE-1000)/(*));
		
//		System.out.println(Double.MAX_VALUE-1000);
//			
//		System.out.println(Double.MAX_VALUE-1000000);
//		System.out.println((((Double.MAX_VALUE-1000000)/3)*3));
//		System.out.println(1.7014117E38f -1594.045 );
		
	}
}
		
		