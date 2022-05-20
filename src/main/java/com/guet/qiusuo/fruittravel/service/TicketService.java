package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.TicketDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.TicketMapper;
import com.guet.qiusuo.fruittravel.model.Ticket;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.slf4j.LoggerFactory.getLogger;
@Service
public class TicketService {
    private TicketMapper ticketMapper;
    private static final Logger LOG = getLogger(lookup().lookupClass());

    @Autowired
    public void setTicketMapper(TicketMapper ticketMapper){this.ticketMapper = ticketMapper;}

    /**
     * 添加景点门票
     * @param ticket
     */
    public void addTicket(Ticket ticket) {
        UserContextHolder.validAdmin();
        long now = System.currentTimeMillis();
        ticket.setId(UUID.randomUUID().toString().replace("-", ""));
        ticket.setCreateTime(now);
        ticket.setUpdateTime(now);
        ticket.setStatus(SystemConstants.STATUS_ACTIVE);
        ticket.setCreateUserId(UserContextHolder.getUserId());
        ticket.setUpdateUserId(UserContextHolder.getUserId());
        int i = ticketMapper.insertSelective(ticket);
        if (i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("创建景区门票成功,Id:{}",ticket.getScenicId());
    }

    /**
     *删除景点门票
     * @param ticketId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteTicket(String ticketId){
        UserContextHolder.validAdmin();
        List<Ticket> ticketList = ticketMapper.selectMany(select(
                TicketDynamicSqlSupport.id,
                TicketDynamicSqlSupport.scenicId,
                TicketDynamicSqlSupport.type,
                TicketDynamicSqlSupport.price,
                TicketDynamicSqlSupport.description,
                TicketDynamicSqlSupport.status,
                TicketDynamicSqlSupport.createTime,
                TicketDynamicSqlSupport.updateTime,
                TicketDynamicSqlSupport.createUserId,
                TicketDynamicSqlSupport.updateUserId
        )
                        .from(TicketDynamicSqlSupport.ticket)
                        .where(TicketDynamicSqlSupport.id,isEqualTo(ticketId))
                        .and(TicketDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .build().render(RenderingStrategies.MYBATIS3));
        if(ticketList != null) {
            for (Ticket ticket:ticketList) {
                ticket.setStatus(SystemConstants.STATUS_NEGATIVE);
                ticket.setUpdateUserId(UserContextHolder.getUserId());
                ticket.setUpdateTime(System.currentTimeMillis());
                int i = ticketMapper.updateByPrimaryKey(ticket);
                if(i == 0){
                    throw new SystemException(ErrorCode.DELETE_ERROR);
                }
                LOG.info("删除景区门票成功,ScenicId:{}",ticketId);
            }
        }

    }

    /**
     * 修改景点门票
     * @param ticket
     */
    public void updateTicket(Ticket ticket){
        UserContextHolder.validAdmin();
        ticket.setUpdateTime(System.currentTimeMillis());
        ticket.setUpdateUserId(UserContextHolder.getUserId());
        int i = ticketMapper.updateByPrimaryKeySelective(ticket);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("修改景点门票成功,id={}",ticket.getId());
    }

    /**
     * 查找景点门票
     * @param scenicId
     */
    public List<Ticket> searchTicket(String scenicId){
        List<Ticket> ticketList = ticketMapper.selectMany(select(
                                        TicketDynamicSqlSupport.id,
                                        TicketDynamicSqlSupport.scenicId,
                                        TicketDynamicSqlSupport.type,
                                        TicketDynamicSqlSupport.price,
                                        TicketDynamicSqlSupport.description,
                                        TicketDynamicSqlSupport.status,
                                        TicketDynamicSqlSupport.createTime,
                                        TicketDynamicSqlSupport.updateTime,
                                        TicketDynamicSqlSupport.createUserId,
                                        TicketDynamicSqlSupport.updateUserId
                                    )
                                    .from(TicketDynamicSqlSupport.ticket)
                                    .where(TicketDynamicSqlSupport.scenicId, isEqualTo(scenicId))
                                    .and(TicketDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                                    .build().render(RenderingStrategies.MYBATIS3));
        if(ticketList==null) {
            LOG.info("没有此类门票!");
        }
            return ticketList;
    }

    /**
     * 根据景区id以及门票类型获取当前门票
     * @param scenicId
     * @param type
     * @return
     */
    public Ticket getTicketByType(String scenicId,Short type){
        List<Ticket> ticketsType = ticketMapper.selectMany(select(
                TicketDynamicSqlSupport.price
        )
                .from(TicketDynamicSqlSupport.ticket)
                .where(TicketDynamicSqlSupport.scenicId, isEqualTo(scenicId))
                .and(TicketDynamicSqlSupport.type, isEqualTo(type))
                .and(TicketDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));
        if (ticketsType.isEmpty()){
            LOG.info("没有此种门票类型");
        }
        return ticketsType.get(0);
    }

}
