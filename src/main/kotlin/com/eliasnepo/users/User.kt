package com.eliasnepo.users

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(unique = true, nullable = false)
        val email: String,
        @Column(nullable = false)
        val name: String,
        @Column(nullable = false)
        val active: Boolean,
        @Column(nullable = false)
        val createdAt: LocalDateTime = LocalDateTime.now()
)
