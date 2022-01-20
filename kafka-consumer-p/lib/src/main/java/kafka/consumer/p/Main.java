package kafka.consumer.p;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import kafka.consumer.p.domain.KlineData;
import kafka.consumer.p.impl.GetSymbols;
import kafka.consumer.p.impl.MADisparity;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DataProcessor dataProcessor = 
				new DataProcessor<>(KlineData.class, new GetSymbols().get());		

		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "binance-MAD");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		Consumer<String,String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singleton("binance"));
		
		while(true) {
			ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(2000));
			records.forEach(record -> dataProcessor.run(record.value()));
		}
		
	}

}
