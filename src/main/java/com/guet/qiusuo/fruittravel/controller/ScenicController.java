package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.service.ScenicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "景点表")
@RestController
@RequestMapping("/scenic")
public class ScenicController {
    private ScenicService scenicService;
    @Autowired
    public void setScenicService(ScenicService scenicService){
        this.scenicService = scenicService;
    }

    @ApiOperation(value = "添加景点")
    @PostMapping
    public void addScenic(@RequestBody Scenic scenic) {scenicService.addScenic(scenic);}

    @ApiOperation(value = "删除景点")
    @DeleteMapping
    public void deleteScenic(@RequestParam String scenicId) {scenicService.deleteScenic(scenicId);}

    @ApiOperation(value = "修改景点")
    @PutMapping
    public void updateScenic(@RequestBody Scenic scenic) {scenicService.updateScenic(scenic);}

    @ApiOperation(value = "查找景点")
    @GetMapping
    public Scenic searchScenic(@RequestParam String scenicId) {return scenicService.searchScenic(scenicId);}

    @ApiOperation(value = "查找所有景点")
    @GetMapping("/get_all_scenic")
    public List<Scenic> searchAllScenic() {return scenicService.searchAllScenic();}

    @ApiOperation("获取景区列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "景区ID",dataType = "String"),
            @ApiImplicitParam(name = "nameLike",value = "景区名称模糊词",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int")
    })
    @GetMapping("/scenic_list")
    public PageList<Scenic> getScenicList(@RequestParam(required = false) String id,
                                          @RequestParam(required = false) String scenicName,
                                          @RequestParam(required = false) String location,
                                          @RequestParam(required = false) String nameLike,
                                          @RequestParam(required = false) Short type,
                                          @RequestParam(required = false) String description,
                                          @RequestParam(required = false) String openingHours,
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
        if(scenicName != null && (scenicName.trim().isEmpty() || scenicName.length() == 0)){
            scenicName = null;
        }
        return scenicService.getScenicList(id,scenicName, nameLike,location, type, description, openingHours, page, pageSize);
    }
}
