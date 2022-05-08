package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.model.Ticket;
import com.guet.qiusuo.fruittravel.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "景点门票表")
@RestController
@RequestMapping("/ticket")
public class TicketController {
    private TicketService ticketService;
    @Autowired
    public void setTicketService(TicketService ticketService) {this.ticketService = ticketService;}

    @ApiOperation(value = "添加景点门票",notes = "门票类型, 1--成人票, 2--儿童票, 3--老人票")
    @PostMapping
    public void addTicket(@RequestBody Ticket ticket) {ticketService.addTicket(ticket);}

    @ApiOperation(value = "删除景点门票",notes = "门票类型, 1--成人票, 2--儿童票, 3--老人票")
    @DeleteMapping
    public void deleteTicket(@RequestParam String ticketId) {ticketService.deleteTicket(ticketId);}

    @ApiOperation(value = "修改景区门票",notes = "门票类型, 1--成人票, 2--儿童票, 3--老人票")
    @PutMapping
    public void updateTicket(@RequestBody Ticket ticket) {ticketService.updateTicket(ticket);}

    @ApiOperation(value = "查找景区门票")
    @GetMapping
    public List<Ticket> searchTicket(@RequestParam String scenicId) {return ticketService.searchTicket(scenicId);}

}
