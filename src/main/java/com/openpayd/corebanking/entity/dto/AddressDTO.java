package com.openpayd.corebanking.entity.dto;

import com.openpayd.corebanking.entity.Address;

public class AddressDTO {

    private Long id;

    public String addressLine1;

    public String addressLine2;

    public String city;

    public String country;

    public AddressDTO() {

    }

    public AddressDTO(Address address) {
        this.id=address.getId();
        this.addressLine1 = address.getAddressLine1();
        this.addressLine2 = address.getAddressLine2();
        this.city = address.getCity();
        this.country = address.getCountry();
    }

    public AddressDTO(String addressLine1,String addressLine2, String city, String country) {
        this.addressLine1 = addressLine1 ;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.country=country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
