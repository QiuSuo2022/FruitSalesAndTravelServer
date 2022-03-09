package com.guet.qiusuo.fruittravel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guet.qiusuo.fruittravel.bean.vo.FruitVO;
import com.guet.qiusuo.fruittravel.bean.vo.ScenicVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.FruitDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ScenicDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ScenicMapper;
import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.model.Scenic;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ScenicService {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    private ScenicMapper scenicMapper;

    private TicketService ticketService;

    @Autowired
    public void setScenicMapper(ScenicMapper scenicMapper) {
        this.scenicMapper = scenicMapper;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public PageList<Scenic> getScenicList(String id,String scenicName,String location,String nameLike,Short type,String description,
                                          String openingHours,Integer page,Integer pageSize) {
        if (nameLike == null || nameLike.length() == 0) {
            nameLike = "";
        }

        PageHelper.startPage(page,pageSize);
        List<Scenic> scenicList = scenicMapper.selectScenic(select(
                ScenicDynamicSqlSupport.id,
                ScenicDynamicSqlSupport.scenicName,
                ScenicDynamicSqlSupport.location,
                ScenicDynamicSqlSupport.openingHours,
                ScenicDynamicSqlSupport.description,
                ScenicDynamicSqlSupport.type,
                ScenicDynamicSqlSupport.status,
                ScenicDynamicSqlSupport.createTime
                )
                        .from(ScenicDynamicSqlSupport.scenic)
                        .where(ScenicDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .and(ScenicDynamicSqlSupport.scenicName,isLike("%" + nameLike + "%"))
                        .orderBy(ScenicDynamicSqlSupport.createTime.descending())
                        .build().render(RenderingStrategies.MYBATIS3)
        );

        PageList<Scenic> pageList = new PageList<>();
        pageList.setList(scenicList);
        pageList.setPageInfo(new PageInfo<>(scenicList));
        return pageList;
    }
    private List<Scenic> getScenicByName(String scenicName) {
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
                .where(ScenicDynamicSqlSupport.scenicName, isEqualTo(scenicName))
                .and(ScenicDynamicSqlSupport.status, isNotEqualTo(SystemConstants.STATUS_NEGATIVE))
                .build().render(RenderingStrategies.MYBATIS3));
    }

    /**
     * 添加Scenic
     *
     * @param scenic
     */
    public void addScenic(Scenic scenic) {
        UserContextHolder.validAdmin();
        if (getScenicByName(scenic.getScenicName()).get(0) != null) {
            //已经存在该景点
            throw new SystemException(ErrorCode.SCENIC_ALREADY_EXITS);
        }
        long now = System.currentTimeMillis();
        scenic.setCreateTime(now);
        scenic.setUpdateTime(now);
        scenic.setUpdateUserId(UserContextHolder.getUserId());
        scenic.setCreateUserId(UserContextHolder.getUserId());
        /*
         * 1.如果前端中 Scenic与Ticket一起提交的时候,直接使用STATUS_ACTIVE
         * 2.否则,设置为TICKET_INFO_NOT_COMPLETE
         * */
        //假设一起提交
        scenic.setStatus(SystemConstants.STATUS_ACTIVE);
        //没有一起提交
        //ticket.setStatus(SystemConstants.SCENIC_INFO_NOT_COMPLETE);
        int i = scenicMapper.insertSelective(scenic);
        if (i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("景点{}添加成功", scenic.getScenicName());
    }

    /**
     * 删除Scenic(和ticket一起)
     *
     * @param scenicId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteScenic(String scenicId) {
        UserContextHolder.validAdmin();
        Optional<Scenic> optionalScenic = scenicMapper.selectByPrimaryKey(scenicId);
        Scenic scenic = optionalScenic.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_CHILD_FRUIT));
        scenic.setStatus(SystemConstants.STATUS_NEGATIVE);
        scenic.setUpdateUserId(UserContextHolder.getUserId());
        scenic.setUpdateTime(System.currentTimeMillis());
        //先删除ticket
        ticketService.deleteTicket(scenic.getId());
        //再删除scenic
        int i = scenicMapper.deleteByPrimaryKey(scenic.getId());
        if (i == 0) {
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
    }

    /**
     * 修改Scenic
     *
     * @param scenic
     */
    public void updateScenic(Scenic scenic) {
        UserContextHolder.validAdmin();
        scenic.setUpdateTime(System.currentTimeMillis());
        scenic.setUpdateUserId(UserContextHolder.getUserId());
        int i = scenicMapper.updateByPrimaryKeySelective(scenic);
        if (i == 0) {
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 查找Scenic
     *
     * @param scenicId
     *
     * @return
     */
    public Scenic searchScenic(String scenicId) {
        return scenicMapper.selectByPrimaryKey(scenicId).orElse(null);
    }

    /**
     * 查找全部景点
     *
     * @return
     */
    public List<Scenic> searchAllScenic() {
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
                .where(ScenicDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_NEGATIVE))
                .and(ScenicDynamicSqlSupport.status, isNotEqualTo(SystemConstants.STATUS_NEGATIVE))
                .build().render(RenderingStrategies.MYBATIS3));
    }

    /**
     * 根据scenic_id查找ScenicVO
     * @param scenic_id
     * @return
     */
    public ScenicVO getScenicVOByScenicId(String scenic_id) {
        List<Scenic> scenicList = scenicMapper.selectMany(select(
                ScenicDynamicSqlSupport.id,
                ScenicDynamicSqlSupport.scenicName,
                ScenicDynamicSqlSupport.location,
                ScenicDynamicSqlSupport.openingHours,
                ScenicDynamicSqlSupport.description,
                ScenicDynamicSqlSupport.type,
                ScenicDynamicSqlSupport.status,
                ScenicDynamicSqlSupport.createTime,
                FruitDynamicSqlSupport.updateTime,
                FruitDynamicSqlSupport.createUserId,
                FruitDynamicSqlSupport.updateUserId
        )
                        .from(ScenicDynamicSqlSupport.scenic)
                        .where(ScenicDynamicSqlSupport.id, isEqualTo(scenic_id))
                        .and(ScenicDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .build().render(RenderingStrategies.MYBATIS3));

        if (scenicList.isEmpty()){
            return null;
        }
        Scenic scenic = scenicList.get(0);
        ScenicVO scenicVO = new ScenicVO();
        scenicVO.setId(scenic.getId());
        scenicVO.setScenicName(scenic.getScenicName());
        scenicVO.setLocation(scenic.getLocation());
        scenicVO.setDescription(scenic.getDescription());
        scenicVO.setOpeningHours(scenic.getOpeningHours());
        scenicVO.setType(scenic.getType());
        scenicVO.setStatus(scenic.getStatus());
        scenicVO.setCreateTime(scenic.getCreateTime());
        scenicVO.setUpdateTime(scenic.getUpdateTime());
        scenicVO.setCreateUserId(scenic.getUpdateUserId());

        scenicVO.setTicketId(ticketService.searchTicket(scenic_id).getScenicId());
        scenicVO.setPrice(ticketService.searchTicket(scenic_id).getPrice());
        scenicVO.setTicketType(ticketService.searchTicket(scenic_id).getType());
        scenicVO.setTicketDescription(ticketService.searchTicket(scenic_id).getDescription());
        return scenicVO;
    }
}
