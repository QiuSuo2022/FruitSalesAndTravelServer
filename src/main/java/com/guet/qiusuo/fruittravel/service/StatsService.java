package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.dao.EvaluateDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.EvaluateMapper;
import com.guet.qiusuo.fruittravel.dao.OrderFormDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.OrderFormMapper;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.model.Scenic;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author : lu
 */
@Service
public class StatsService {

    long now = System.currentTimeMillis();
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private static final short WEEK  = 1;
    private static final long WEEK_MILLIS  = new BigInteger("604800017").longValue();

    private static final short MONTH = 2;
    private static final long MONTH_MILLIS = new BigInteger("2629800000").longValue();

    private static final short YEAH  = 3;
    private static final long YEAH_MILLIS  = new BigInteger("31557600000").longValue();

    private static final short STAR_1 = 1;
    private static final short STAR_2 = 2;
    private static final short STAR_3 = 3;
    private static final short STAR_4 = 4;
    private static final short STAR_5 = 5;

    private static final short TYPE_PRODUCT   = 0;

    private static final short UNPAID = 0;

    private static final short GOOD_EVALUATION   = 1;
    private static final short COMMON_EVALUATION = 2;
    private static final short BAD_EVALUATION    = 3;

    /**
     * 各近期时间点的毫秒数 - 检索的时候 weekAgo to now
     */
    private final long weekAgo  = now - WEEK_MILLIS;
    private final long monthAgo = now - MONTH_MILLIS;
    private final long yearAgo  = now - YEAH_MILLIS;

    private EvaluateMapper evaluateMapper;
    private ChildFruitService childFruitService;
    private OrderFormMapper orderFormMapper;
    private ScenicService scenicService;
    private FruitService fruitService;
    @Autowired
    public void setEvaluateMapper(EvaluateMapper evaluateMapper) {
        this.evaluateMapper = evaluateMapper;
    }


    @Autowired
    public void setOrderFormMapper(OrderFormMapper orderFormMapper) {
        this.orderFormMapper = orderFormMapper;
    }

    @Autowired
    public void setFruitService(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @Autowired
    public void setChildFruitService(ChildFruitService childFruitService) {
        this.childFruitService = childFruitService;
    }
    @Autowired
    public void setScenicService(ScenicService scenicService) {
        this.scenicService = scenicService;
    }

    /**
     * Controller调用 - 根据FruitId获取单个销量
     * @param fruitId        水果id
     * @param ago       近期: 周/月/年
     * @return
     */
    public long getSingleSalesByFruitId(String fruitId, short ago){
        long amount = 0;
        switch (ago){
            case WEEK:
                amount = getSalesByFruitIdSql(fruitId,weekAgo);break;
            case MONTH:
                amount = getSalesByFruitIdSql(fruitId,monthAgo);break;
            case YEAH:
                amount = getSalesByFruitIdSql(fruitId,yearAgo);break;
            default:LOG.info("错误参数!");break;
        }
        return amount;
    }

    /**
     * Controller调用 - 根据scenicId获取单个销量
     * @param id    门票id
     * @param ago   近期: 周/月/年
     * @return
     */
    public long getSingleSalesByScenicId(String id, short ago) {
        long amount = 0;
        switch (ago){
            case WEEK:
                amount = getSalesByScenicIdSql(id,weekAgo);break;
            case MONTH:
                amount = getSalesByScenicIdSql(id,monthAgo);break;
            case YEAH:
                amount = getSalesByScenicIdSql(id,yearAgo);break;
            default:LOG.info("错误参数!");break;
        }
        return amount;
    }

    /**
     * Controller调用 - 获取近期所有门票销量的list
     * @param ago
     * @return
     */
    public List<Map.Entry<String, Long>> getAllScenicSalesByAgo(short ago){
        //此方法用于返回List<map<scenicName,Sales>即景区与销量的键值对
        return getAllScenicSalesMapByAgo(ago);
    }

    /**
     * done
     * Controller调用 - 获取近期所有水果销量的list
     * @param ago
     * @return
     */
    public List<Map.Entry<String, Long>> getAllFruitsSalesByAgo(short ago){
        //此方法用于返回List<map<fruitName,Sales>即水果与销量的键值对
        return getAllFruitsSalesMapByAgo(ago);
    }

    /**
     * Controller调用 - 获取近期所有景区评价情况的list
     * @param ago
     * @return
     */
    public List<Map.Entry<String, Long>> getAllScenicEvaluationByAgo(short ago, short evaluationType){
        return getAllScenicEvaluationMapByAgo(ago,evaluationType);
    }

    /**
     * Controller调用 - 获取近期所有水果的评价情况的list
     * @param ago
     * @param evaluationType
     * @return
     */
    public List<Map.Entry<String, Long>> getAllFruitsEvaluationByAgo(short ago, short evaluationType){
        return getAllFruitsEvaluationMapByAgo(ago,evaluationType);
    }

    /**
     * Controller调用 - 获取销量最高的景区
     * @param ago
     * @return
     */
    public HashMap<String,Long> getTopSaleScenic(short ago){
        List<Scenic> scenicList = scenicService.getAllScenic();
        HashMap<String,Long> max = new HashMap<String,Long>();
        max.put(getAllScenicSalesMapByAgo(ago).get(scenicList.size()).getKey(),getAllScenicSalesMapByAgo(ago).get(scenicList.size()).getValue());
        return max;
    }

    /**
     * Controller调用 - 获取销量最高的水果
     * @param ago
     * @return
     */
    public HashMap<String,Long> getTopSaleFruit(short ago){
        List<ChildFruit> childFruitsList = childFruitService.getAllChildFruits();
        HashMap<String,Long> max = new HashMap<String,Long>();
        max.put(getAllFruitsSalesByAgo(ago).get(childFruitsList.size()).getKey(),getAllFruitsSalesByAgo(ago).get(childFruitsList.size()).getValue());
        return max;
    }

    /***********************************以下是主要方法***************************************/

    private List<Map.Entry<String, Long>> getAllFruitsSalesMapByAgo(short ago){
        List<Fruit> FruitList = fruitService.getAllFruitList();
        Map<String,Long> map = new HashMap<String, Long>(FruitList.size());
        for (int i = 0; i < FruitList.size(); i++){
            map.put(FruitList.get(i).getFruitName(),
                    getSingleSalesByFruitId(FruitList.get(i).getId(),ago));
        }
        return sortHashmapUtil(map);
    }
    /**
     * 获取所有景区的评价情况
     * @param ago
     * @param evaluationType
     * @return
     */
    private List<Map.Entry<String,Long>> getAllScenicEvaluationMapByAgo(short ago,short evaluationType){
        //获取景区种类   一景区对应n张门票即n个评论
        List<Scenic> scenic = scenicService.getAllScenic();
        Map<String,Long> map = new HashMap<String, Long>(scenic.size());
        switch (evaluationType){
            case GOOD_EVALUATION:
                switch (ago){
                    //所有childFruit以<fruitName, goodEvaluationAmount> 存入map
                    case WEEK:
                        for (int i = 0; i < scenic.size();i++){
                            map.put(scenic.get(i).getScenicName(),getScenicEvaluationAmountSql(scenic.get(i).getId(),weekAgo,STAR_4,STAR_5));
                        }break;
                    case MONTH:
                        for (int i = 0; i < scenic.size();i++){
                            map.put(scenic.get(i).getScenicName(),getScenicEvaluationAmountSql(scenic.get(i).getId(),monthAgo,STAR_4,STAR_5));
                        }break;
                    case YEAH:
                        for (int i = 0; i < scenic.size();i++){
                            map.put(scenic.get(i).getScenicName(),getScenicEvaluationAmountSql(scenic.get(i).getId(),yearAgo,STAR_4,STAR_5));
                        }break;
                    default:LOG.info("错误参数!");break;
                }break;
            case BAD_EVALUATION:
                switch (ago){
                    //所有childFruit以<fruitName, badEvaluationAmount> 存入map
                    case WEEK:
                        for (int i = 0; i < scenic.size();i++){
                            map.put(scenic.get(i).getScenicName(),getScenicEvaluationAmountSql(scenic.get(i).getId(),weekAgo,STAR_1,STAR_2));
                        }break;
                    case MONTH:
                        for (int i = 0; i < scenic.size();i++){
                            map.put(scenic.get(i).getScenicName(),getScenicEvaluationAmountSql(scenic.get(i).getId(),monthAgo,STAR_1,STAR_2));
                        }break;
                    case YEAH:
                        for (int i = 0; i < scenic.size();i++){
                            map.put(scenic.get(i).getScenicName(),getScenicEvaluationAmountSql(scenic.get(i).getId(),yearAgo,STAR_1,STAR_2));
                        }break;
                    default:LOG.info("错误参数!");break;
                }break;
            default:LOG.info("错误参数!");break;
        }
        //所有ChildFruit的数据已经按一年/月/周统计存入map
        //将map按照value排序
        return sortHashmapUtil(map);
    }

    /**
     * 获取所有Fruit的评价list
     * @param ago
     * @param evaluationType
     * @return
     */
    private List<Map.Entry<String,Long>> getAllFruitsEvaluationMapByAgo(short ago,short evaluationType){
        //所有fruit种类 - fruitList
        List<Fruit> fruitList = fruitService.getAllFruitList();
        Map<String,Long> map = new HashMap<String, Long>(fruitList.size());
        switch (evaluationType){
            case GOOD_EVALUATION:
                switch (ago){
                    //所有fruit<fruitName, goodEvaluationAmount> 存入map
                    case WEEK:
                        for (int i = 0; i < fruitList.size();i++){
                            map.put(fruitList.get(i).getFruitName(),getFruitEvaluationAmountSql(fruitList.get(i).getId(),weekAgo,STAR_4,STAR_5));
                        }break;
                    case MONTH:
                        for (int i = 0; i < fruitList.size();i++){
                            map.put(fruitList.get(i).getFruitName(),getFruitEvaluationAmountSql(fruitList.get(i).getId(),monthAgo,STAR_4,STAR_5));
                        }break;
                    case YEAH:
                        for (int i = 0; i < fruitList.size();i++){
                            map.put(fruitList.get(i).getFruitName(),getFruitEvaluationAmountSql(fruitList.get(i).getId(),yearAgo,STAR_4,STAR_5));
                        }break;
                    default:LOG.info("错误参数!");break;
                }break;
            case BAD_EVALUATION:
                switch (ago){
                    //所有childFruit以<fruitName, badEvaluationAmount> 存入map
                    case WEEK:
                        for (int i = 0; i < fruitList.size();i++){
                            map.put(fruitList.get(i).getFruitName(),getFruitEvaluationAmountSql(fruitList.get(i).getId(),weekAgo,STAR_1,STAR_2));
                        }break;
                    case MONTH:
                        for (int i = 0; i < fruitList.size();i++){
                            map.put(fruitList.get(i).getFruitName(),getFruitEvaluationAmountSql(fruitList.get(i).getId(),monthAgo,STAR_1,STAR_2));
                        }break;
                    case YEAH:
                        for (int i = 0; i < fruitList.size();i++){
                            map.put(fruitList.get(i).getFruitName(),getFruitEvaluationAmountSql(fruitList.get(i).getId(),yearAgo,STAR_1,STAR_2));
                        }break;
                    default:LOG.info("错误参数!");break;
                }break;
            default:LOG.info("错误参数!");break;
        }
        //所有ChildFruit的数据已经按一年/月/周统计存入map
        //将map按照value排序
        return sortHashmapUtil(map);
    }

    /**
     * 获取所有景区的销量情况
     * @param ago
     * @return
     */
    private List<Map.Entry<String,Long>> getAllScenicSalesMapByAgo(short ago){
        //获取景区种类, 一个景区对应多个订单即销量
        List<Scenic> scenicList = scenicService.getAllScenic();
        Map<String,Long> map = new HashMap<String, Long>(scenicList.size());
            for (int i = 0;i < scenicList.size(); i++) {
                map.put(scenicList.get(i).getScenicName(),
                        getSingleSalesByScenicId(scenicList.get(i).getId(),ago));
            }
        //所有ChildFruit的数据已经按一年/月/周统计存入map
        //将map按照value排序
        return sortHashmapUtil(map);
    }


    /************************************以下是工具方法和sql方法*********************************/

    /**
     * 对map根据value进行排序, 返回的是List<Map.Entry<String,Long>>
     * @param map
     * @return
     */
    private static List<Map.Entry<String,Long>> sortHashmapUtil(Map<String,Long> map){
        List<Map.Entry<String,Long>>  list = new ArrayList<Map.Entry<String,Long>>(map.entrySet());
        //比较器实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, Long> o1,
                               Map.Entry<String, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return list;
    }

    /**
     * 获取景区好评以及差评的Sql方法
     * @param scenicId
     * @param past
     * @param gradeFrom
     * @param gradeTo
     * @return
     */
    private long getScenicEvaluationAmountSql(String scenicId,long past,short gradeFrom ,short gradeTo) {
        return  evaluateMapper.count(select()
                .from(EvaluateDynamicSqlSupport.evaluate)
                .where(EvaluateDynamicSqlSupport.productId,isEqualTo(scenicId))
                .and(EvaluateDynamicSqlSupport.grade,isBetween(gradeFrom).and(gradeTo))
                .and(EvaluateDynamicSqlSupport.type,isEqualTo(TYPE_PRODUCT))
                .and(EvaluateDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(EvaluateDynamicSqlSupport.createTime,isBetween(past).and(now))
                .build().render(RenderingStrategies.MYBATIS3));
    }

    /**
     * 获取水果好评以及差评的Sql方法
     * @param fruitId
     * @param past
     * @param gradeFrom
     * @param gradeTo
     * @return
     */
    private long getFruitEvaluationAmountSql(String fruitId,long past,short gradeFrom ,short gradeTo) {
        return  evaluateMapper.count(select()
                .from(EvaluateDynamicSqlSupport.evaluate)
                .where(EvaluateDynamicSqlSupport.productId,isEqualTo(childFruitService.getChildFruitByFruitId(fruitId).getId()))
                .and(EvaluateDynamicSqlSupport.grade,isBetween(gradeFrom).and(gradeTo))
                .and(EvaluateDynamicSqlSupport.type,isEqualTo(TYPE_PRODUCT))
                .and(EvaluateDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(EvaluateDynamicSqlSupport.createTime,isBetween(past).and(now))
                .build().render(RenderingStrategies.MYBATIS3));
    }

    /**
     * 获取中评的Sql方法
     * @param id
     * @param past
     * @return
     */
    /*
    private long getCommonEvaluationAmountSql(String id,long past) {
        return  evaluateMapper.count(select()
                .from(EvaluateDynamicSqlSupport.evaluate)
                .where(EvaluateDynamicSqlSupport.productId,isEqualTo(id))
                .and(EvaluateDynamicSqlSupport.grade,isEqualTo(STAR_3))
                .and(EvaluateDynamicSqlSupport.type,isEqualTo(TYPE_PRODUCT))
                .and(EvaluateDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(EvaluateDynamicSqlSupport.createTime,isBetween(past).and(now))
                .build().render(RenderingStrategies.MYBATIS3));
    }*/

    /**
     * 根据childFruitId统计销量的Sql方法
     * @param fruitId
     * @param past
     * @return
     */
    private long getSalesByFruitIdSql(String fruitId,long past) {
        //fruitId获取fruit, 根据fruitId检索已经支付的订单
        return  orderFormMapper.count(select()
                .from(OrderFormDynamicSqlSupport.orderForm)
                .where(OrderFormDynamicSqlSupport.orderFormStatus,isNotEqualTo(UNPAID))
                .and(OrderFormDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(OrderFormDynamicSqlSupport.payTime,isBetween(past).and(now))
                .and(OrderFormDynamicSqlSupport.fruitId,isEqualTo(fruitId))
                .build().render(RenderingStrategies.MYBATIS3));
    }

    /**
     * 根据ScenicId统计销量的Sql方法
     * @param scenicId
     * @param past
     * @return
     */
    private long getSalesByScenicIdSql(String scenicId,long past) {
        return  orderFormMapper.count(select()
                .from(OrderFormDynamicSqlSupport.orderForm)
                .where(OrderFormDynamicSqlSupport.orderFormStatus,isNotEqualTo(UNPAID))
                .and(OrderFormDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(OrderFormDynamicSqlSupport.payTime,isBetween(past).and(now))
                .and(OrderFormDynamicSqlSupport.scenicId,isEqualTo(scenicId))
                .build().render(RenderingStrategies.MYBATIS3));
    }

}
