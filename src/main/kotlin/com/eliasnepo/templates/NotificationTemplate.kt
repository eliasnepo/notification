package com.eliasnepo.templates

import com.eliasnepo.notifications.NotificationType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notifications_templates")
data class NotificationTemplate(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        val type: NotificationType,
        @Column(nullable = false)
        val title: String,
        @Column(nullable = false)
        val content: String,
        @Column(nullable = false)
        val active: Boolean,
        @Column(nullable = false)
        val createdAt: LocalDateTime = LocalDateTime.now()
)
