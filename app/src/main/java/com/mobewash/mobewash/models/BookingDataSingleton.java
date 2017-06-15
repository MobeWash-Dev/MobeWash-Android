package com.mobewash.mobewash.models;

/**
 * Created by Danny on 5/13/2017.
 */

public class BookingDataSingleton {
    private static final BookingDataSingleton ourInstance = new BookingDataSingleton();

    public static BookingDataSingleton getInstance() {
        return ourInstance;
    }

    private BookingDataSingleton() {
    }

    //add all the variables for the Data we want to share here

    private WashService service;

    private CompanyData companyData;

    private UserDetails userDetails;

    //
    // Setters
    //

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

    public void setService(WashService service) {
        this.service = service;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    //
    // Getters
    //

    public CompanyData getCompanyData() {
        return this.companyData;
    }

    public UserDetails getUserDetails() {
        return this.userDetails;
    }

}
