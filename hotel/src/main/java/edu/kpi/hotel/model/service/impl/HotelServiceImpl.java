package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.dao.api.HotelDao;
import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.service.api.HotelService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao;

    @Autowired
    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    public void createHotel(String name, String country, String city, String address, Integer rating) {
        var hotel = new Hotel();
        hotel.setName(name);
        hotel.setCountry(country);
        hotel.setCity(city);
        hotel.setAddress(address);
        hotel.setRating(rating);

        hotelDao.save(hotel);
    }

    @Override
    public void createRoom(Hotel hotel, Integer number, Integer maxPeople, BigDecimal cost) {
        var room = new Room();
        room.setMaxPeople(maxPeople);
        room.setCost(cost);
        hotel.getRooms().put(number, room);

        hotelDao.save(hotel);
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelDao.findAll();
    }

    @Override
    public Optional<Hotel> getHotelById(ObjectId id) {
        return hotelDao.find(id);
    }

    @Override
    public Map<Integer, Room> getSuitableRooms(RoomRequest request) {
        return hotelDao.findSuitableRoomsByRequest(request);
    }
}
