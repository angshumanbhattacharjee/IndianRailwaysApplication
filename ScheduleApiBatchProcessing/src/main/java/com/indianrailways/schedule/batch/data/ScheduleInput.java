package com.indianrailways.schedule.batch.data;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleInput implements Serializable {

    private String arrival;
    private Integer day;
    private String train_name;
    private String station_name;
    private String station_code;
    private String id;
    private String train_number;
    private String departure;
}
