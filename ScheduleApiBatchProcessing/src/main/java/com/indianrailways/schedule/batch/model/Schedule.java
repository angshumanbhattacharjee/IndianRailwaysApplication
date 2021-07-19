package com.indianrailways.schedule.batch.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Schedule implements Serializable {

    @Id
    private String scheduleId;
    private String trainNumber;
    private String trainName;
    private String stationName;
    private String stationCode;
    private String journeyDay;
    private String arrivalTime;
    private String departureTime;
}
