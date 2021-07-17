package com.indianrailways.trains.batch.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Train implements Serializable {

    private String trainName;
    private String trainNumber;
    private String returnTrainNumber;
    private String trainType;
    private String fromStationCode;
    private String fromStationName;
    private String toStationCode;
    private String toStationName;
    private String departureTime;
    private String arrivalTime;
    private String distanceInKilometers;
    private String durationInHours;
    private String railwayZone;
}
