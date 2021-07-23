package com.comcast.vrex.command.processing.config;

import com.comcast.vrex.command.processing.domain.StateCommandResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {

    @Bean
    public StateCommandResponse getStateCommandResponse() {
        return new StateCommandResponse();
    }
}
