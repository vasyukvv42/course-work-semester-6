package edu.kpi.hotel.model.exception;

public class NotEnoughBalanceException extends HotelException {
    public NotEnoughBalanceException() {
    }

    public NotEnoughBalanceException(String message) {
        super(message);
    }

    public NotEnoughBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughBalanceException(Throwable cause) {
        super(cause);
    }
}
