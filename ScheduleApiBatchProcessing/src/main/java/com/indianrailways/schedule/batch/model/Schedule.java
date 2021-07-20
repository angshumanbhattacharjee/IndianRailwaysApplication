package com.indianrailways.schedule.batch.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document
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
