package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper mapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        //1.注入mapper
        //2.创建example
        TbContentCategoryExample example = new TbContentCategoryExample();
        //3.设置条件
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //4.执行查询
        List<TbContentCategory> list = mapper.selectByExample(example);
        //5.转成EasyUITreeNode列表
        List<EasyUITreeNode> nodes = new ArrayList<>();
        for(TbContentCategory tbContentCategory:list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            node.setText(tbContentCategory.getName());
            nodes.add(node);
        }
        //6.返回
        return nodes;
    }

    @Override
    public TaotaoResult createContentCategory(Long parentId, String name) {
        //1.注入服务
        //2.构建对象 补全其他的属性
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setCreated(new Date());
        contentCategory.setIsParent(false);
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);
        contentCategory.setUpdated(contentCategory.getCreated());
        //3.插入contentcategory表数据
        mapper.insertSelective(contentCategory);
        //4.返回taotaoresult包含内容分类的id 需要主键返回

        //判断如果要添加的节点的父节点本身叶子节点，需要更新其为父节点
        TbContentCategory parent = mapper.selectByPrimaryKey(parentId);
        if(parent.getIsParent() == false){
            //原本就是叶子节点
            parent.setIsParent(true);
            mapper.updateByPrimaryKeySelective(parent);//更新节点的is_parent属性为true
        }
        return TaotaoResult.ok(contentCategory);
    }

    //更新节点名称
    @Override
    public TaotaoResult updateContentCategory( Long Id, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setId(Id);
        contentCategory.setName(name);
        mapper.updateByPrimaryKeySelective(contentCategory);
        return TaotaoResult.ok(contentCategory);
    }
}
