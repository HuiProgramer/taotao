package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.ItemCatResult;
import com.taotao.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService catservice;
    //url:'/item/cat/list',
    //参数：id
    //返回值：json
    //method:get post
    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
        //1.引入服务
        //2.注入服务
        //3.调用方法
        List<EasyUITreeNode> list = catservice.getItemCatListByParentId(parentId);
        return list;
    }

    @RequestMapping(value = "/rest/itemcat/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback){
        ItemCatResult result = catservice.getItemCatList();
        //判断是否为空
        if(StringUtils.isBlank(callback))
            return JsonUtils.objectToJson(result);

        // 如果字符串不为空，需要支持jsonp调用
        //需要把result转换成字符串
        return callback + "(" + JsonUtils.objectToJson(result) + ");";
    }
}
