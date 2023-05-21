package com.eliasnepo.shared.kafka

import com.eliasnepo.shared.kafka.dto.NotificationActionEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CreateNotificationListener {

    @KafkaListener(
        groupId = "group-id",
        topics = ["triggering-action"],
        containerFactory = "notificationActionEventContainerFactory"
    )
    fun consume(message: NotificationActionEvent) {
        println("Consumed message: $message")
    }
}
