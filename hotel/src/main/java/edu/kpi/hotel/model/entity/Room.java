package edu.kpi.hotel.model.entity;

import dev.morphia.annotations.Embedded;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Objects;

@Embedded
public class Room {

    @NotNull
    @Positive
    private Integer maxPeople;

    @NotNull
    @PositiveOrZero
    private BigDecimal cost;

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (!Objects.equals(maxPeople, room.maxPeople)) return false;
        return Objects.equals(cost, room.cost);

    }

    @Override
    public int hashCode() {
        int result = maxPeople != null ? maxPeople.hashCode() : 0;
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
