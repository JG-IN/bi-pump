package binance.telegram.receiveBinance;

@FunctionalInterface
public interface BinanceCallback {

	public void onReceive(String text);
	
}
