package edu.kpi.hotel.controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class HotelDto {
    @NotNull
    @Size(min = 2, max = 25)
    private String name;

    @NotNull
    @Size(min = 2, max = 75)
    private String country;

    @NotNull
    @Size(min = 2, max = 100)
    private String city;

    @NotNull
    @Size(min = 2, max = 255)
    private String address;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
