package com.eliasnepo.users

import com.eliasnepo.users.dto.UserRequest
import com.eliasnepo.users.dto.UserResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userRepository: UserRepository) {

    @PostMapping
    fun create(@RequestBody @Valid userRequest: UserRequest): ResponseEntity<UserResponse> {
        val user = User(email = userRequest.email, name = userRequest.name, active = true)

        return ResponseEntity.ok(UserResponse.from(userRepository.save(user)))
    }
}
