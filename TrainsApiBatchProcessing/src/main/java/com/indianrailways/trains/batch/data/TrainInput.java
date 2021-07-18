package com.indianrailways.trains.batch.data;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrainInput implements Serializable {

    private String type;
    private String arrival;
    private String departure;
    private Integer distance;
    private Integer duration_h;
    private String from_station_code;
    private String from_station_name;
    private String name;
    private String number;
    private String return_train;
    private String to_station_code;
    private String to_station_name;
    private String zone;
}
