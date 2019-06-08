package edu.kpi.hotel.controller;

import edu.kpi.hotel.model.exception.NotEnoughBalanceException;
import edu.kpi.hotel.model.service.api.ReservationService;
import edu.kpi.hotel.model.service.api.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationController {

    private ReservationService reservationService;
    private UserService userService;

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String reservations(Model model) {
        var user = userService.currentUser().get();
        model.addAttribute("reservations", reservationService.getReservationsByUser(user));

        return "reservations";
    }

    @RequestMapping(value = "/reservations/{reservationId}/pay-invoice", method = RequestMethod.POST)
    public String payInvoice(Model model, @PathVariable String reservationId) {
        var user = userService.currentUser().get();
        var objectId = new ObjectId(reservationId);
        var reservation = reservationService.getReservationById(objectId).get();

        try {
            reservationService.payInvoice(user, reservation);
        } catch (NotEnoughBalanceException e) {
            model.addAttribute("error", "Not enough balance");
        }

        model.addAttribute("reservations", reservationService.getReservationsByUser(user));
        return "reservations";
    }
}
