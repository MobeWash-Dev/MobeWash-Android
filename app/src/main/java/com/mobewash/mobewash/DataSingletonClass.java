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

    private Service selectedService = null;
    private WashService service;

    private CompanyData companyData;

    //For Company Frag
    private String companyName = "";


    ////setters////
    public void setSelectedService(Service selectedService) {
        this.selectedService = selectedService;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

    ///////getters///////

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




    ///////////////// DETAILSCLASS///////
    public DetailsClass deets = new DetailsClass();
}
