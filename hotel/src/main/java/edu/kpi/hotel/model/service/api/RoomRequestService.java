package edu.kpi.hotel.model.service.api;

import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.entity.User;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomRequestService {
    void createRoomRequest(User user,
                           Hotel hotel,
                           Integer people,
                           BigDecimal maxCost,
                           Date reserveFrom,
                           Date reserveTo);

    void acceptRoomRequest(RoomRequest request);

    void declineRoomRequest(RoomRequest request);

    List<RoomRequest> getPendingRoomRequests();
    List<RoomRequest> getRoomRequestsByUser(User user);

    Optional<RoomRequest> getRoomRequestById(ObjectId id);
}
