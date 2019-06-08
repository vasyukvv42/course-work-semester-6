package edu.kpi.hotel.model.dao.api;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;

import java.util.Map;

public interface HotelDao extends Dao<Hotel> {
    Map<Integer, Room> findSuitableRoomsByRequest(RoomRequest request);
}
