package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.TicketDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.TicketMapper;
import com.guet.qiusuo.fruittravel.model.Ticket;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class TicketService {
    private TicketMapper ticketMapper;


    @Autowired
    public void setTicketMapper(TicketMapper ticketMapper){this.ticketMapper = ticketMapper;}

    /**
     * 添加景点门票
     * @param ticket
     */
    public void addTicket(Ticket ticket) {
        long now = System.currentTimeMillis();
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")) {
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        ticket.setId(UUID.randomUUID().toString());
        ticket.setCreateTime(now);
        ticket.setUpdateTime(now);
        ticket.setStatus(SystemConstants.STATUS_ACTIVE);
        int i = ticketMapper.insertSelective(ticket);
        if (i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        System.out.println("创建景点门票成功:" + ticket.toString());
    }

    /**
     *删除景点门票
     * @param scenicId
     */
    public void deleteTicket(String scenicId){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        Optional<Ticket> optionalTicket = ticketMapper.selectByPrimaryKey(scenicId);
        Ticket ticket = optionalTicket.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_TICKET));
        ticket.setStatus(SystemConstants.STATUS_NEGATIVE);
        ticket.setUpdateTime(System.currentTimeMillis());
        ticketMapper.deleteByPrimaryKey(scenicId);
    }

    /**
     * 修改景点门票
     * @param ticket
     */
    public void updateTicket(Ticket ticket){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        ticket.setUpdateTime(System.currentTimeMillis());
        ticketMapper.updateByPrimaryKeySelective(ticket);
    }

    /**
     * 查找景点门票
     * @param scenicId
     */
    public Ticket searchTicket(String scenicId){
        return ticketMapper.selectByPrimaryKey(scenicId).orElse(null);
    }
}
