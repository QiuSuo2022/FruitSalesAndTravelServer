package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ReservationDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ReservationMapper;
import com.guet.qiusuo.fruittravel.dao.ScenicDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.model.Reservation;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ReservationService {
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private ReservationMapper reservationMapper;

    @Autowired
    public void setReservationMapper(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    /**
     * 添加Reservation(预定门票)
     *
     * @param reservation
     */
    public void addReservation(Reservation reservation) {
        UserContextHolder.validUser(UserContextHolder.getUserId());
        long now = System.currentTimeMillis();
        reservation.setCreateTime(now);
        reservation.setUpdateTime(now);
        reservation.setId(UUID.randomUUID().toString().replace("-", ""));
        reservation.setUpdateUserId(UserContextHolder.getUserId());
        reservation.setCreateUserId(UserContextHolder.getUserId());
        reservation.setStatus(SystemConstants.STATUS_ACTIVE);
        int i = reservationMapper.insertSelective(reservation);
        if (i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("门票预定成功,Id:{}", reservation.getTicketId());
    }

    /**
     *删除Reservation(退订门票)
     * @param Id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteReservation(String Id) {
        UserContextHolder.validUser(UserContextHolder.getUserId());
        Optional<Reservation> optionalReservation = reservationMapper.selectByPrimaryKey(Id);
        Reservation reservation = optionalReservation.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_RESERVATION));
        reservation.setStatus(SystemConstants.STATUS_NEGATIVE);
        reservation.setUpdateUserId(UserContextHolder.getUserId());
        reservation.setUpdateTime(System.currentTimeMillis());
        int i = reservationMapper.deleteByPrimaryKey(reservation.getId());
        if (i == 0) {
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
    }

    /**
     * 修改Reservation
     *
     * @param reservation
     */
    public void updateReservation(Reservation reservation) {
        UserContextHolder.validUser(UserContextHolder.getUserId());
        reservation.setUpdateTime(System.currentTimeMillis());
        reservation.setUpdateUserId(UserContextHolder.getUserId());
        int i = reservationMapper.updateByPrimaryKeySelective(reservation);
        if (i == 0) {
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 查找Reservation
     *
     * @param Id
     *
     * @return
     */
    public Reservation searchReservation(String Id) {
        UserContextHolder.validUser(UserContextHolder.getUserId());
        return reservationMapper.selectByPrimaryKey(Id).orElse(null);
    }

    /**
     * 查找全部预定
     *
     * @return
     */
    public List<Reservation> searchAllReservation() {
        UserContextHolder.validUser(UserContextHolder.getUserId());
        return reservationMapper.selectMany(select(
                ReservationDynamicSqlSupport.id,
                ReservationDynamicSqlSupport.userId,
                ReservationDynamicSqlSupport.ticketId,
                ReservationDynamicSqlSupport.reserveTime,
                ReservationDynamicSqlSupport.quantity,
                ReservationDynamicSqlSupport.status,
                ReservationDynamicSqlSupport.createTime,
                ReservationDynamicSqlSupport.updateTime,
                ReservationDynamicSqlSupport.createUserId,
                ReservationDynamicSqlSupport.updateUserId
        )
                        .from(ReservationDynamicSqlSupport.reservation)
                        .where(ReservationDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .and(ReservationDynamicSqlSupport.status, isNotEqualTo(SystemConstants.STATUS_NEGATIVE))
                        .build().render(RenderingStrategies.MYBATIS3));
    }
}
