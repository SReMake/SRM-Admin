package com.sreMake.conf

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component


@Component
class ConfigFileLogger : ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    override fun onApplicationEvent(event: ApplicationEnvironmentPreparedEvent) {
        val environment = event.environment
        for (propertySource in environment.propertySources) {
            if (propertySource.name.startsWith("applicationConfig:")) {
                println("Using configuration file: " + propertySource.name)
            }
        }
    }
}