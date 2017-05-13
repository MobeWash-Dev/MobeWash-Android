package com.mobewash.mobewash;

/**
 * Created by Danny on 5/13/2017.
 */

class DataSingletonClass {
    private static final DataSingletonClass ourInstance = new DataSingletonClass();

    static DataSingletonClass getInstance() {
        return ourInstance;
    }

    private DataSingletonClass() {
    }

    //add all the variables for the Data we want to share here
    public String FirstName = "";
    public String LastName = "";
    public String Phone = "";
    public String Email = "";
    public String carMake = "";
    public String carModel = "";
    public String carColor = "";
    public String Licence = "";
}
