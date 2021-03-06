package no.hvl.dat251.fjelltur.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.util.UUID

@Configuration
@ConfigurationProperties("security")
@EnableConfigurationProperties
class SecurityProperty(
  var secretSigningKey: String = UUID.randomUUID().toString(),
  var maxAgeInDays: Long = 30L
)
