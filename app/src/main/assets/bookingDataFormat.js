
/* Includes all the companies and their respective settings, which affects
 * the calendar and which dates and timeslots are available for booking */
var companyData = [{
        uid: 123912,
        name: "DayBreak Games",
        location: "12681 Sandy Crest Court",
        setting: {
            opDays: [2, 3], // 0-6 represents Monday through Sunday
            washCap: 3,     // Represents max number of washes per time block (usually 1h blocks)
            range: [8,16]   // Range from 8am to 6pm, in this example its to 8-am 4pm
        },
				months: []// Should have month collection ID's based on year/ etc., collections of years
    },
    {
        uid: 223222,
        name: "Alexandria",
        location: "12681 Sandy Crest Court",
        setting: {
            opDays: [3],
            washCap: 3,
            range: [8,16]
        }
    }
];

/* The Services (type of washes) available for booking at a given time */
var services = [
{
   uid: ,
   appointmentTypeID: 2925879,   // To make the correct booking query to Acuity, this is required
   name: "Mobe" ,
   cost: 24 ,             // Base Cost
   extraCost: 6 ,         // If they have an SUV or bigger Car
   length: 55,            // Length of Time
   extraLength: 25 ,      // Extra Wash Time for bigger cars
   info: ["The Full Exterior Car Wash Service",
         "Quality Hand Wash with Wheel & Tire Shine"]   // Important info given line by line
},
{
   uid: ,
   appointmentTypeID: 2944155,
   name: "MobePlus" ,
   cost: 35,   // decide
   extraCost: 10 ,
   length: 90,
   extraLength: 25 ,
   info: ["The Full Exterior Car Wash Service",
         "Quality Hand Wash with Wheel & Tire Shine"]
}
];


/*
 * This is part of a greater object called Month, which stores 29, 30, or 31 of these dates.
 */

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


/* Final Data Submitted for everything except Payment Form */
var bookingData = {
"body": {
   "calendarType": 1144452,
   "appointmentTypeID": "2944155",
   "datetime": "2017-04-13T12:00",
   "firstName": "Jesse",
   "lastName": "Ren",
   "email": "jeren.neurogen@gmail.com",
   "phone": "8588475518",
   "fields": [
       {
           "id": 2695254,
           "name": "extraLocationInfo",
           "value": "1111"
       },
       {
           "id": 2695257,
           "name": "4digitslicense",
           "value": "4819"
       },
       {
           "id": 2695260,
           "name": "carMakeModel",
           "value": "GMC Sonoma "
       },
       {
           "id": 2695263,
           "name": "carColor",
           "value": "White"
       },
       {
           "id": 2695266,
           "name": "extraInformation",
           "value": "Filler"
       },
       {
           "id": 2695272,
           "name": "company",
           "value": "DayBreak Games"
       }
   ]
}
}
