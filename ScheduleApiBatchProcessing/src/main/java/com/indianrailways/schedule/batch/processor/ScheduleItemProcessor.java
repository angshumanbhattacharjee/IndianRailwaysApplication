package com.indianrailways.schedule.batch.processor;

import com.indianrailways.schedule.batch.data.ScheduleInput;
import com.indianrailways.schedule.batch.model.Schedule;
import org.springframework.batch.item.ItemProcessor;

public class ScheduleItemProcessor implements ItemProcessor<ScheduleInput, Schedule> {

    @Override
    public Schedule process(ScheduleInput scheduleInput) throws Exception {

        Schedule schedule = new Schedule();

        schedule.setScheduleId(scheduleInput.getId());
        schedule.setTrainNumber(scheduleInput.getTrain_number());
        schedule.setTrainName(scheduleInput.getTrain_name());
        schedule.setStationCode(scheduleInput.getStation_code());
        schedule.setStationName(scheduleInput.getStation_name());
        schedule.setJourneyDay(String.valueOf(scheduleInput.getDay()));
        schedule.setArrivalTime(scheduleInput.getArrival());
        schedule.setDepartureTime(scheduleInput.getDeparture());

        return schedule;
    }
}
