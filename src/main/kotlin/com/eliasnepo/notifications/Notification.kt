package com.eliasnepo.notifications

import com.eliasnepo.users.User
import java.time.LocalDateTime

data class Notification(
        val id: Long? = null,
        val title: String,
        val content: String,
        val expiresAt: LocalDateTime,
        val user: User
);
