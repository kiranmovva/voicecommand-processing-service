package com.comcast.vrex.command.processing.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"frequentCommands","topCommandsNationally"})
public class StateCommandResponse {

    @JsonIgnore
    @JsonAnyGetter
    private Map<String,FrequentCommand> frequentCommands;

    private Set<String> topCommandsNationally;

}
