package com.eliasnepo.templates

import com.eliasnepo.notifications.NotificationType
import java.time.LocalDateTime

data class NotificationTemplate(
        val id: Long? = null,
        val type: NotificationType,
        val title: String,
        val content: String,
        val active: Boolean,
        val createdAt: LocalDateTime
)
