package kafka.producer.p.impl;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import kafka.producer.p.BinanceCallback;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class BinanceSocketListener extends WebSocketListener{
	

	private final BinanceCallback callback;
	private final KafkaProducer producer;
	
	public BinanceSocketListener(BinanceCallback callback) {
		// TODO Auto-generated constructor stub
		this.callback = callback;
		
		Properties properties = new Properties();
		
       properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
       properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
       properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
       properties.put(ProducerConfig.ACKS_CONFIG,"0");

       this.producer = new KafkaProducer<>(properties);
	}

	@Override
	public void onClosed(WebSocket webSocket, int code, String reason) {
		// TODO Auto-generated method stub
		super.onClosed(webSocket, code, reason);
	}

	@Override
	public void onClosing(WebSocket webSocket, int code, String reason) {
		// TODO Auto-generated method stub
		super.onClosing(webSocket, code, reason);
	}

	@Override
	public void onFailure(WebSocket webSocket, Throwable t, Response response) {
		// TODO Auto-generated method stub
		super.onFailure(webSocket, t, response);
		System.out.println("fail_msg : "+response.toString());
	}

	@Override
	public void onMessage(WebSocket webSocket, ByteString bytes) {
		// TODO Auto-generated method stub			
		System.out.println("text_byte : "+bytes);
		
	}

	@Override
	public void onMessage(WebSocket webSocket, String text) {
		// TODO Auto-generated method stub

//		this.callback.onReceive(text);
		
		ProducerRecord<String,String> record = new ProducerRecord<>("binance",text);
		
//		System.out.println(text+" 전송 완료");
		
		this.producer.send(record);
		
		

	}

	@Override
	public void onOpen(WebSocket webSocket, Response response) {
		// TODO Auto-generated method stub
		System.out.println("open : "+response);
	}

	
	
	
}
