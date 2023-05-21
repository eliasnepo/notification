package com.eliasnepo.templates

import com.eliasnepo.notifications.NotificationType
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationTemplateRepository : JpaRepository<NotificationTemplate, Long> {
    fun findByType(type: NotificationType): NotificationTemplate?
}
