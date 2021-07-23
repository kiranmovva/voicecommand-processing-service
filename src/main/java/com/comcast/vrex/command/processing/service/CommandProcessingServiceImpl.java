package com.comcast.vrex.command.processing.service;

import com.comcast.vrex.command.processing.domain.CommandVO;
import com.comcast.vrex.command.processing.domain.FrequentCommand;
import com.comcast.vrex.command.processing.domain.StateCommandRequest;
import com.comcast.vrex.command.processing.domain.StateCommandResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommandProcessingServiceImpl implements CommandProcessingService {

    @Autowired
    StateCommandResponse stateCommandResponse;

    @Override
    public StateCommandResponse getFrequentCommandInfo(StateCommandRequest stateCommandRequest) {
        log.debug("CommandProcessingServiceImplL::getFrequentCommandInfo start");
        if (null != stateCommandRequest.getStateName() && !stateCommandRequest.getStateName().isEmpty()) {
            stateCommandResponse = calculateTopCommand(stateCommandRequest.getStateName());
        }
        log.debug("CommandProcessingServiceImplL::getFrequentCommandInfo End");
        return stateCommandResponse;
    }

    private StateCommandResponse calculateTopCommand(Map<String, List<CommandVO>> stateName) {
        log.debug("CommandProcessingServiceImplL::calculateMaxCommand start");
        Map<String, Long> countryWideMap = new HashMap<>();
        Map<String, FrequentCommand> filterdResponseMap = new HashMap<>();
        for (Map.Entry<String, List<CommandVO>> commandRequest :
                stateName.entrySet()) {
            FrequentCommand frequentCommand = new FrequentCommand();
            long startTime = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
            //top state command logic
            Map<String, Long> stateMap = commandRequest.getValue().stream().
                    collect(Collectors.groupingBy(CommandVO -> CommandVO.getCommand().toUpperCase(), Collectors.counting()));
            Optional<String> topStateCommand = stateMap.entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey);
            log.debug("The Top command for State:{} : Command {} : count {}"
                    , commandRequest.getKey(), topStateCommand.get(), stateMap.entrySet());
            if (topStateCommand.isPresent()) {
                FrequentCommand frequentCommandInfo = frequentCommand.builder()
                        .mostFrequentCommand(topStateCommand.get())
                        .startProcessTime(startTime)
                        .stopProcessTime(TimeUnit.NANOSECONDS.toMicros(System.nanoTime()))
                        .build();
                filterdResponseMap.put(commandRequest.getKey(), frequentCommandInfo);
            }
            stateMap.forEach(
                    (key, value) -> countryWideMap.merge(key, value, (v1, v2) -> v1.equals(v2) ? v1 : v1 + v2)
            );
        }
        stateCommandResponse.setFrequentCommands(filterdResponseMap);
        log.debug("The Top countryWideMap for  count {}", countryWideMap.entrySet());
        //top three commands on country
        Set<String> topThreeCommands =
                countryWideMap.entrySet().stream().
                        sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(3)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new)).keySet();
        stateCommandResponse.setTopCommandsNationally(topThreeCommands);
        log.debug("CommandProcessingServiceImplL::calculateMaxCommand End");
        return stateCommandResponse;
    }
}
