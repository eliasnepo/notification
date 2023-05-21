package com.eliasnepo.shared.kafka.dto

import com.eliasnepo.notifications.NotificationTarget
import java.time.LocalDateTime

data class NotificationActionEvent(
    val userId: Long,
    val targets: List<NotificationTarget>,
    val expiresAt: LocalDateTime
)

/*
     Example of the JSON expected:
     {
        "userId": 1,
        "targets": ["SMS"],
        "expiresAt": "2023-01-08T00:21:21.444945"
     }
 */
