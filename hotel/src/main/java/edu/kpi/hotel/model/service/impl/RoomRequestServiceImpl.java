package edu.kpi.hotel.model.service.impl;

import edu.kpi.hotel.model.dao.api.RoomRequestDao;
import edu.kpi.hotel.model.entity.*;
import edu.kpi.hotel.model.service.api.RoomRequestService;
import edu.kpi.hotel.model.util.DateUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomRequestServiceImpl implements RoomRequestService {

    private RoomRequestDao roomRequestRepository;
    private DateUtil dateUtil;

    @Autowired
    public void setRoomRequestRepository(RoomRequestDao roomRequestRepository) {
        this.roomRequestRepository = roomRequestRepository;
    }

    @Autowired
    public void setDateUtil(DateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @Override
    public void createRoomRequest(User user,
                                  Hotel hotel,
                                  Integer people,
                                  BigDecimal maxCost,
                                  Date reserveFrom,
                                  Date reserveTo) {

        if (reserveFrom == null || reserveTo == null || reserveFrom.after(reserveTo))
            throw new IllegalArgumentException("Invalid reservation date");

        var expiryDate = dateUtil.getExpiryDate(reserveFrom);

        if (expiryDate.before(new Date()))
            throw new IllegalArgumentException("Invalid reservation date");

        var request = new RoomRequest();
        request.setUser(user);
        request.setHotel(hotel);
        request.setPeople(people);
        request.setMaxCost(maxCost);
        request.setReserveFrom(reserveFrom);
        request.setReserveTo(reserveTo);
        request.setExpiryDate(expiryDate);

        roomRequestRepository.save(request);
    }

    @Override
    public void acceptRoomRequest(RoomRequest request) {
        request.setStatus(RoomRequestStatus.ACCEPTED);
        roomRequestRepository.update(request);
    }

    @Override
    public void declineRoomRequest(RoomRequest request) {
        request.setStatus(RoomRequestStatus.DECLINED);
        roomRequestRepository.update(request);
    }

    @Override
    public List<RoomRequest> getPendingRoomRequests() {
        return roomRequestRepository.findPendingRoomRequests();
    }

    @Override
    public List<RoomRequest> getRoomRequestsByUser(User user) {
        return roomRequestRepository.findRoomRequestsByUser(user);
    }

    @Override
    public Optional<RoomRequest> getRoomRequestById(ObjectId id) {
        return roomRequestRepository.find(id);
    }


}
