package edu.kpi.hotel.model.entity;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity("hotels")
public class Hotel {
    @Id
    private ObjectId id;

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

    @Embedded
    private Map<Integer, Room> rooms = new HashMap<>();

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Integer getRating() {
        return rating;
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (!Objects.equals(id, hotel.id)) return false;
        if (!Objects.equals(name, hotel.name)) return false;
        if (!Objects.equals(country, hotel.country)) return false;
        if (!Objects.equals(city, hotel.city)) return false;
        if (!Objects.equals(address, hotel.address)) return false;
        if (!Objects.equals(rating, hotel.rating)) return false;
        return Objects.equals(rooms, hotel.rooms);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        return result;
    }
}
