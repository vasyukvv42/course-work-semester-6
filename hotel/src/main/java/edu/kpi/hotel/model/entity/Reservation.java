package edu.kpi.hotel.model.entity;

import dev.morphia.annotations.*;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Objects;

@Entity("reservations")
@Indexes({
        // Delete reservations if the invoice is not paid in time
        @Index(fields = @Field("invoice.dueDate"), options = @IndexOptions(expireAfterSeconds = 0,
                partialFilter = "{isPaid: false}"))
})
public class Reservation {
    @Id
    private ObjectId id;

    @NotNull
    private Date reservedFrom;

    @NotNull
    private Date reservedTo;

    @Reference
    private Hotel hotel;

    @PositiveOrZero
    private Integer roomNumber;

    @Embedded
    private Invoice invoice;

    @Reference
    private User user;

    public ObjectId getId() {
        return id;
    }

    public Date getReservedFrom() {
        return reservedFrom;
    }

    public void setReservedFrom(Date reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    public Date getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(Date reservedTo) {
        this.reservedTo = reservedTo;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(reservedFrom, that.reservedFrom)) return false;
        if (!Objects.equals(reservedTo, that.reservedTo)) return false;
        if (!Objects.equals(hotel, that.hotel)) return false;
        if (!Objects.equals(roomNumber, that.roomNumber)) return false;
        if (!Objects.equals(invoice, that.invoice)) return false;
        return Objects.equals(user, that.user);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reservedFrom != null ? reservedFrom.hashCode() : 0);
        result = 31 * result + (reservedTo != null ? reservedTo.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (roomNumber != null ? roomNumber.hashCode() : 0);
        result = 31 * result + (invoice != null ? invoice.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
