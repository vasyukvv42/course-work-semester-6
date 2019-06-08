package edu.kpi.hotel.model.dao.morphia;

import edu.kpi.hotel.model.dao.api.HotelDao;
import edu.kpi.hotel.model.entity.Hotel;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.dao.api.ReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class HotelMorphiaDao extends MorphiaDao<Hotel> implements HotelDao {

    private ReservationDao reservationRepository;

    @Autowired
    public void setReservationRepository(ReservationDao reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public HotelMorphiaDao() {
        super(Hotel.class);
    }

    @Override
    public Map<Integer, Room> findSuitableRoomsByRequest(RoomRequest request) {
        var hotel = request.getHotel();

        return hotel.getRooms().entrySet().stream()
                .filter((entry) -> {
                    var room = entry.getValue();
                    var roomNumber = entry.getKey();
                    var conflictingReservations = reservationRepository.findReservationsInRange(
                            hotel,
                            roomNumber,
                            request.getReserveFrom(),
                            request.getReserveTo()
                    );

                    return room.getCost().compareTo(request.getMaxCost()) <= 0
                            && room.getMaxPeople().compareTo(request.getPeople()) >= 0
                            && conflictingReservations.isEmpty();
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
