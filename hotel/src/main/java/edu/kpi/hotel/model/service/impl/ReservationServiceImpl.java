package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.dao.api.ReservationDao;
import edu.kpi.hotel.model.dao.api.UserDao;
import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Invoice;
import edu.kpi.hotel.model.entity.Reservation;
import edu.kpi.hotel.model.entity.User;
import edu.kpi.hotel.model.exception.NotEnoughBalanceException;
import edu.kpi.hotel.model.exception.ReservationConflictException;
import edu.kpi.hotel.model.service.api.ReservationService;
import edu.kpi.hotel.model.util.DateUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private UserDao userRepository;
    private ReservationDao reservationRepository;
    private DateUtil dateUtil;

    @Autowired
    public void setReservationRepository(ReservationDao reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Autowired
    public void setUserRepository(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Override
    public void createReservation(User user, Hotel hotel, Integer roomNumber, Date reservedFrom, Date reservedTo)
            throws ReservationConflictException {
        if (reservedFrom == null || reservedTo == null || reservedFrom.after(reservedTo))
            throw new IllegalArgumentException("Invalid reservation date");

        var dueDate = dateUtil.getExpiryDate(reservedFrom);

        if (dueDate.before(new Date()))
            throw new IllegalArgumentException("Invalid reservation date");

        var conflictingReservations = reservationRepository
                .findReservationsInRange(hotel, roomNumber, reservedFrom, reservedFrom);
        if (!conflictingReservations.isEmpty())
            throw new ReservationConflictException("This room is reserved already");

        var invoice = new Invoice();
        invoice.setDueDate(dueDate);

        var room = hotel.getRooms().get(roomNumber);
        var days = dateUtil.getDifferenceInDays(reservedFrom, reservedTo);
        var totalPrice = room.getCost().multiply(BigDecimal.valueOf(days));
        invoice.setTotalPrice(totalPrice);

        var reservation = new Reservation();
        reservation.setUser(user);
        reservation.setHotel(hotel);
        reservation.setRoomNumber(roomNumber);
        reservation.setReservedFrom(reservedFrom);
        reservation.setReservedTo(reservedTo);
        reservation.setInvoice(invoice);

        reservationRepository.save(reservation);
    }

    @Override
    public void payInvoice(User user, Reservation reservation) throws NotEnoughBalanceException {
        var invoice = reservation.getInvoice();
        var totalPrice = invoice.getTotalPrice();

        if (invoice.getPaid())
            throw new IllegalArgumentException("Invoice is already paid");

        var balance = user.getBalance();
        if (balance.compareTo(totalPrice) < 0)
            throw new NotEnoughBalanceException("Not enough balance");

        user.setBalance(balance.subtract(totalPrice));
        userRepository.update(user);

        invoice.setPaid(true);
        reservationRepository.update(reservation);
    }

    @Override
    public List<Reservation> getReservationsByUser(User user) {
        return reservationRepository.findReservationsByUser(user);
    }

    @Override
    public Optional<Reservation> getReservationById(ObjectId id) {
        return reservationRepository.find(id);
    }
}
