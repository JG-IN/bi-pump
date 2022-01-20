package kafka.producer.p;

@FunctionalInterface
public interface BinanceCallback {

	public void onReceive(String text);
	
}
