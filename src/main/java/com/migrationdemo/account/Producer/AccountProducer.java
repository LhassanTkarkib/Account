package com.migrationdemo.account.Producer;

import com.migrationdemo.feignclient.AccountEntityDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service

public class AccountProducer {
//    @Value("${spring.kafka.topic.name}")
//    private String topicName;

    @Autowired
    private NewTopic topic;

    @Autowired
    private KafkaTemplate<String, AccountEntityDto> kafkaTemplate;

    public void sendMessage(AccountEntityDto account){

        Message<AccountEntityDto> message = MessageBuilder
                .withPayload(account)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
