package edu.kpi.hotel.model.dao.morphia;

import edu.kpi.hotel.model.dao.api.RoomRequestDao;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.entity.RoomRequestStatus;
import edu.kpi.hotel.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRequestMorphiaDao extends MorphiaDao<RoomRequest> implements RoomRequestDao {

    public RoomRequestMorphiaDao() {
        super(RoomRequest.class);
    }

    @Override
    public List<RoomRequest> findPendingRoomRequests() {
        var query = datastore.createQuery(entityClass)
                .filter("status", RoomRequestStatus.PENDING);
        return query.find().toList();
    }

    @Override
    public List<RoomRequest> findRoomRequestsByUser(User user) {
        var query = datastore.createQuery(entityClass)
                .filter("user", user);
        return query.find().toList();
    }
}
