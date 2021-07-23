package com.comcast.vrex.command.processing.controller;

import com.comcast.vrex.command.processing.domain.CommandVO;
import com.comcast.vrex.command.processing.domain.StateCommandResponse;
import com.comcast.vrex.command.processing.service.CommandProcessingService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CommandProcessingController {

    @Autowired
    private CommandProcessingService commandProcessingService;

    @Autowired
    private StateCommandResponse stateCommandResponse;

    @Synchronized
    @PostMapping("/commands")
    public ResponseEntity<StateCommandResponse> getFrequentCommandInfo(@RequestBody Map<String, List<CommandVO>> stateCommandRequest){
        log.debug("CommandProcessingController::getFrequentCommandInfo start");
        stateCommandResponse= commandProcessingService.getFrequentCommandInfo(stateCommandRequest);
        if(null!=stateCommandResponse && !CollectionUtils.isEmpty(stateCommandResponse.getFrequentCommands()) &&
                !CollectionUtils.isEmpty(stateCommandResponse.getTopCommandsNationally())){
            return new ResponseEntity<StateCommandResponse>(stateCommandResponse,HttpStatus.OK);
        }else{
            return new ResponseEntity<StateCommandResponse>(stateCommandResponse,HttpStatus.NOT_FOUND);
        }
    }

}
