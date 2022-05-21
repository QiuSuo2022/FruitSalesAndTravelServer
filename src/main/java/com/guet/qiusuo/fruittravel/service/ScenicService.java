package com.guet.qiusuo.fruittravel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guet.qiusuo.fruittravel.bean.vo.*;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.*;
import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.model.Ticket;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ScenicService {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    private ScenicMapper scenicMapper;

    private GoodsService goodsService;
    private OrderFormMapper orderFormMapper;
    @Autowired
    public void setOrderFormMapper(OrderFormMapper orderFormMapper) {
        this.orderFormMapper = orderFormMapper;
    }

    private TicketService ticketService;
    @Autowired
    public void setUploadImgService(UploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }

    private UploadImgService uploadImgService;
    @Autowired
    public void setScenicMapper(ScenicMapper scenicMapper) {
        this.scenicMapper = scenicMapper;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public PageList<ScenicUrlVO> getScenicList(String id,String scenicName,String location,String nameLike,Short type,String description,
                                          String openingHours,Short orderByType, Integer page,Integer pageSize) {
        if (nameLike == null || nameLike.length() == 0) {
            nameLike = "";
        }

        PageHelper.startPage(page,pageSize);
        List<Scenic> scenicList;
        if(orderByType.equals(SystemConstants.PRICE_ASC)) {
            scenicList = scenicMapper.selectScenicSortByPricesASC(nameLike);
        }
        else if (orderByType.equals(SystemConstants.PRICE_DESC)) {
            scenicList = scenicMapper.selectScenicSortByPricesDESC(nameLike);
        }

        else if(orderByType.equals(SystemConstants.SALE_ASC)) {
            scenicList = scenicMapper.selectScenicSortBySalesASC(nameLike);
        }

        else if(orderByType.equals(SystemConstants.SALE_DESC)) {
            scenicList = scenicMapper.selectScenicSortBySalesDESC(nameLike);
        }

        else if(orderByType.equals(SystemConstants.GRADE_ASC)) {
            scenicList = scenicMapper.selectScenicSortByGradesASC(nameLike);
        }

        else if (orderByType.equals(SystemConstants.GRADE_DESC)) {
            scenicList = scenicMapper.selectScenicSortByGradesDESC(nameLike);
        }

        else if(orderByType.equals(SystemConstants.SORT_ALL)) {
            scenicList = scenicMapper.selectScenicSort(nameLike);
        }

        else {
            throw new SystemException(ErrorCode.ORDERBYTYPE_ERROR);
        }
        //将Scenic与图片url封装为ScenicUrlVO
        ArrayList<ScenicUrlVO> scenicUrlVOs = new ArrayList<>();

        for (Scenic s:scenicList) {
            ScenicUrlVO scenicUrlVO = new ScenicUrlVO();
            scenicUrlVO.setId(s.getId());
            scenicUrlVO.setScenicName(s.getScenicName());
            scenicUrlVO.setLocation(s.getLocation());
            scenicUrlVO.setOpeningHours(s.getOpeningHours());
            scenicUrlVO.setDescription(s.getDescription());
            scenicUrlVO.setType(s.getType());
            scenicUrlVO.setStatus(s.getStatus());
            scenicUrlVO.setCreateTime(s.getCreateTime());
            scenicUrlVO.setUpdateTime(s.getUpdateTime());
            scenicUrlVO.setCreateUserId(s.getCreateUserId());
            scenicUrlVO.setUpdateUserId(s.getUpdateUserId());
            scenicUrlVO.setImgUrl(uploadImgService.getUrlByProdId(s.getId()));
            scenicUrlVOs.add(scenicUrlVO);
        }
        PageList<ScenicUrlVO> pageList = new PageList<>();
        pageList.setList(scenicUrlVOs);
        pageList.setPageInfo(new PageInfo<>(scenicUrlVOs));
        LOG.info("获取景区列表成功");
        return pageList;
    }

    /**
     * 根据景区名字获取门票
     * @param scenicName
     * @return
     */
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
    public Scenic addScenic(Scenic scenic) {
        UserContextHolder.validAdmin();
        if (!getScenicByName(scenic.getScenicName()).isEmpty()) {
            //已经存在该景点
            throw new SystemException(ErrorCode.SCENIC_ALREADY_EXITS);
        }
        long now = System.currentTimeMillis();
        scenic.setId(UUID.randomUUID().toString().replace("-", ""));
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
        return scenic;
    }

    /**
     * 删除Scenic(和ticket一起)
     *
     * @param scenicId
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteScenic(String scenicId) {
        UserContextHolder.validAdmin();
        Optional<Scenic> optionalScenic = scenicMapper.selectByPrimaryKey(scenicId);
        Scenic scenic = optionalScenic.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_SCENIC));
        scenic.setStatus(SystemConstants.STATUS_NEGATIVE);
        scenic.setUpdateUserId(UserContextHolder.getUserId());
        scenic.setUpdateTime(System.currentTimeMillis());
        //先删除ticket(若ticket存在)
        if(ticketService.searchTicket(scenic.getId()) != null) {
            ticketService.deleteTicket(scenic.getId());
        }
        //再删除scenic
        int i = scenicMapper.updateByPrimaryKeySelective(scenic);
        if (i == 0) {
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        LOG.info("删除景区{}成功,id={}",scenic.getScenicName(),scenic.getId());
        return true;
    }

    /**
     * 修改Scenic
     *
     * @param scenic
     */
    public boolean updateScenic(Scenic scenic) {
        UserContextHolder.validAdmin();
        scenic.setUpdateTime(System.currentTimeMillis());
        scenic.setUpdateUserId(UserContextHolder.getUserId());
        int i = scenicMapper.updateByPrimaryKeySelective(scenic);
        if (i == 0) {
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("修改景区{}成功,id={}",scenic.getScenicName(),scenic.getId());
        return true;
    }

    /**
     * 查找全部景点
     *
     * @return
     */
    public PageList<ScenicVO> searchAllScenic(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Scenic> scenicList;
        scenicList = scenicMapper.selectMany(select(
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
                        .where(ScenicDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .orderBy(ScenicDynamicSqlSupport.createTime)
                        .build().render(RenderingStrategies.MYBATIS3)
        );
        List<ScenicVO> scenicVOList = new ArrayList<>();
        for(Scenic scenic : scenicList) {
            ScenicVO scenicVO = new ScenicVO();
            scenicVO.setId(scenic.getId());
            scenicVO.setScenicName(scenic.getScenicName());
            scenicVO.setLocation(scenic.getLocation());
            scenicVO.setOpeningHours(scenic.getOpeningHours());
            scenicVO.setDescription(scenic.getDescription());
            scenicVO.setType(scenic.getType());
            scenicVO.setStatus(scenic.getStatus());
            scenicVO.setCreateTime(scenic.getCreateTime());
            scenicVO.setUpdateTime(scenic.getUpdateTime());
            scenicVO.setCreateUserId(scenic.getCreateUserId());
            scenicVO.setUpdateUserId(scenic.getUpdateUserId());

            scenicVO.setImgUrl(uploadImgService.getUrlByProdId(scenicVO.getId()));

            ArrayList<TicketArray> arrays = new ArrayList<>();
            List<Ticket> tickets = ticketService.searchTicket(scenic.getId());
            if(tickets == null) {
                scenicVO.setTicketList(null);
            }
            else {
                for (Ticket ticket : tickets) {
                    TicketArray ticketArray = new TicketArray();
                    ticketArray.setTicketId(ticket.getId());
                    ticketArray.setTicketType(ticket.getType());
                    ticketArray.setTicketDescription(ticket.getDescription());
                    ticketArray.setPrice(ticket.getPrice());
                    arrays.add(ticketArray);
                }
            }
            scenicVO.setTicketList(arrays);
            scenicVOList.add(scenicVO);
        }
        PageList<ScenicVO> pageList = new PageList<>();
        pageList.setList(scenicVOList);
        pageList.setPageInfo(new PageInfo<>(scenicVOList));
        LOG.info("查找所有景区成功");
        return pageList;
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
                ScenicDynamicSqlSupport.updateTime,
                ScenicDynamicSqlSupport.createUserId,
                ScenicDynamicSqlSupport.updateUserId
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
        scenicVO.setImgUrl(uploadImgService.getUrlByProdId(scenicVO.getId()));
        if(ticketService.searchTicket(scenic_id) == null) {
            scenicVO.setTicketList(null);
        }
        else {
            ArrayList<TicketArray> arrays = new ArrayList<>();
            for(Ticket ticket : ticketService.searchTicket(scenic_id)) {
                TicketArray ticketArray = new TicketArray();
                ticketArray.setPrice(ticket.getPrice());
                ticketArray.setTicketType(ticket.getType());
                ticketArray.setTicketDescription(ticket.getDescription());
                ticketArray.setTicketId(ticket.getId());
                arrays.add(ticketArray);
                scenicVO.setTicketList(arrays);
            }
        }
        LOG.info("查找景区{}成功,id={}",scenicVO.getScenicName(),scenicVO.getId());
        return scenicVO;
    }

    /**
     * 获取全部景区对象
     * @return
     */
    public List<ScenicUrlVO> getAllScenic(){
        long now = System.currentTimeMillis();
        //从数据库中选取
        List<Scenic> l = scenicMapper.selectScenic(select(
                ScenicDynamicSqlSupport.id,
                ScenicDynamicSqlSupport.scenicName,
                ScenicDynamicSqlSupport.type,
                ScenicDynamicSqlSupport.updateTime,
                ScenicDynamicSqlSupport.description
                )
                        .from(ScenicDynamicSqlSupport.scenic)
                        .where(ScenicDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .build().render(RenderingStrategies.MYBATIS3)
        );
        //加入到vo类中,为了附加图片url
        ArrayList<ScenicUrlVO> list = new ArrayList<>();
        for (Scenic s:l) {
            ScenicUrlVO scenicUrlVO = new ScenicUrlVO();
            scenicUrlVO.setId(s.getId());
            scenicUrlVO.setScenicName(s.getScenicName());
            scenicUrlVO.setImgUrl(uploadImgService.getUrlByProdId(s.getId()));
            scenicUrlVO.setLocation(s.getLocation());
            scenicUrlVO.setDescription(s.getDescription());
            scenicUrlVO.setType(s.getType());
            scenicUrlVO.setStatus(s.getStatus());
            scenicUrlVO.setOpeningHours(s.getOpeningHours());
            scenicUrlVO.setUpdateTime(s.getUpdateTime());
            scenicUrlVO.setUpdateUserId(s.getUpdateUserId());
            scenicUrlVO.setCreateTime(s.getCreateTime());
            scenicUrlVO.setCreateUserId(s.getCreateUserId());
            scenicUrlVO.setDescription(s.getDescription());
            Long ans = orderFormMapper.count(countFrom(OrderFormDynamicSqlSupport.orderForm)
                    .where(OrderFormDynamicSqlSupport.payStatus,isNotEqualTo(SystemConstants.UNPAID))
                    .and(OrderFormDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                    .and(OrderFormDynamicSqlSupport.payTime,isBetween(SystemConstants.MONTH_MILLIS).and(now))
                    .and(OrderFormDynamicSqlSupport.scenicId,isEqualTo(s.getId()))
                    .build().render(RenderingStrategies.MYBATIS3));
            if (ans == null) {
                ans = 0L;
            }
            scenicUrlVO.setSales(Integer.valueOf(ans.toString()));
            list.add(scenicUrlVO);
        }
        return list;

    }

    /**
     * 获取景区推荐列表
     */
    public List<ScenicUrlVO> getScenicRec() {
        List<ScenicUrlVO> res = new ArrayList<>();
        List<ScenicUrlVO> allScenic = getAllScenic();
            for (int i = 0; i < 6; i++) {
                res.add(allScenic.get(i));
            }
            return res;
    }
}
