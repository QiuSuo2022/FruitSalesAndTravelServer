package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.model.Reservation;
import com.guet.qiusuo.fruittravel.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "门票预订表")
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;
    @Autowired
    public void setReservationService(ReservationService reservationService) {this.reservationService = reservationService;}

    @ApiOperation(value = "预定门票")
    @PostMapping
    public void addReservation(@RequestBody Reservation reservation) {reservationService.addReservation(reservation);}

    @ApiOperation(value = "退订门票")
    @DeleteMapping
    public void deleteReservation(@RequestBody String Id) {reservationService.deleteReservation(Id);}

    @ApiOperation(value = "修改预定信息")
    @PutMapping
    public void updateReservation(@RequestBody Reservation reservation) {reservationService.updateReservation(reservation);}

    @ApiOperation(value = "查找预定信息")
    @GetMapping
    public Reservation searchReservation(@RequestBody String Id) {return reservationService.searchReservation(Id);}

    @ApiOperation(value = "查找全部预定信息")
    @GetMapping("/getAllReservation")
    public List<Reservation> searchAllReservation() {return reservationService.searchAllReservation();}

}
