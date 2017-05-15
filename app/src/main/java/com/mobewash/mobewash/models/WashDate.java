package com.mobewash.mobewash.models;

/**
 * Created by sidney on 5/14/17.
 */

public class WashDate {
    private int day;
    private Slot[] slots;

    class Slot {
        private int time;
        private String[] appID;
    }
}

/*
var date = {  // Given date of a specific month
  day: 0,
  slots: [
    {
      time: 8,      // Hour of the day.  Is in Military time (0-23)
      appID:  []    // Stores appointment ID's for this time slot
                    // A timeslot is considered closed if
                    // appID.length >= washCap from company settings
    },
    {
      time: 9,
      appID:  []    // Stores appointment ID's for this time slot
                    // A timeslot is considered closed if
                    // appID.length >= washCap from company settings
    }
    // Etc...
]
};

 */