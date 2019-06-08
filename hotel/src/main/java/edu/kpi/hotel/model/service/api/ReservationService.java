package edu.kpi.hotel.model.service.api;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Reservation;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.NotEnoughBalanceException;
import edu.kpi.hotel.model.exception.ReservationConflictException;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    void createReservation(User user, Hotel hotel, Integer roomNumber, Date reservedFrom, Date reservedTo)
            throws ReservationConflictException;

    void payInvoice(User user, Reservation reservation) throws NotEnoughBalanceException;

    List<Reservation> getReservationsByUser(User user);

    Optional<Reservation> getReservationById(ObjectId id);
}
