package com.eliasnepo.shared.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CreateNotificationListener {

    @KafkaListener(topics = ["triggering-action"], groupId = "group_id")
    fun consume(message: String) {
        println("Consumed message: $message")
    }
}
