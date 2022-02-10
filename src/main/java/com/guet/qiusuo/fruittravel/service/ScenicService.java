package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ScenicDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ScenicMapper;
import com.guet.qiusuo.fruittravel.model.Scenic;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class ScenicService {
    private ScenicMapper scenicMapper;
    private TicketService ticketService;

    @Autowired
    public void setScenicMapper(ScenicMapper scenicMapper){this.scenicMapper = scenicMapper;}

    @Autowired
    public void setTicketService(TicketService ticketService){this.ticketService = ticketService;}

    private List<Scenic> getScenicByName(String scenicName){
        return scenicMapper.selectMany(select(
                ScenicDynamicSqlSupport.id,
                ScenicDynamicSqlSupport.scenicName,
                ScenicDynamicSqlSupport.location,
                ScenicDynamicSqlSupport.openingHours,
                ScenicDynamicSqlSupport.description,
                ScenicDynamicSqlSupport.type,
                ScenicDynamicSqlSupport.status,
                ScenicDynamicSqlSupport.createTime,
                ScenicDynamicSqlSupport.updateTime,
                ScenicDynamicSqlSupport.createUserId,
                ScenicDynamicSqlSupport.updateUserId
        )
                .from(ScenicDynamicSqlSupport.scenic)
                .where(ScenicDynamicSqlSupport.scenicName,isEqualTo(scenicName))
                .and(ScenicDynamicSqlSupport.status,isNotEqualTo(SystemConstants.STATUS_NEGATIVE))
                .build().render(RenderingStrategies.MYBATIS3));
    }
    /**
     * 添加Scenic
     * @param scenic
     */
    public void addScenic(Scenic scenic){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        if(getScenicByName(scenic.getScenicName()).get(0) != null){
            //已经存在该景点
            throw new SystemException(ErrorCode.SCENIC_ALREADY_EXITS);
        }
        else{
            long now = System.currentTimeMillis();
            scenic.setCreateTime(now);
            scenic.setUpdateTime(now);
            /*
             * 1.如果前端中 Scenic与Ticket一起提交的时候,直接使用STATUS_ACTIVE
             * 2.否则,设置为TICKET_INFO_NOT_COMPLETE
             * */
            //假设一起提交
            scenic.setStatus(SystemConstants.STATUS_ACTIVE);
            //没有一起提交
            //ticket.setStatus(SystemConstants.SCENIC_INFO_NOT_COMPLETE);
            int i = scenicMapper.insertSelective(scenic);
            if(i == 0){
                throw new SystemException(ErrorCode.INSERT_ERROR);
            }
            System.out.println("创建新景点成功:" + scenic.toString());
        }


    }

    /**
     * 删除Scenic(和ticket一起)
     * @param scenicId
     */
    public void deleteScenic(String scenicId){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        Optional<Scenic> optionalScenic = scenicMapper.selectByPrimaryKey(scenicId);
        Scenic scenic = optionalScenic.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_CHILD_FRUIT));
        scenic.setStatus(SystemConstants.STATUS_NEGATIVE);
        scenic.setUpdateTime(System.currentTimeMillis());
        //先删除ticket
        ticketService.deleteTicket(scenic.getId());
        //再删除scenic
        scenicMapper.deleteByPrimaryKey(scenic.getId());
    }
    /**
     * 修改Scenic
     * @param scenic
     */
    public void updateScenic(Scenic scenic){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        scenic.setUpdateTime(System.currentTimeMillis());
        int i = scenicMapper.updateByPrimaryKeySelective(scenic);
        if (i == 0) {
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }
    /**
     * 查找Scenic
     * @param scenicId
     * @return
     */
    public Scenic searchScenic(String scenicId){
        return scenicMapper.selectByPrimaryKey(scenicId).orElse(null);
    }

    /**
     * 查找全部景点
     * @return
     */
    public List<Scenic> searchAllScenic(){
        return scenicMapper.selectMany(select(
                ScenicDynamicSqlSupport.id,
                ScenicDynamicSqlSupport.scenicName,
                ScenicDynamicSqlSupport.location,
                ScenicDynamicSqlSupport.openingHours,
                ScenicDynamicSqlSupport.description,
                ScenicDynamicSqlSupport.type,
                ScenicDynamicSqlSupport.status,
                ScenicDynamicSqlSupport.createTime,
                ScenicDynamicSqlSupport.updateTime,
                ScenicDynamicSqlSupport.createUserId,
                ScenicDynamicSqlSupport.updateUserId
        )
                .from(ScenicDynamicSqlSupport.scenic)
                .where(ScenicDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_NEGATIVE))
                .and(ScenicDynamicSqlSupport.status,isNotEqualTo(SystemConstants.STATUS_NEGATIVE))
                .build().render(RenderingStrategies.MYBATIS3));
    }
}
