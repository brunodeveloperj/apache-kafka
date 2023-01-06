package com.martins.strproducer.services;

import java.util.Objects;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StringProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		kafkaTemplate.send("str-topic", message).handle((t, u) -> extracted(t, u));
	}

	private Object extracted(SendResult<String, String> t, Throwable u) {
		if (Objects.nonNull(t)) {
			log.info("Send message with success {}", t.getProducerRecord().value());
			log.info("Partition {}, Offset {}", t.getRecordMetadata().partition(), t.getRecordMetadata().offset());
			return t;
		} else if (u != null) {
			throw new RuntimeException(u);
		} else {
			throw new RuntimeException("Error send message");
		}
	}

}
