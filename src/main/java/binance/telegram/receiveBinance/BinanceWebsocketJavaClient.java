package binance.telegram.receiveBinance;

import binance.telegram.conditionProcess.domain.KlineData;
import binance.telegram.receiveBinance.impl.BinanceSocketListener;
import okhttp3.Request;

public interface BinanceWebsocketJavaClient {

	AutoCloseable klineCandlestick(String symbols,int interval,BinanceCallback callback);

	AutoCloseable createConnection(String url, BinanceSocketListener listner);
	
}

