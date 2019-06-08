package edu.kpi.hotel.model.dao.api;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Reservation;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.User;

import java.util.Date;
import java.util.List;

public interface ReservationDao extends Dao<Reservation> {
    List<Reservation> findReservationsByUser(User user);

    Room findRoomByReservation(Reservation reservation);

    List<Reservation> findReservationsInRange(Hotel hotel, Integer roomNumber, Date from, Date to);
}
