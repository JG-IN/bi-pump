package binance.telegram.conditionProcess;

public interface ChartCondition<T> {

	public void update(long curTime, float curVal);
	
	public void check(T res);

	
}
