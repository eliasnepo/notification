package com.eliasnepo.users

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var userRepository: UserRepository

    @Test
    fun `should create a user given a valid data`() {
        val user = User(email = "test@example.com", name = "Test User", active = true)
        given(userRepository.save(any<User>())).willReturn(user)

        mockMvc.post("/users") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "email": "test@example.com",
                    "name": "Test User"
                }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("$.email") { value("test@example.com") }
            jsonPath("$.name") { value("Test User") }
            jsonPath("$.active") { value(true) }
        }

        Mockito.verify(userRepository).save(any<User>())
    }

    @Test
    fun `shouldn't create a user given a invalid email`() {
        val user = User(email = "test@example.com", name = "Test User", active = true)
        given(userRepository.save(any<User>())).willReturn(user)

        mockMvc.post("/users") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "email": "test",
                    "name": "Test User"
                }
            """.trimIndent()
        }.andExpect {
            status { isBadRequest() }
        }

        Mockito.verifyNoInteractions(userRepository)
    }

    @Test
    fun `shouldn't create a user given a invalid name`() {
        val user = User(email = "test@example.com", name = "Test User", active = true)
        given(userRepository.save(any<User>())).willReturn(user)

        mockMvc.post("/users") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "email": "test@example.com",
                    "name": ""
                }
            """.trimIndent()
        }.andExpect {
            status { isBadRequest() }
        }

        Mockito.verifyNoInteractions(userRepository)
    }
}
