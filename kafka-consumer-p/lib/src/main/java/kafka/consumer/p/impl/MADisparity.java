package kafka.consumer.p.impl;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import kafka.consumer.p.ChartCondition;
import kafka.consumer.p.domain.CommonDomain;
import kafka.consumer.p.domain.KlineData;

public class MADisparity implements ChartCondition<KlineData>{

	private final double disparity;
	private final int mv;
	private long curTime;
	
	private Deque<Double> que;
	private double curMV;
	
	
	public MADisparity(double disparity, int mv) {
		
		this.disparity = disparity;
		this.mv = mv;		
		
		Double[] initQue = new Double[mv];

		Double maxCoinVal = 100000.0;
		
		for(int i=0; i<mv; i++) {initQue[i] = maxCoinVal;}
		this.que = new ArrayDeque<Double>(Arrays.asList(initQue));
		
		this.curMV = maxCoinVal;
		
	}	
	
	private void setCurTime(long curTime) {
		
		if(!this.compareTime(curTime)) {this.curTime = curTime;}

	}
	
	private double setQue(double curVal) {
		
		double last = this.que.poll();
		this.que.offer(curVal);
		
		return last;
	}
	
	private void setCurMv(double curVal) {
		
//		System.out.println("이전 "+this.curMV*this.mv);
		this.curMV = (this.curMV*this.mv - this.setQue(curVal) + curVal)/mv;
//		System.out.println("이후 "+this.curMV*this.mv);
	}	
	
	private boolean compareTime(long curTime) {
		
		return curTime == this.curTime;
	}
	
	private boolean compareMV(double curVal) {

//		System.out.println(this.curMV * this.disparity+" 와 "+curVal+" 비교");
		
		return this.curMV * this.disparity < curVal;
	}	
	
	@Override
	public void update(long curTime, double curVal) {

		this.setCurTime(curTime);
		this.setCurMv(curVal);
		
	}
	
	@Override
	public void check(KlineData data) {		
		
		if(this.compareMV(data.k.c)) {
			System.out.println("조건 만족 "+data.s);
		}

		if(!this.compareTime(data.k.t)) {
			this.update(data.k.t, data.k.c);
		}
		
	}
	
}
