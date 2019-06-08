package edu.kpi.hotel.model.dao.api;

import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.entity.User;

import java.util.List;

public interface RoomRequestDao extends Dao<RoomRequest> {
    List<RoomRequest> findPendingRoomRequests();
    List<RoomRequest> findRoomRequestsByUser(User user);
}
