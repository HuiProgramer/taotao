package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;

public interface ItemService {
    /**
     * 根据当前页码和行数进行分页查询
     * */
    public EasyUIDataGridResult getItemList(Integer page,Integer rows);
}
