package binance.telegram;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import binance.telegram.domain.KlineData;
import binance.telegram.impl.BinanceJavaClientImpl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class Main {
	
	public static void main(String args[]) throws Exception {

		
		BinanceJavaClient client= new BinanceJavaClientImpl(new OkHttpClient.Builder()
				.pingInterval(120, TimeUnit.SECONDS).build());			
		
		AutoCloseable a = client.klineCandlestick("ethusdt,ftmusdt", 1);

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
		
		