package com.example.ritz.iextrading.Data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ritz on 17-11-2018.
 */

public class StockData {

       @SerializedName("quote")
        Quote QuoteObject;
       @SerializedName("company")
        Company CompanyObject;


        // Getter Methods

        public Quote getQuote() {
            return QuoteObject;
        }

        public Company getCompany() {
            return CompanyObject;
        }

        // Setter Methods

        public void setQuote(Quote quoteObject) {
            this.QuoteObject = quoteObject;
        }

        public void setCompany(Company companyObject) {
            this.CompanyObject = companyObject;
        }

    public class Company {

        @SerializedName("companyName")
        private String companyName;
        @SerializedName("website")
        private String website;
        @SerializedName("CEO")
        private String CEO;


        // Getter Methods


        public String getCompanyName() {
            return companyName;
        }


        public String getWebsite() {
            return website;
        }


        public String getCEO() {
            return CEO;
        }


        // Setter Methods


        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }


        public void setWebsite(String website) {
            this.website = website;
        }


        public void setCEO(String CEO) {
            this.CEO = CEO;
        }

    }
    public class Quote {

            @SerializedName("latestPrice")
        private float latestPrice;



        // Getter Methods

        public float getLatestPrice() {
            return latestPrice;
        }



        // Setter Methods

        public void setLatestPrice(float latestPrice) {
            this.latestPrice = latestPrice;
        }



}
}