package com.martins.strconsumer.listeners;

import org.springframework.stereotype.Component;

import com.martins.strconsumer.annotation.StrConsumerAnnotation;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StrConsumerListener {
	
	@SneakyThrows
	@StrConsumerAnnotation(groupId = "group-1")
	public void create(String message) {
		log.info("CREATE ::: Receive message {}", message);
		throw new NullPointerException("EXCEPTION ...");
	}

	@StrConsumerAnnotation(groupId = "group-1")
	public void log(String message) {
		log.info("LOG ::: Receive message {}", message);
	}

	@StrConsumerAnnotation(groupId = "group-2", containerFactory = "validMessageContainerFactory")
	public void history(String message) {
		log.info("history ::: Receive message {}", message);
	}

}
