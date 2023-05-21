package com.eliasnepo.shared.kafka

import com.eliasnepo.shared.kafka.dto.NotificationActionEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
@EnableKafka
class KafkaConfiguration {

    @Autowired
    private lateinit var kafkaProperties: KafkaProperties

    private fun consumerFactory(jsonDeserializerName: Class<*>): DefaultKafkaConsumerFactory<String, *> {
        val properties = mapOf<String, Any>(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to kafkaProperties.consumer.groupId,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to kafkaProperties.consumer.keyDeserializer,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to kafkaProperties.consumer.valueDeserializer,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to kafkaProperties.consumer.autoOffsetReset,
            JsonDeserializer.TRUSTED_PACKAGES to "*"
        )
        return DefaultKafkaConsumerFactory(properties, StringDeserializer(), JsonDeserializer(jsonDeserializerName))
    }

    @Bean
    fun notificationActionEventContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, NotificationActionEvent> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, NotificationActionEvent>()
        factory.consumerFactory = consumerFactory(NotificationActionEvent::class.java) as ConsumerFactory<String, NotificationActionEvent>
        return factory
    }
}
