package com.dnd.sub.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("front")
public record FrontUriProperties(
    String redirectUri
) {
}
