package com.comcast.vrex.command.processing.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateCommandRequest {

  @JsonAnySetter
  public Map<String, List<CommandVO>> stateName;

  @JsonAnySetter
  public void set(String key, List<CommandVO> CommandVO){
    stateName.put(key, CommandVO);
  }


}
