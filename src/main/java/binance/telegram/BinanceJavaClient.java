package binance.telegram;

import binance.telegram.domain.KlineData;
import okhttp3.Request;

public interface BinanceJavaClient {

	AutoCloseable klineCandlestick(String symbols,int interval);

	AutoCloseable createConnection(String url, BinanceSocketListener<?> listner);
	
}

