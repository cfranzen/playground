package de.cfranzen.playground.playground.jackson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest

@JsonTest
class ValueObjectDeSerializationTest {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `correctly serialize value objects`() {
        val entity = MyEntity(MyValueObject("my-id"))
        val result = objectMapper.writeValueAsString(entity)
        assertThat(result).isEqualTo("""{"id":"my-id"}""")
    }

    @Test
    fun `correctly deserialize value objects`() {
        val result = objectMapper.readValue<MyEntity>("""{"id":"my-id"}""")
        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(MyEntity(MyValueObject("my-id")))
    }
}

data class MyEntity(
    val id: MyValueObject
)

data class MyValueObject
@JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
    @JsonValue val value: String
)