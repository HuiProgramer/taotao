package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    /**
     * 根据当前页码和行数进行分页查询
     * @param page
     * @param rows
     * */
    public EasyUIDataGridResult getItemList(Integer page,Integer rows);
    /**
     * 添加商品基本数据和描述数据
     * @param item
     * @param desc
     * @return
     */
    public TaotaoResult saveItem(TbItem item, String desc);
}
