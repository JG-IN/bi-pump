package kafka.producer.p;


import kafka.producer.p.impl.BinanceSocketListener;
import okhttp3.Request;

public interface BinanceWebsocketJavaClient {

	AutoCloseable klineCandlestick(String symbols,int interval,BinanceCallback callback);

	AutoCloseable createConnection(String url, BinanceSocketListener listner);
	
}

