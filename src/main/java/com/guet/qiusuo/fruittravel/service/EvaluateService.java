package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.FruitEvaluateVO;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.EvaluateDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.EvaluateMapper;
import com.guet.qiusuo.fruittravel.model.Evaluate;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        UserContextHolder.validAdmin();
        long now = System.currentTimeMillis();
        evaluate.setId(UUID.randomUUID().toString());
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
        UserContextHolder.validAdmin();
        Evaluate reEvaluate = new Evaluate();
        long now = System.currentTimeMillis();
        reEvaluate.setStatus(SystemConstants.STATUS_ACTIVE);
        reEvaluate.setEvaluateId(evaluateId);
        reEvaluate.setId(UUID.randomUUID().toString());
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
        UserContextHolder.validAdmin();
        Optional<Evaluate> optionalEvaluate = evaluateMapper.selectByPrimaryKey(evaluateId);
        Evaluate evaluate = optionalEvaluate.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_REEVALUATE));
        evaluate.setStatus(SystemConstants.STATUS_NEGATIVE);
        evaluate.setUpdateUserId(UserContextHolder.getUserId());
        evaluate.setUpdateTime(System.currentTimeMillis());
        int i = evaluateMapper.deleteByPrimaryKey(evaluateId);
        if (i == 0) {
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        LOG.info("删除追评成功,Id:{}", evaluateId);
    }


    /**
     * 删除评价(连带追评一起删除)
     *
     * @param UUID
     */
    public void deleteFruitEvaluate(String UUID) {
        UserContextHolder.validAdmin();
        Optional<Evaluate> optionalEvaluate = evaluateMapper.selectByPrimaryKey(UUID);
        Evaluate evaluate = optionalEvaluate.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_EVALUATE));
        evaluate.setStatus(SystemConstants.STATUS_NEGATIVE);
        evaluate.setUpdateUserId(UserContextHolder.getUserId());
        evaluate.setUpdateTime(System.currentTimeMillis());
        //先删除追评
        deleteFruitReEvaluate(evaluate.getId());
        //再删除主评
        int i = evaluateMapper.deleteByPrimaryKey(evaluate.getId());
        if (i == 0) {
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
    }

    /**
     * 修改水果评价
     *
     * @param evaluate
     */
    public void updateFruitEvaluate(Evaluate evaluate) {
        UserContextHolder.validAdmin();
        evaluate.setUpdateTime(System.currentTimeMillis());
        evaluate.setUpdateUserId(UserContextHolder.getUserId());
        int i = evaluateMapper.updateByPrimaryKeySelective(evaluate);
        if (i == 0) {
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 根据evaluateId查询水果追评
     *
     * @param evaluateId
     * @return
     */
    public List<Evaluate> searchFruitReevaluate(String evaluateId) {
         return evaluateMapper.selectMany(select(
                EvaluateDynamicSqlSupport.id,
                EvaluateDynamicSqlSupport.userId,
                EvaluateDynamicSqlSupport.childFruitId,
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
    public List<FruitEvaluateVO> searchFruitEvaluate(String evaluateId) {
        List<Evaluate> evaluateList = evaluateMapper.selectMany(select(
                EvaluateDynamicSqlSupport.id,
                EvaluateDynamicSqlSupport.userId,
                EvaluateDynamicSqlSupport.childFruitId,
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
                        .and(EvaluateDynamicSqlSupport.type, isEqualTo(SystemConstants.EVALUATE_TYPE))
                        .orderBy(EvaluateDynamicSqlSupport.createTime)
                        .build().render(RenderingStrategies.MYBATIS3));
        if (evaluateList.isEmpty()) {
            return null;
        }
        //获取符合查询条件的水果主评
        Evaluate evaluate = evaluateList.get(0);
        FruitEvaluateVO fruitEvaluateVO = new FruitEvaluateVO();
        List<FruitEvaluateVO> fruitEvaluateVOList = null;
        fruitEvaluateVO.setId(evaluate.getId());
        fruitEvaluateVO.setEvaluateId(evaluate.getEvaluateId());
        fruitEvaluateVO.setChildFruitId(evaluate.getChildFruitId());
        fruitEvaluateVO.setDetail(evaluate.getDetail());
        fruitEvaluateVO.setGrade(evaluate.getGrade());
        fruitEvaluateVO.setType(evaluate.getType());
        fruitEvaluateVO.setStatus(evaluate.getStatus());
        fruitEvaluateVO.setCreateTime(evaluate.getCreateTime());
        fruitEvaluateVO.setUpdateTime(evaluate.getUpdateTime());
        fruitEvaluateVO.setCreateUserId(evaluate.getUpdateUserId());



        if(searchFruitReevaluate(evaluateId) != null &&
                !searchFruitReevaluate(evaluateId).isEmpty()) {
            fruitEvaluateVO.setFruitReevaluate(searchFruitReevaluate(evaluateId));
        }
        else {
            fruitEvaluateVO.setFruitReevaluate(null);
        }
        fruitEvaluateVOList.add(fruitEvaluateVO);
        return fruitEvaluateVOList;
    }
}
