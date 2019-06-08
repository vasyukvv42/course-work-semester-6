package edu.kpi.hotel.controller.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

public class RoomRequestDto {
    @Positive
    @NotNull
    private Integer people;

    @PositiveOrZero
    @NotNull
    private BigDecimal maxCost;

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date reserveFrom;

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date reserveTo;

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
}
