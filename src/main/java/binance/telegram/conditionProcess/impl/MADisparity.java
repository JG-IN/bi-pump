package binance.telegram.conditionProcess.impl;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import binance.telegram.conditionProcess.ChartCondition;
import binance.telegram.conditionProcess.domain.KlineData;

public class MADisparity<T> implements ChartCondition<T>{

	private final float disparity;
	private final int mv;
	private long curTime;
	
	private Deque<Float> que;
	private float curMV;
	
	
	public MADisparity(float disparity, int mv) {
		
		this.disparity = disparity;
		this.mv = mv;		
		
		Float[] initQue = new Float[mv];

		Float maxCoinVal = 10000f;
		for(int i=0; i<mv; i++) {initQue[i] = maxCoinVal;}
		this.que = new ArrayDeque<Float>(Arrays.asList(initQue));
		
		this.curMV = maxCoinVal;
		System.out.println(this.curMV*mv);
	}	
	
	private void setCurTime(long curTime) {
		
		if(!this.compareTime(curTime)) {this.curTime = curTime;}

	}
	
	private float setQue(float curVal) {
		
		float last = this.que.poll();
		this.que.offer(curVal);
		
		return last;
	}
	
	private void setCurMv(float curVal) {
		
		this.curMV = (this.curMV*this.mv - this.setQue(curVal) + curVal)/mv;// long 나누기

	}	
	
	private boolean compareTime(long curTime) {
		
		return curTime == this.curTime;
	}
	
	private boolean compareMV(float curVal) {

		return this.curMV * this.disparity < curVal;
	}	
	
	@Override
	public void update(long curTime, float curVal) {

		this.setCurTime(curTime);
		this.setCurMv(curVal);
		
	}
	
	@Override
	public void check(T res) {
		
		KlineData data = (KlineData)res;

		if(this.compareMV(data.k.c)) {
			System.out.println("조건 만족");
		}

		if(!this.compareTime(data.k.t)) {
			this.update(data.k.t, data.k.c);
		}
		
	}
	
}
