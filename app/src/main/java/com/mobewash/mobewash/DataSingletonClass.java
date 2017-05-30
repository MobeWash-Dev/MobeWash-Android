package com.mobewash.mobewash;

import com.mobewash.mobewash.models.CompanyData;
import com.mobewash.mobewash.models.WashService;

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
    private String firstName = "";
    private String lastName = "";
    private String phone = "";
    private String email = "";
    private String carMake = "";
    private String carModel = "";
    private String carColor = "";
    private String license = "";
    private Service selectedService = null;
    private WashService service;

    private CompanyData companyData;

    //For Company Frag
    private String companyName = "";

   //////////////////////setters///////////////////////
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSelectedService(Service selectedService) {
        this.selectedService = selectedService;
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

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

    public Service getSelectedService() {
        return selectedService;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CompanyData getCompanyData() {
        return this.companyData;
    }

    public void setService(WashService service) {
        this.service = service;
    }
}
