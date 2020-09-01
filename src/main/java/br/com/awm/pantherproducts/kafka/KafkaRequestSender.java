package br.com.awm.pantherproducts.kafka;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Component
public class KafkaRequestSender<T> {

	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	public void send(String topic, T objectRequest) {
	    send(topic, objectRequest, null);
	}

	public void send(String topic, T objectRequest, Map<String, String> header) {
		ProducerRecord<String, String> record = new ProducerRecord<>(topic, new Gson().toJson(objectRequest));
		if(Optional.ofNullable(header).isPresent()) {
            addRecordHeader(header, record);
        }
		
		kafkaTemplate.send(record);
	}
	
	private void addRecordHeader(Map<String, String> header, ProducerRecord<String, String> record) {
        for (Entry<String, String> entry : header.entrySet()) {
            record.headers().add(new RecordHeader(entry.getKey(), entry.getValue().getBytes()));
        }
    }
}
