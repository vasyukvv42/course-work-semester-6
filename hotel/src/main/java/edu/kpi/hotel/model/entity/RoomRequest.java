package edu.kpi.hotel.model.entity;

import dev.morphia.annotations.*;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity("room_requests")
public class RoomRequest {
    @Id
    private ObjectId id;

    @Reference
    private User user;

    @Reference
    private Hotel hotel;

    @Positive
    private Integer people;

    @PositiveOrZero
    private BigDecimal maxCost;

    @NotNull
    private Date reserveFrom;

    @NotNull
    private Date reserveTo;

    @NotNull
    @Indexed(options = @IndexOptions(expireAfterSeconds = 0))
    private Date expiryDate;

    @NotNull
    private RoomRequestStatus status = RoomRequestStatus.PENDING;

    public ObjectId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public BigDecimal getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(BigDecimal maxCost) {
        this.maxCost = maxCost;
    }

    public Date getReserveFrom() {
        return reserveFrom;
    }

    public void setReserveFrom(Date reserveFrom) {
        this.reserveFrom = reserveFrom;
    }

    public Date getReserveTo() {
        return reserveTo;
    }

    public void setReserveTo(Date reserveTo) {
        this.reserveTo = reserveTo;
    }

    public RoomRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RoomRequestStatus status) {
        this.status = status;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isAccepted() {
        return status == RoomRequestStatus.ACCEPTED;
    }

    public boolean isPending() {
        return status == RoomRequestStatus.PENDING;
    }

    public boolean isDeclined() {
        return status == RoomRequestStatus.DECLINED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomRequest that = (RoomRequest) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(user, that.user)) return false;
        if (!Objects.equals(hotel, that.hotel)) return false;
        if (!Objects.equals(people, that.people)) return false;
        if (!Objects.equals(maxCost, that.maxCost)) return false;
        if (!Objects.equals(reserveFrom, that.reserveFrom)) return false;
        if (!Objects.equals(reserveTo, that.reserveTo)) return false;
        if (!Objects.equals(expiryDate, that.expiryDate)) return false;
        return status == that.status;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (people != null ? people.hashCode() : 0);
        result = 31 * result + (maxCost != null ? maxCost.hashCode() : 0);
        result = 31 * result + (reserveFrom != null ? reserveFrom.hashCode() : 0);
        result = 31 * result + (reserveTo != null ? reserveTo.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
