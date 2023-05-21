package com.eliasnepo.notifications

import com.eliasnepo.users.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notifications")
data class Notification(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Enumerated(EnumType.STRING)
        val type: NotificationType,
        @Enumerated(EnumType.STRING)
        val target: NotificationTarget,
        @Column(nullable = false)
        val title: String,
        @Column(nullable = false)
        val content: String,
        val expiresAt: LocalDateTime?,
        @ManyToOne
        val user: User
);
