package com.comcast.vrex.command.processing.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "mostFrequentCommand", "startProcessTime", "stopProcessTime" })
public class FrequentCommand {
    private String mostFrequentCommand;
    private Long startProcessTime;
    private Long stopProcessTime;
}
