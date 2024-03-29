package edu.kpi.hotel.model.exception;

public class HotelException extends Exception {
    public HotelException() {
        super();
    }

    public HotelException(String message) {
        super(message);
    }

    public HotelException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelException(Throwable cause) {
        super(cause);
    }
}
