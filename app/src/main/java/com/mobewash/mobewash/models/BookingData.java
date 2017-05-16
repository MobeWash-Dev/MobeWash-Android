package com.mobewash.mobewash.models;

/**
 * Created by sidney on 5/14/17.
 */

public class BookingData {
    private int calendarType;
    private String appointmentTypeID;
    private String dateTime;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    BookingData(int calendarType, String appointmentTypeID, String dateTime, String firstName, String lastName, String email, String phone) {
        this.calendarType = calendarType;
        this.appointmentTypeID = appointmentTypeID;
        this.dateTime = dateTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    class Field {
        private int id;
        private String name;
        private String value;
        Field(int id, String name, String value) {
            this.id = id;
            this.name = name;
            this.value = value;
        }
    }
}
