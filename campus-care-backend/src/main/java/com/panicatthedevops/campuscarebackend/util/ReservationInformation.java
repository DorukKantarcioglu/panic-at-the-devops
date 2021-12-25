package com.panicatthedevops.campuscarebackend.util;

import java.util.Arrays;
import java.util.List;

public interface ReservationInformation {
    String DIAGNOVIR_RESERVATION = "diagnovir";
    String LIBRARY_RESERVATION = "library";
    String SPORTS_CENTER_RESERVATION = "sports_center";

    List<String> DIAGNOVIR_TIME_SLOTS = Arrays.asList("9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",
            "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00");
    List<String> DIAGNOVIR_PLACES = Arrays.asList("room1", "room2");

    List<String> LIBRARY_TIME_SLOTS = Arrays.asList("9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",
            "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00",
            "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00");
    List<String> LIBRARY_PLACES = Arrays.asList("Study_Room_1", "Study_Room_2", "Study_Room_3", "Study_Room_4"
            , "Study_Room_5", "Study_Room_6", "Study_Room_7", "Study_Room_8", "Study_Room_9", "Study_Room_10");

    List<String> SPORTS_CENTER_TIME_SLOTS = Arrays.asList("8:30", "9:30", "10:30", "11:30", "12:30", "13:30",
            "14:30", "15:30", "16:30", "17:30", "18:30", "19:30", "20:30", "21:30");
    List<String> SPORTS_CENTER_PLACES = Arrays.asList("Machine", "Free", "Indoor_Basketball", "Outdoor_Basketball",
            "Table_Tennis1", "Table_Tennis2", "Table_Tennis3", "Table_Tennis4");


}
