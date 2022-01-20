package kafka.consumer.p.domain;

public class KlineData extends CommonDomain {
	
	public K k;
	
	public class K {

	//start time
	public long t;
	//current price
	public float c;
	//high price
	public float h;
	
	}
	
}
