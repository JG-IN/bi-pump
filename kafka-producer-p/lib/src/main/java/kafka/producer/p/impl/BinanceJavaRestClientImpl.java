package kafka.producer.p.impl;

import java.io.IOException;

import kafka.producer.p.BinanceJavaRestClient;
import kafka.producer.p.RequestsBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class BinanceJavaRestClientImpl implements BinanceJavaRestClient{

	private static final String BASE_URL = "https://api.binance.com/api/v3/";
	
	private final OkHttpClient client;
	
	public BinanceJavaRestClientImpl(OkHttpClient client) {
		
		this.client = client;
		
	}
	
	/*
	 * https://api.binance.com/api/v3/exchangeinfo
	 * */
	public String getSymbols() {

		return this.executeRequset(RequestsBuilder.restApiBuilder(BASE_URL.concat("exchangeInfo")));	
		
	}
	
		
	private String executeRequset(Request request) {
		
		String result = null;
		
		try(ResponseBody resBody = this.client.newCall(request).execute().body()){
			
			result = resBody.string();
			
		}catch (IOException e) {
			
		}
		
		return result;
		
	}
	
}
