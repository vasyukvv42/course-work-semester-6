package edu.kpi.hotel.controller;

import edu.kpi.hotel.controller.dto.RoomRequestDto;
import edu.kpi.hotel.model.entity.Room;
import edu.kpi.hotel.model.entity.RoomRequest;
import edu.kpi.hotel.model.exception.ReservationConflictException;
import edu.kpi.hotel.model.service.api.HotelService;
import edu.kpi.hotel.model.service.api.ReservationService;
import edu.kpi.hotel.model.service.api.RoomRequestService;
import edu.kpi.hotel.model.service.api.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RoomRequestController {

    private RoomRequestService roomRequestService;
    private UserService userService;
    private HotelService hotelService;
    private ReservationService reservationService;

    @Autowired
    public void setRoomRequestService(RoomRequestService roomRequestService) {
        this.roomRequestService = roomRequestService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public String userRoomRequests(Model model) {
        var user = userService.currentUser().get();
        model.addAttribute("requests", roomRequestService.getRoomRequestsByUser(user));

        return "requests";
    }

    @RequestMapping(value = "/admin/requests", method = RequestMethod.GET)
    public String adminRoomRequests(Model model) {
        var requests = roomRequestService.getPendingRoomRequests();
        var suitableRooms = new HashMap<RoomRequest, Map<Integer, Room>>();

        for (var roomRequest : requests) {
            var rooms = hotelService.getSuitableRooms(roomRequest);
            suitableRooms.put(roomRequest, rooms);
        }

        model.addAttribute("requests", requests);
        model.addAttribute("suitableRooms", suitableRooms);

        return "requests";
    }

    @RequestMapping(value = "/admin/{roomRequestId}/accept-request/{roomNumber}", method = RequestMethod.POST)
    public String acceptRoomRequest(@PathVariable String roomRequestId,
                                    @PathVariable String roomNumber,
                                    Model model) {
        var objectId = new ObjectId(roomRequestId);
        var request = roomRequestService.getRoomRequestById(objectId).get();
        var number = Integer.valueOf(roomNumber);
        var user = userService.currentUser();

        try {
            reservationService.createReservation(
                    request.getUser(),
                    request.getHotel(),
                    number,
                    request.getReserveFrom(),
                    request.getReserveTo()
            );
        } catch (ReservationConflictException ignored) { }
        roomRequestService.acceptRoomRequest(request);

        return "redirect:/admin/requests";
    }

    @RequestMapping(value = "/{hotelId}/create-request", method = RequestMethod.POST)
    public String createRoomRequest(@PathVariable String hotelId,
                                    @ModelAttribute("request")
                                    @Valid RoomRequestDto requestDto,
                                    BindingResult result,
                                    Model model) {
        System.out.println(requestDto.getReserveFrom());

        if (!result.hasErrors()) {
            var user = userService.currentUser().get();
            var objectId = new ObjectId(hotelId);
            var hotel = hotelService.getHotelById(objectId).get();
            roomRequestService.createRoomRequest(
                    user,
                    hotel,
                    requestDto.getPeople(),
                    requestDto.getMaxCost(),
                    requestDto.getReserveFrom(),
                    requestDto.getReserveTo()
            );

            return "redirect:/hotels";
        }

        model.addAttribute("hotels", hotelService.getHotels());
        model.addAttribute("error", "Validation failed");
        return "hotels";
    }
}
