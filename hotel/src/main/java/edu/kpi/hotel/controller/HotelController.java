package edu.kpi.hotel.controller;

import edu.kpi.hotel.controller.dto.HotelDto;
import edu.kpi.hotel.controller.dto.RoomDto;
import edu.kpi.hotel.controller.dto.RoomRequestDto;
import edu.kpi.hotel.model.service.api.HotelService;
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

@Controller
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(value = "/hotels", method = RequestMethod.GET)
    public String hotels(Model model) {
        model.addAttribute("hotels", hotelService.getHotels());
        model.addAttribute("hotel", new HotelDto());
        model.addAttribute("room", new RoomDto());
        model.addAttribute("request", new RoomRequestDto());

        return "hotels";
    }

    @RequestMapping(value = "/admin/create-hotel", method = RequestMethod.POST)
    public String createHotel(@ModelAttribute("hotel") @Valid HotelDto hotelDto,
                              BindingResult result, Model model) {
        if (!result.hasErrors()) {
            hotelService.createHotel(
                    hotelDto.getName(),
                    hotelDto.getCountry(),
                    hotelDto.getCity(),
                    hotelDto.getAddress(),
                    hotelDto.getRating()
            );
            return "redirect:/hotels";
        }

        model.addAttribute("hotels", hotelService.getHotels());
        model.addAttribute("hotel", hotelDto);
        model.addAttribute("room", new RoomDto());

        return "hotels";
    }

    @RequestMapping(value = "/admin/{hotelId}/create-room", method = RequestMethod.POST)
    public String createRoom(@PathVariable String hotelId,
                             @ModelAttribute("room") @Valid RoomDto roomDto,
                             BindingResult result,
                             Model model) {
        if (!result.hasErrors()) {
            var objectId = new ObjectId(hotelId);
            var hotel = hotelService.getHotelById(objectId);
            hotelService.createRoom(hotel.get(), roomDto.getNumber(), roomDto.getMaxPeople(), roomDto.getCost());
            return "redirect:/hotels";
        }

        model.addAttribute("hotels", hotelService.getHotels());
        model.addAttribute("hotel", new HotelDto());
        model.addAttribute("room", new RoomDto());
        model.addAttribute("error", "Validation failed");
        return "hotels";
    }
}
