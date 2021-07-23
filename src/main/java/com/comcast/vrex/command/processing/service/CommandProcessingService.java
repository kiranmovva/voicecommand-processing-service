package com.comcast.vrex.command.processing.service;

import com.comcast.vrex.command.processing.domain.StateCommandRequest;
import com.comcast.vrex.command.processing.domain.StateCommandResponse;

public interface CommandProcessingService {

    StateCommandResponse getFrequentCommandInfo(StateCommandRequest stateCommandRequest);

}
