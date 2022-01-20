package kafka.consumer.p;

public interface ChartCondition<T> {

	public void update(long curTime, double curVal);
	
	public void check(T res);

	
}
