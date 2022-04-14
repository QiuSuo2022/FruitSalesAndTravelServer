package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.bean.vo.FruitAndChildFruitsVO;
import com.guet.qiusuo.fruittravel.bean.vo.FruitVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.service.ChildFruitService;
import com.guet.qiusuo.fruittravel.service.FruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "水果商品表")
@RestController
@RequestMapping("/fruit")
public class FruitController {

    private FruitService fruitService;

    private ChildFruitService childFruitService;

    @Autowired
    public void  setFruitService(FruitService fruitService){
        this.fruitService = fruitService;
    }

    @Autowired
    public void setChildFruitService(ChildFruitService childFruitService) { this.childFruitService = childFruitService; }

    @ApiOperation(value = "添加水果")
    @PostMapping
    public boolean addFruit(@RequestBody Fruit fruit){
        return fruitService.addFruit(fruit);
    }

    @ApiOperation(value = "删除水果")
    @DeleteMapping
    public void deleteFruit(@RequestParam String fruitId){
         fruitService.deleteFruit(fruitId);
    }

    @ApiOperation(value = "修改水果")
    @PutMapping("/updateFruit")
    public boolean updateFruit(@RequestBody Fruit fruit){
        return fruitService.updateFruit(fruit);
    }

    @ApiOperation(value = "查找所有水果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("/getFruits")
    public PageList<Fruit> searchAllFruits(@RequestParam(required = false,defaultValue =
                                                    SystemConstants.DEFAULT_PAGE) Integer page,
                                           @RequestParam(required = false,defaultValue =
                                                   SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return fruitService.getAllFruits(page, pageSize);
    }

    @ApiOperation("获取水果列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderByType",value = "排序方式",dataType = "Short", dataTypeClass = Short.class, required = true),
            @ApiImplicitParam(name = "nameLike",value = "水果名称模糊词",dataType = "String", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("/fruitList")
    public PageList<Fruit> getFruitList(@RequestParam(required = false) String id,
                                        @RequestParam(required = false) String fruitName,
                                        @RequestParam(required = false) String fruitPrice,
                                        @RequestParam(required = false) String nameLike,
                                        @RequestParam(required = false) String departurePoint,
                                        @RequestParam(required = false) String description,
                                        @RequestParam(required = false) Integer deliveryCost,
                                        @RequestParam(required = false) Short orderByType,
                                        @RequestParam(required = false,defaultValue =
                                                SystemConstants.DEFAULT_PAGE) Integer page,
                                        @RequestParam(required = false,defaultValue =
                                                SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize){
        if(nameLike != null && nameLike.trim().isEmpty()) {
            nameLike = null;
        }
        if(id != null && (id.trim().isEmpty() || id.length() == 0)){
            id = null;
        }
        if(fruitName != null && (fruitName.trim().isEmpty() || fruitName.length() == 0)){
            fruitName = null;
        }
        return fruitService.getFruitList(id,fruitName,fruitPrice,nameLike,departurePoint,description,deliveryCost,orderByType,page,pageSize);
    }

    @ApiOperation(value = "获取水果推荐列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("/fruitRecommendList")
    public PageList<FruitVO> getFruitRecommendList(
                                                 @RequestParam(required = false,defaultValue =
                                                         SystemConstants.DEFAULT_PAGE) Integer page,
                                                 @RequestParam(required = false,defaultValue =
                                                         SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize) {

        return fruitService.getFruitRecommendList(page, pageSize);
    }

    @ApiOperation(value = "获取单个水果")
    @GetMapping
    public Fruit getFruit(String fruitId){
        return fruitService.getFruit(fruitId);
    }

    @ApiOperation(value = "获取水果以及该水果的子项")
    @GetMapping("/getFruitAndChildFruits")
    public FruitAndChildFruitsVO getFruitAndChildFruits(@RequestParam String fruitId){
        if (fruitId.isEmpty()){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        FruitAndChildFruitsVO fruitAndChildFruitsVO = new FruitAndChildFruitsVO();
        fruitAndChildFruitsVO.setFruit(getFruit(fruitId));
        fruitAndChildFruitsVO.setChildFruits(childFruitService.getChildFruitListByFruitId(fruitId));
        return fruitAndChildFruitsVO;
    }
}

