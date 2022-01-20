package kafka.producer.p;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import kafka.producer.p.domain.ExchangeInfoSymbolDomain;
import kafka.producer.p.impl.BinanceJavaRestClientImpl;
import kafka.producer.p.impl.BinanceJavaWebSocketClientImpl;
import okhttp3.OkHttpClient;

public class Main {
	
	public static void main(String args[]) throws Exception {

		
		OkHttpClient client = new OkHttpClient.Builder().pingInterval(120, TimeUnit.SECONDS).build();
		
		BinanceWebsocketJavaClient wclient= new BinanceJavaWebSocketClientImpl(client);			
		
		BinanceJavaRestClient rclient = new BinanceJavaRestClientImpl(client);
		
		ExchangeInfoSymbolDomain exchangeInfoSymbolDomain = new Gson().fromJson(rclient.getSymbols(), ExchangeInfoSymbolDomain.class);		

		List<ExchangeInfoSymbolDomain.Symbol> symbols =  exchangeInfoSymbolDomain.getFilterdSymbols();
		
		String sysmbols = symbols.stream().map(s -> s.symbol.toLowerCase()).limit(100).collect(Collectors.joining(","));
		
		AutoCloseable a = wclient.klineCandlestick(sysmbols, 1,(text) -> {});
		
	}
}
		
		