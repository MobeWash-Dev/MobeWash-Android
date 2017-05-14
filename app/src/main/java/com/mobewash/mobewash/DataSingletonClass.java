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
    private String FirstName = "";
    private String LastName = "";
    private String Phone = "";
    private String Email = "";
    private String carMake = "";
    private String carModel = "";
    private String carColor = "";
    private String Licence = "";

   //////////////////////setters///////////////////////
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public void setLicence(String licence) {
        Licence = licence;
    }

            ///////getters///////
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public String getLicence() {
        return Licence;
    }
}
