package com.mobewash.mobewash;

/**
 * Created by Danny on 6/3/2017.
 */

public class DetailsClass {

    private String firstName = "";
    private String lastName = "";
    private String phone = "";
    private String email = "";
    private String carMake = "";
    private String carModel = "";
    private String carColor = "";
    private String license = "";
    private String Extras = "";

    public DetailsClass() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setLicense(String licence) {
        license = licence;
    }

    public void setExtras(String extra){ Extras = extra;}


    ///////getters///////
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
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

    public String getLicense() {
        return license;
    }

    public String getExtras(){return Extras;}


}
