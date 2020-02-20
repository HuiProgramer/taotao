package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    //url:/item/list
    //method:get
    //参数：page,rows
    //返回值：json
    @RequestMapping(value="/item/list",method=RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //引入服务
        //注册服务
        //调用服务的方法
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;

    }

    //添加商品的方法
    //url:：/item/save
    //参数：tbitem ,desc
    //返回值 json
    //method:post
    @RequestMapping(value="/item/save",method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveItem(TbItem item, String desc){
//		//1.引入服务
        //2.注入服务
        //3.调用
        return itemService.saveItem(item, desc);
    }
}
