package com.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.content.service.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;
    @Autowired
    private TbContentMapper mapper;
    @Autowired
    private JedisClient jedisClient;


    @Override
    public EasyUIDataGridResult getContentList(Long categoryId,Integer page, Integer rows) {

        if(page==null)page=1;
        if(rows==null)rows=20;
        //设置分页信息
        PageHelper.startPage(page, rows);

        //添加缓存不能影响正常业务逻辑
        //判断是否redis中有数据，如果有，直接从redis中获取数据返回
        try{
            String jsonstr = jedisClient.hget(CONTENT_KEY,categoryId+"");
            if(StringUtils.isNotBlank(jsonstr)){
                System.out.println("这里有缓存啦！！！！！");
                //创建返回结果对象
                EasyUIDataGridResult result = new EasyUIDataGridResult();
                List<TbContent> list = JsonUtils.jsonToList(jsonstr, TbContent.class);
                //取分页信息
                PageInfo<TbContent> pageInfo = new PageInfo<>(list);
                result.setTotal((int)pageInfo.getTotal());
                result.setRows(list);
                return result;
            }
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

        //执行查询

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria =example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = mapper.selectByExample(example);
        //取分页信息
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);

        //将数据写入到redis数据库中
        //注入jedisclientCluster
        //调用方法写入redis
        try {
            System.out.println("没有缓存！！！！！！");
            jedisClient.hset(CONTENT_KEY,categoryId+"",JsonUtils.objectToJson(list));
        }
       catch (Exception e){
            e.printStackTrace();
       }
        //创建返回结果对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int)pageInfo.getTotal());
        result.setRows(list);

        return result;
    }

    @Override
    public TaotaoResult saveContent(TbContent tbContent) {
        //1.注入mapper

        //2.补全其他的属性
        tbContent.setCreated(new Date());
        tbContent.setUpdated(tbContent.getCreated());
        //3.插入内容表中
        mapper.insertSelective(tbContent);

        //当添加内容的时候，需要清空此内容所属的分类下的所有的缓存
        try {
            jedisClient.hdel(CONTENT_KEY, tbContent.getCategoryId()+"");
            System.out.println("当插入时，清空缓存!!!!!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        mapper.updateByPrimaryKeySelective(tbContent);
        //当更新内容的时候，需要清空此内容所属的分类下的所有的缓存
        try {
            jedisClient.hdel(CONTENT_KEY, tbContent.getCategoryId()+"");
            System.out.println("当更新时，清空缓存!!!!!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok(tbContent);
    }

    @Override
    public TaotaoResult deleteContent(List<Long> Ids) {
        for(Long id:Ids){
            mapper.deleteByPrimaryKey(id);
        }
        //当删除内容的时候，需要清空此内容所属的分类下的所有的缓存
        try {
            jedisClient.hdel(CONTENT_KEY, "89");
            System.out.println("当删除时，清空缓存!!!!!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }


}
