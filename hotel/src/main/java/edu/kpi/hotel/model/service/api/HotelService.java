package edu.kpi.hotel.model.service.api;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface HotelService {
    void createHotel(String name, String country, String city, String address, Integer rating);

    void createRoom(Hotel hotel, Integer number, Integer maxPeople, BigDecimal cost);

    List<Hotel> getHotels();
    Optional<Hotel> getHotelById(ObjectId id);
    Map<Integer, Room> getSuitableRooms(RoomRequest request);
}
