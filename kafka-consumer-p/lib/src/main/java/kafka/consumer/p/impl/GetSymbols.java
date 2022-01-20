package kafka.consumer.p.impl;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import kafka.consumer.p.domain.ExchangeInfoSymbolDomain;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetSymbols {

	private OkHttpClient client;

	public GetSymbols() {

		this.client = new OkHttpClient();
	}

	private String getResponse() {

		String result = null;

		try {

			result = this.client
					.newCall(new Request.Builder().get().url("https://api.binance.com/api/v3/exchangeInfo")
							.addHeader("Content-Type", "application/x-www-form-urlencoded").build())
					.execute().body().string();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

	public String[] get() {
		
		String serializedSymbols = this.getResponse();
	
		if(serializedSymbols != null) {
			
			Gson gson = new Gson();
			
			ExchangeInfoSymbolDomain deserializedSymbols = (ExchangeInfoSymbolDomain)gson.fromJson(serializedSymbols,
																							ExchangeInfoSymbolDomain.class);
			
			return deserializedSymbols.getFilterdSymbols().stream().map(s -> s.symbol).toArray(String[]::new);
			
		}
		
		return null;
				
	}

}
