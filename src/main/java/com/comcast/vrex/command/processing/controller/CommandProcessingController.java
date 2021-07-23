package com.comcast.vrex.command.processing.controller;

import com.comcast.vrex.command.processing.domain.StateCommandRequest;
import com.comcast.vrex.command.processing.domain.StateCommandResponse;
import com.comcast.vrex.command.processing.service.CommandProcessingService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CommandProcessingController {

    @Autowired
    private CommandProcessingService commandProcessingService;

    @Synchronized
    @PostMapping("/command")
    public ResponseEntity<StateCommandResponse> getFrequentCommandInfo(@RequestBody StateCommandRequest stateCommandRequest){
        log.debug("CommandProcessingController::getFrequentCommandInfo start");
        StateCommandResponse stateCommandResponse= commandProcessingService.getFrequentCommandInfo(stateCommandRequest);
        return new ResponseEntity<StateCommandResponse>(stateCommandResponse,HttpStatus.OK);
    }

}
