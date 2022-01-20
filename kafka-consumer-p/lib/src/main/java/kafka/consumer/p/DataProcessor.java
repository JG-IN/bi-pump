package kafka.consumer.p;

import java.util.HashMap;

import com.google.gson.Gson;

import kafka.consumer.p.domain.CommonDomain;
import kafka.consumer.p.impl.MADisparity;

public class DataProcessor<T extends CommonDomain, K> {
	
	private Gson gson = new Gson();
	private Class<T> type;
	
	private final HashMap<String,ChartCondition> symbolsMap = new HashMap<String, ChartCondition>();
	
	public DataProcessor(Class<T> type,String[] symbols) {
		
		this.type = type;

		for(String symbol : symbols) {
					
			this.symbolsMap.put(symbol,new MADisparity(1.015,5));
			
		}
	}
	
	
	public void run(String text) {
				
		T res = gson.fromJson(text, this.type);
		
		if(symbolsMap.containsKey(res.s)) {
			
			this.symbolsMap.get(res.s).check(res);
			
		}		
		

	}
	
	
}
