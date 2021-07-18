package com.indianrailways.trains.batch.processor;

import com.indianrailways.trains.batch.data.TrainInput;
import com.indianrailways.trains.batch.model.Train;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;

@Slf4j
public class TrainItemProcessor implements ItemProcessor<TrainInput, Train> {

    @Override
    public Train process(TrainInput trainInput) throws Exception {

        Train train = new Train();

        train.setTrainNumber(trainInput.getNumber());
        train.setTrainName(trainInput.getName());
        train.setReturnTrainNumber(trainInput.getReturn_train());
        train.setTrainType(trainInput.getType());
        train.setFromStationCode(trainInput.getFrom_station_code());
        train.setFromStationName(trainInput.getFrom_station_name());
        train.setToStationCode(trainInput.getTo_station_code());
        train.setToStationName(trainInput.getTo_station_name());
        train.setDepartureTime(trainInput.getDeparture());
        train.setArrivalTime(trainInput.getArrival());
        train.setDistanceInKilometers(String.valueOf(trainInput.getDistance()));
        train.setDurationInHours(String.valueOf(trainInput.getDuration_h()));
        train.setRailwayZone(trainInput.getZone());
        train.setStationsInRoute(new ArrayList<>());
        return train;
    }
}
