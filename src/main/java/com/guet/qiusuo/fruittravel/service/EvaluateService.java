package com.guet.qiusuo.fruittravel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guet.qiusuo.fruittravel.bean.vo.FruitEvaluateVO;
import com.guet.qiusuo.fruittravel.bean.vo.ScenicEvaluateVO;
import com.guet.qiusuo.fruittravel.bean.vo.ScenicEvaluateVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.EvaluateDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.EvaluateMapper;
import com.guet.qiusuo.fruittravel.model.Evaluate;
import com.guet.qiusuo.fruittravel.model.Fruit;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author ss
 * @date 2022/3/14
 */
@Service
public class EvaluateService {
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private EvaluateMapper evaluateMapper;

    @Autowired
    public void setEvaluateMapper(EvaluateMapper evaluateMapper) {
        this.evaluateMapper = evaluateMapper;
    }

    /**
     * 添加Evaluate(主评)
     *
     * @param evaluate
     */
    public void addFruitEvaluate(Evaluate evaluate) {
        UserContextHolder.validUser(UserContextHolder.getUserId());
        long now = System.currentTimeMillis();
        evaluate.setId(UUID.randomUUID().toString().replace("-", ""));
        evaluate.setCreateTime(now);
        evaluate.setUpdateTime(now);
        evaluate.setEvaluateId(evaluate.getId());
        evaluate.setStatus(SystemConstants.STATUS_ACTIVE);
        evaluate.setCreateUserId(UserContextHolder.getUserId());
        evaluate.setUpdateUserId(UserContextHolder.getUserId());
        int i = evaluateMapper.insertSelective(evaluate);
        if (i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("评价水果成功,Id:{}", evaluate.getId());
    }

    /**
     * 添加主评的追评(evaluateId为主评的Id)
     *
     * @param evaluateId
     */
    public void addFruitReevaluate(String evaluateId) {
        Evaluate reEvaluate = new Evaluate();
        long now = System.currentTimeMillis();
        reEvaluate.setStatus(SystemConstants.STATUS_ACTIVE);
        reEvaluate.setEvaluateId(evaluateId);
        reEvaluate.setId(UUID.randomUUID().toString().replace("-", ""));
        reEvaluate.setCreateTime(now);
        reEvaluate.setUpdateTime(now);
        reEvaluate.setCreateUserId(UserContextHolder.getUserId());
        reEvaluate.setUpdateUserId(UserContextHolder.getUserId());
        int i = evaluateMapper.insertSelective(reEvaluate);
        if (i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("添加追评成功,Id:{}", evaluateId);
    }

    /**
     * 删除追评
     *
     * @param evaluateId
     */
    public void deleteFruitReEvaluate(String evaluateId) {
        UserContextHolder.validUser(UserContextHolder.getUserId());
        List<Evaluate> evaluateList = evaluateMapper.selectMany(select(
                EvaluateDynamicSqlSupport.id,
                EvaluateDynamicSqlSupport.userId,
                EvaluateDynamicSqlSupport.productId,
                EvaluateDynamicSqlSupport.evaluateId,
                EvaluateDynamicSqlSupport.detail,
                EvaluateDynamicSqlSupport.grade,
                EvaluateDynamicSqlSupport.type,
                EvaluateDynamicSqlSupport.status,
                EvaluateDynamicSqlSupport.createTime,
                EvaluateDynamicSqlSupport.updateTime,
                EvaluateDynamicSqlSupport.createUserId,
                EvaluateDynamicSqlSupport.updateUserId
        )
                .from(EvaluateDynamicSqlSupport.evaluate)
                .where(EvaluateDynamicSqlSupport.evaluateId, isEqualTo(evaluateId))
                .and(EvaluateDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(EvaluateDynamicSqlSupport.type, isEqualTo(SystemConstants.REEVALUATE_TYPE))
                .build().render(RenderingStrategies.MYBATIS3));
        if (evaluateList.isEmpty()) {
            throw new SystemException(ErrorCode.NO_FOUND_EVALUATE);
        }
        for(Evaluate evaluate : evaluateList) {
            evaluate.setStatus(SystemConstants.STATUS_NEGATIVE);
            evaluate.setUpdateUserId(UserContextHolder.getUserId());
            evaluate.setUpdateTime(System.currentTimeMillis());
            int i = evaluateMapper.updateByPrimaryKeySelective(evaluate);
            if (i == 0) {
                throw new SystemException(ErrorCode.DELETE_ERROR);
            }
            LOG.info("删除追评成功,Id:{}", evaluateId);
        }
    }


    /**
     * 删除评价(连带追评一起删除)
     *
     * @param UUID
     */
    public void deleteFruitEvaluate(String UUID) {
        UserContextHolder.validUser(UserContextHolder.getUserId());
        Optional<Evaluate> optionalEvaluate = evaluateMapper.selectByPrimaryKey(UUID);
        Evaluate evaluate = optionalEvaluate.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_EVALUATE));
        evaluate.setStatus(SystemConstants.STATUS_NEGATIVE);
        evaluate.setUpdateUserId(UserContextHolder.getUserId());
        evaluate.setUpdateTime(System.currentTimeMillis());
        //先删除追评
        deleteFruitReEvaluate(evaluate.getId());
        //再删除主评
        int i = evaluateMapper.updateByPrimaryKeySelective(evaluate);
        if (i == 0) {
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
    }

    /**
     * 修改评价
     *
     * @param evaluate
     */
    public void updateEvaluate(Evaluate evaluate) {
        UserContextHolder.validUser(UserContextHolder.getUserId());
        evaluate.setUpdateTime(System.currentTimeMillis());
        evaluate.setUpdateUserId(UserContextHolder.getUserId());
        int i = evaluateMapper.updateByPrimaryKeySelective(evaluate);
        if (i == 0) {
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 根据evaluateId查询追评
     *
     * @param evaluateId
     * @return
     */
    public List<Evaluate> searchReevaluate(String evaluateId) {
         return evaluateMapper.selectMany(select(
                EvaluateDynamicSqlSupport.id,
                EvaluateDynamicSqlSupport.userId,
                EvaluateDynamicSqlSupport.productId,
                EvaluateDynamicSqlSupport.evaluateId,
                EvaluateDynamicSqlSupport.detail,
                EvaluateDynamicSqlSupport.grade,
                EvaluateDynamicSqlSupport.type,
                EvaluateDynamicSqlSupport.status,
                EvaluateDynamicSqlSupport.createTime,
                EvaluateDynamicSqlSupport.updateTime,
                EvaluateDynamicSqlSupport.createUserId,
                EvaluateDynamicSqlSupport.updateUserId
        )
                        .from(EvaluateDynamicSqlSupport.evaluate)
                        .where(EvaluateDynamicSqlSupport.evaluateId, isEqualTo(evaluateId))
                         .and(EvaluateDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .and(EvaluateDynamicSqlSupport.type, isEqualTo(SystemConstants.REEVALUATE_TYPE))
                        .build().render(RenderingStrategies.MYBATIS3));

    }
    /**
     * 查询水果评价(包括主评和追评)
     *
     * @param evaluateId
     * @return
     */
    public FruitEvaluateVO searchFruitEvaluate(String evaluateId) {
        List<Evaluate> evaluateList = evaluateMapper.selectMany(select(
                EvaluateDynamicSqlSupport.id,
                EvaluateDynamicSqlSupport.userId,
                EvaluateDynamicSqlSupport.productId,
                EvaluateDynamicSqlSupport.evaluateId,
                EvaluateDynamicSqlSupport.detail,
                EvaluateDynamicSqlSupport.grade,
                EvaluateDynamicSqlSupport.type,
                EvaluateDynamicSqlSupport.status,
                EvaluateDynamicSqlSupport.createTime,
                EvaluateDynamicSqlSupport.updateTime,
                EvaluateDynamicSqlSupport.createUserId,
                EvaluateDynamicSqlSupport.updateUserId
        )
                        .from(EvaluateDynamicSqlSupport.evaluate)
                        .where(EvaluateDynamicSqlSupport.id, isEqualTo(evaluateId))
                        .and(EvaluateDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .and(EvaluateDynamicSqlSupport.type, isEqualTo(SystemConstants.FRUIT_EVALUATE_TYPE))
                        .orderBy(EvaluateDynamicSqlSupport.createTime)
                        .build().render(RenderingStrategies.MYBATIS3));
        if (evaluateList.isEmpty()) {
            return null;
        }
        //获取符合查询条件的水果主评
        Evaluate evaluate = evaluateList.get(0);
        FruitEvaluateVO fruitEvaluateVO = new FruitEvaluateVO();
        fruitEvaluateVO.setId(evaluate.getId());
        fruitEvaluateVO.setEvaluateId(evaluate.getEvaluateId());
        fruitEvaluateVO.setProductId(evaluate.getProductId());
        fruitEvaluateVO.setDetail(evaluate.getDetail());
        fruitEvaluateVO.setGrade(evaluate.getGrade());
        fruitEvaluateVO.setType(evaluate.getType());
        fruitEvaluateVO.setStatus(evaluate.getStatus());
        fruitEvaluateVO.setCreateTime(evaluate.getCreateTime());
        fruitEvaluateVO.setUpdateTime(evaluate.getUpdateTime());
        fruitEvaluateVO.setCreateUserId(evaluate.getUpdateUserId());

        if(!searchReevaluate(evaluateId).isEmpty()) {
            fruitEvaluateVO.setFruitReevaluate(searchReevaluate(evaluateId));
        }
        else {
            fruitEvaluateVO.setFruitReevaluate(null);
        }
        return fruitEvaluateVO;
    }

    /**
     * 查询景区评价(包括主评和追评)
     *
     * @param evaluateId
     * @return
     */
    public ScenicEvaluateVO searchScenicEvaluate(String evaluateId) {
        List<Evaluate> evaluateList = evaluateMapper.selectMany(select(
                EvaluateDynamicSqlSupport.id,
                EvaluateDynamicSqlSupport.userId,
                EvaluateDynamicSqlSupport.productId,
                EvaluateDynamicSqlSupport.evaluateId,
                EvaluateDynamicSqlSupport.detail,
                EvaluateDynamicSqlSupport.grade,
                EvaluateDynamicSqlSupport.type,
                EvaluateDynamicSqlSupport.status,
                EvaluateDynamicSqlSupport.createTime,
                EvaluateDynamicSqlSupport.updateTime,
                EvaluateDynamicSqlSupport.createUserId,
                EvaluateDynamicSqlSupport.updateUserId
        )
                .from(EvaluateDynamicSqlSupport.evaluate)
                .where(EvaluateDynamicSqlSupport.id, isEqualTo(evaluateId))
                .and(EvaluateDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(EvaluateDynamicSqlSupport.type, isEqualTo(SystemConstants.SCENIC_EVALUATE_TYPE))
                .orderBy(EvaluateDynamicSqlSupport.createTime)
                .build().render(RenderingStrategies.MYBATIS3));
        if (evaluateList.isEmpty()) {
            return null;
        }
        //获取符合查询条件的水果主评
        Evaluate evaluate = evaluateList.get(0);
        ScenicEvaluateVO scenicEvaluateVO = new ScenicEvaluateVO();
        scenicEvaluateVO.setId(evaluate.getId());
        scenicEvaluateVO.setEvaluateId(evaluate.getEvaluateId());
        scenicEvaluateVO.setProductId(evaluate.getProductId());
        scenicEvaluateVO.setDetail(evaluate.getDetail());
        scenicEvaluateVO.setGrade(evaluate.getGrade());
        scenicEvaluateVO.setType(evaluate.getType());
        scenicEvaluateVO.setStatus(evaluate.getStatus());
        scenicEvaluateVO.setCreateTime(evaluate.getCreateTime());
        scenicEvaluateVO.setUpdateTime(evaluate.getUpdateTime());
        scenicEvaluateVO.setCreateUserId(evaluate.getUpdateUserId());

        if(!searchReevaluate(evaluateId).isEmpty()) {
            scenicEvaluateVO.setScenicReevaluate(searchReevaluate(evaluateId));
        }
        else {
            scenicEvaluateVO.setScenicReevaluate(null);
        }
        return scenicEvaluateVO;
    }

    /**
     * 获取水果评价列表
     * @param productId
     * @param page
     * @param pageSize
     * @return
     */
    public PageList<FruitEvaluateVO> getFruitEvaluateVOList(String productId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Evaluate> evaluateList = evaluateMapper.selectMany(select(
                EvaluateDynamicSqlSupport.id,
                EvaluateDynamicSqlSupport.userId,
                EvaluateDynamicSqlSupport.productId,
                EvaluateDynamicSqlSupport.evaluateId,
                EvaluateDynamicSqlSupport.detail,
                EvaluateDynamicSqlSupport.grade,
                EvaluateDynamicSqlSupport.type,
                EvaluateDynamicSqlSupport.status,
                EvaluateDynamicSqlSupport.createTime,
                EvaluateDynamicSqlSupport.updateTime,
                EvaluateDynamicSqlSupport.createUserId,
                EvaluateDynamicSqlSupport.updateUserId
        )
                .from(EvaluateDynamicSqlSupport.evaluate)
                .where(EvaluateDynamicSqlSupport.productId, isEqualTo(productId))
                .and(EvaluateDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(EvaluateDynamicSqlSupport.type, isEqualTo(SystemConstants.FRUIT_EVALUATE_TYPE))
                .orderBy(EvaluateDynamicSqlSupport.createTime)
                .build().render(RenderingStrategies.MYBATIS3));
        if (evaluateList.isEmpty()) {
            return null;
        }
        List<FruitEvaluateVO> fruitEvaluateVOList = new ArrayList<>();
        for (Evaluate evaluate : evaluateList) {
            FruitEvaluateVO fruitEvaluateVO = new FruitEvaluateVO();
            fruitEvaluateVO.setId(evaluate.getId());
            fruitEvaluateVO.setEvaluateId(evaluate.getEvaluateId());
            fruitEvaluateVO.setProductId(evaluate.getProductId());
            fruitEvaluateVO.setDetail(evaluate.getDetail());
            fruitEvaluateVO.setGrade(evaluate.getGrade());
            fruitEvaluateVO.setType(evaluate.getType());
            fruitEvaluateVO.setStatus(evaluate.getStatus());
            fruitEvaluateVO.setCreateTime(evaluate.getCreateTime());
            fruitEvaluateVO.setUpdateTime(evaluate.getUpdateTime());
            fruitEvaluateVO.setCreateUserId(evaluate.getUpdateUserId());
            fruitEvaluateVO.setFruitReevaluate(searchReevaluate(evaluate.getEvaluateId()));
            fruitEvaluateVOList.add(fruitEvaluateVO);
        }
        PageList<FruitEvaluateVO> pageList = new PageList<>();
        pageList.setList(fruitEvaluateVOList);
        pageList.setPageInfo(new PageInfo<>(fruitEvaluateVOList));
        return pageList;
    }

    /**
     * 获取景区评价列表
     * @param productId
     * @param page
     * @param pageSize
     * @return
     */
        public PageList<ScenicEvaluateVO> getScenicEvaluateVOList(String productId, Integer page, Integer pageSize) {
            PageHelper.startPage(page,pageSize);
            List<Evaluate> evaluateList = evaluateMapper.selectMany(select(
                    EvaluateDynamicSqlSupport.id,
                    EvaluateDynamicSqlSupport.userId,
                    EvaluateDynamicSqlSupport.productId,
                    EvaluateDynamicSqlSupport.evaluateId,
                    EvaluateDynamicSqlSupport.detail,
                    EvaluateDynamicSqlSupport.grade,
                    EvaluateDynamicSqlSupport.type,
                    EvaluateDynamicSqlSupport.status,
                    EvaluateDynamicSqlSupport.createTime,
                    EvaluateDynamicSqlSupport.updateTime,
                    EvaluateDynamicSqlSupport.createUserId,
                    EvaluateDynamicSqlSupport.updateUserId
            )
                    .from(EvaluateDynamicSqlSupport.evaluate)
                    .where(EvaluateDynamicSqlSupport.productId, isEqualTo(productId))
                    .and(EvaluateDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                    .and(EvaluateDynamicSqlSupport.type, isEqualTo(SystemConstants.SCENIC_EVALUATE_TYPE))
                    .orderBy(EvaluateDynamicSqlSupport.createTime)
                    .build().render(RenderingStrategies.MYBATIS3));
            if (evaluateList.isEmpty()) {
                return null;
            }
            List<ScenicEvaluateVO> scenicEvaluateVOList = new ArrayList<>();
            for(Evaluate evaluate : evaluateList) {
                ScenicEvaluateVO scenicEvaluateVO = new ScenicEvaluateVO();
                scenicEvaluateVO.setId(evaluate.getId());
                scenicEvaluateVO.setEvaluateId(evaluate.getEvaluateId());
                scenicEvaluateVO.setProductId(evaluate.getProductId());
                scenicEvaluateVO.setDetail(evaluate.getDetail());
                scenicEvaluateVO.setGrade(evaluate.getGrade());
                scenicEvaluateVO.setType(evaluate.getType());
                scenicEvaluateVO.setStatus(evaluate.getStatus());
                scenicEvaluateVO.setCreateTime(evaluate.getCreateTime());
                scenicEvaluateVO.setUpdateTime(evaluate.getUpdateTime());
                scenicEvaluateVO.setCreateUserId(evaluate.getUpdateUserId());
                scenicEvaluateVO.setScenicReevaluate(searchReevaluate(evaluate.getEvaluateId()));
                scenicEvaluateVOList.add(scenicEvaluateVO);
            }
            PageList<ScenicEvaluateVO> pageList = new PageList<>();
            pageList.setList(scenicEvaluateVOList);
            pageList.setPageInfo(new PageInfo<>(scenicEvaluateVOList));
            return pageList;
    }
}
