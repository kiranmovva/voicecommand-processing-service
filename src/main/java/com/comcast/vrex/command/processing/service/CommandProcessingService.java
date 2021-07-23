package com.comcast.vrex.command.processing.service;

import com.comcast.vrex.command.processing.domain.CommandVO;
import com.comcast.vrex.command.processing.domain.StateCommandResponse;

import java.util.List;
import java.util.Map;

public interface CommandProcessingService {

    StateCommandResponse getFrequentCommandInfo(Map<String, List<CommandVO>> stateCommandRequest);

}
