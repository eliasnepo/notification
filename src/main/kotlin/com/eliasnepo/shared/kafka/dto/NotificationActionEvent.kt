package com.eliasnepo.shared.kafka.dto

import com.eliasnepo.notifications.NotificationTarget
import com.eliasnepo.notifications.NotificationType
import java.time.LocalDateTime

data class NotificationActionEvent(
    val userId: Long,
    val type: NotificationType,
    val targets: List<NotificationTarget>,
    val title: String?,
    val content: String?,
    val expiresAt: LocalDateTime?
)

/*
     Example of the JSON expected:
     {
        "userId": 1,
        "type": "LOW_BALANCE",
        "targets": ["SMS", "EMAIL"],
        "title": "Title sample",
        "content": "Content Sample",
        "expiresAt": "2023-01-08T00:21:21.444945"
     }
 */
