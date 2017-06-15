package com.mobewash.mobewash.models;

import com.mobewash.mobewash.DetailsClass;

/**
 * Created by Danny on 5/13/2017.
 */

public class BookingDataSingleton {
    private static final BookingDataSingleton ourInstance = new BookingDataSingleton();

    static BookingDataSingleton getInstance() {
        return ourInstance;
    }

    private BookingDataSingleton() {
    }

    //add all the variables for the Data we want to share here

    private WashService service;

    private CompanyData companyData;

    //For Company Frag
    private String companyName = "";


    ////setters////

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

    ///////getters///////

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
