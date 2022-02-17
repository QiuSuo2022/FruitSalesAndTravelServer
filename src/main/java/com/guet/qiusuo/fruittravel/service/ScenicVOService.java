package com.guet.qiusuo.fruittravel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guet.qiusuo.fruittravel.bean.vo.ScenicVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.dao.ScenicTicketMapper;
import com.guet.qiusuo.fruittravel.dao.ScenicTicketDynamicSqlSupport;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.*;



@Service
public class ScenicVOService {
    private ScenicTicketMapper scenicTicketMapper;
    public PageList<ScenicVO> getScenicVOList(String ticketId,String nameLike,Integer price,Short type,String description,
                                              String ticketName,Integer page,Integer pageSize) {
        if(nameLike == null || nameLike.length() == 0) {
            nameLike = "";
        }

        PageHelper.startPage(page,pageSize);
        List<ScenicVO> scenicVOList = scenicTicketMapper.selectScenicVO(select(
                ScenicTicketDynamicSqlSupport.id,
                ScenicTicketDynamicSqlSupport.ticketId,
                ScenicTicketDynamicSqlSupport.ticketName,
                ScenicTicketDynamicSqlSupport.type,
                ScenicTicketDynamicSqlSupport.description,
                ScenicTicketDynamicSqlSupport.price,
                ScenicTicketDynamicSqlSupport.createTime
        )
                        .from(ScenicTicketDynamicSqlSupport.scenicVO)
                        .where(ScenicTicketDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .and(ScenicTicketDynamicSqlSupport.ticketName,isLike("%" + nameLike + "%"))
                        .and(ScenicTicketDynamicSqlSupport.description,isEqualToWhenPresent(description))
                        .and(ScenicTicketDynamicSqlSupport.ticketId,isEqualToWhenPresent(ticketId))
                        .orderBy(ScenicTicketDynamicSqlSupport.createTime.descending())
                        .build().render(RenderingStrategies.MYBATIS3)
        );

        PageList<ScenicVO> pageList = new PageList<>();
        pageList.setList(scenicVOList);
        pageList.setPageInfo(new PageInfo<>(scenicVOList));
        return pageList;
    }
}
