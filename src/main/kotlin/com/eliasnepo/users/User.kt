package com.eliasnepo.users

import java.time.LocalDateTime

data class User(
        val id: Long? = null,
        val name: String,
        val active: Boolean,
        val createdAt: LocalDateTime = LocalDateTime.now()
)
