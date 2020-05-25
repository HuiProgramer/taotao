package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.CatNode;
import com.taotao.pojo.ItemCatResult;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import com.taotao.pojo.TbItemCatExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper catmapper;
    @Override
    public List<EasyUITreeNode> getItemCatListByParentId(Long parentId) {
        //1.注入mapper
        //2.创建example
        TbItemCatExample example = new TbItemCatExample();
        //3.设置查询的条件
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);//select *ｆｒｏｍ　ｔｂｉｔｅｍｃａｔ　ｗｈｅｒｅ　ｐａｒｅｎｔＩｄ＝１
        //4.执行查询  list<ibitemCat>
        List<TbItemCat> list = catmapper.selectByExample(example);
        //5.转成需要的数据类型List<EasyUITreeNode>
        List<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbItemCat cat : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent()?"closed":"open");//"open",closed
            nodes.add(node);
        }
        //6.返回
        return nodes;
    }

    @Override
    public ItemCatResult getItemCatList() {
        //调用递归方法查询商品分类列表
        List catlist = getItemcatList(0l);
        //返回结果
        ItemCatResult itemCatResult = new ItemCatResult();
        itemCatResult.setData(catlist);
        return itemCatResult;
    }

    private List getItemcatList(Long parentId){
        //根据parentId查询列表
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> tbItemCats = catmapper.selectByExample(example);
        List resultList  = new ArrayList();
        for(TbItemCat tbItemCat : tbItemCats){
            //如果是父节点
            if(tbItemCat.getIsParent()){
                CatNode node = new CatNode();
                node.setUrl("/products/"+tbItemCat.getId()+".html");
                //如果当前节点为一级节点
                if(tbItemCat.getParentId() == 0)
                    node.setName("<a href = '/products/'"+tbItemCat.getId()+".html>"+tbItemCat.getName()+"</a>");
                else
                    node.setName(tbItemCat.getName());
                node.setItems(getItemcatList(tbItemCat.getId()));
                //把node添加到列表
                resultList.add(node);
            }
            else {
                //如果是叶子节点
                String item = "/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName();
                resultList.add(item);
            }
        }
        return resultList;
    }

}
