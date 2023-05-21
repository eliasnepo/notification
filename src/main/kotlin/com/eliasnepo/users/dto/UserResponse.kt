package com.eliasnepo.users.dto

import com.eliasnepo.users.User
import java.time.LocalDateTime

data class UserResponse(
        val name: String,
        val email: String,
        val active: Boolean,
        val createdAt: LocalDateTime
) {
    companion object {
        fun from(user: User): UserResponse? {
            return UserResponse(user.name, user.email, user.active, user.createdAt)
        }
    }
}
