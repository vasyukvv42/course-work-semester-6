package edu.kpi.hotel.model.entity;

import dev.morphia.annotations.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Embedded
public class Invoice {
    @NotNull
    @Positive
    private BigDecimal totalPrice;

    @NotNull
    private Boolean isPaid = false;

    @NotNull
    @FutureOrPresent
    private Date dueDate;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (!Objects.equals(totalPrice, invoice.totalPrice)) return false;
        if (!Objects.equals(isPaid, invoice.isPaid)) return false;
        return Objects.equals(dueDate, invoice.dueDate);

    }

    @Override
    public int hashCode() {
        int result = totalPrice != null ? totalPrice.hashCode() : 0;
        result = 31 * result + (isPaid != null ? isPaid.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        return result;
    }
}
