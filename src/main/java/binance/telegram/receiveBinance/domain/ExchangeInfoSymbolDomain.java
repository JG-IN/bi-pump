package binance.telegram.receiveBinance.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ExchangeInfoSymbolDomain {

	public List<Symbol> symbols;
	public List<Symbol> filteredSymbols;
	
	public List<Symbol> getFilterdSymbols() {
		
		List<Symbol> filteredSymbols = new ArrayList();
		
		for(Symbol symbol : symbols) {
			
			if(symbol.quoteAsset.equals("USDT"))
			filteredSymbols.add(symbol);
		}
		
		return filteredSymbols;
	}
	
	public class Symbol{
	
	public String symbol;
	public String quoteAsset;
	
	}
}
