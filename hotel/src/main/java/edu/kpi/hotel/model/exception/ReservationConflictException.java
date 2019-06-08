package edu.kpi.hotel.model.exception;

public class ReservationConflictException extends HotelException {
    public ReservationConflictException() {
    }

    public ReservationConflictException(String message) {
        super(message);
    }

    public ReservationConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservationConflictException(Throwable cause) {
        super(cause);
    }
}
