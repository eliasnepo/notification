package com.eliasnepo.shared.kafka

import com.eliasnepo.notifications.Notification
import com.eliasnepo.notifications.NotificationRepository
import com.eliasnepo.shared.exceptions.NotFoundException
import com.eliasnepo.shared.kafka.dto.NotificationActionEvent
import com.eliasnepo.templates.NotificationTemplateRepository
import com.eliasnepo.users.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CreateNotificationListener {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var notificationTemplateRepository: NotificationTemplateRepository

    @Autowired
    private lateinit var notificationRepository: NotificationRepository

    @KafkaListener(
        groupId = "group-id",
        topics = ["triggering-action"],
        containerFactory = "notificationActionEventContainerFactory"
    )
    fun consume(message: NotificationActionEvent) {
        val user = userRepository.findById(message.userId)
            .orElseThrow { NotFoundException("User not found with id ${message.userId}") }

        val notificationTemplate = notificationTemplateRepository.findByType(message.type)

        message.targets.forEach { target ->
            val title = notificationTemplate?.title ?: message.title
            val content = notificationTemplate?.content ?: message.content

            val notification = Notification(
                title = title ?: "Place holder",
                content = content ?: "Place holder",
                user = user,
                target = target,
                expiresAt = message.expiresAt,
                type = message.type
            )
            notificationRepository.save(notification)
        }

        println("Consumed message: $message")
    }
}
