package com.clashcoach.player;
import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration; import org.springframework.web.client.RestTemplate;
/** Central configuration point for outbound HTTP calls. */
@Configuration public class RestTemplateBuilderFactory { @Bean public RestTemplate create(){return new RestTemplate();} }
