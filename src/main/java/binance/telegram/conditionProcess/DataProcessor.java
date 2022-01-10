package binance.telegram.conditionProcess;

import java.util.HashMap;

import com.google.gson.Gson;

import binance.telegram.conditionProcess.domain.CommonDomain;
import binance.telegram.conditionProcess.impl.MADisparity;

public class DataProcessor<T extends CommonDomain> {
	
	private Gson gson = new Gson();
	private Class<T> type;
	
	private final HashMap<String,ChartCondition> symbolsMap = new HashMap<String, ChartCondition>();
	
	public DataProcessor(Class<T> type) {
		this.type = type;
		
		String[] ss = {"ETHUSDT","FTMUSDT"};

		
		for(String symbol : ss) {
					
			this.symbolsMap.put(symbol,new MADisparity(1.005f,2));
			
		}
	}

	public DataProcessor(Class<T> type, String[] symbols) {
	
		String[] ss = {"ETHUSDT","FTMUSDT"};

			
		for(String symbol : symbols) {
					
			this.symbolsMap.put(symbol,new MADisparity(1.1f,20));
			
		}
		
	}
	
	
	public void run(String text) {
				
		T res = gson.fromJson(text, this.type);
		
		if(symbolsMap.containsKey(res.s)) {
			
			this.symbolsMap.get(res.s).check(res);
			
		}		
		

	}
	
	
}
