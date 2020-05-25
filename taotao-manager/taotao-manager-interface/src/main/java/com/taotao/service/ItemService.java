package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

import java.util.List;

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

    /**
     * 删除商品基本数据和描述数据以及图片
     * @param ids
     * */
    public TaotaoResult deleteItem(List<Long> ids);

    /**
     * 更新商品信息
     * */
    public TaotaoResult updateItem(TbItem tbItem,String desc);

    /**
     * 商品描述回显
     * @param id
     * @return TaotaoResult
     * **/
    public TaotaoResult showDesc(String id);

    /**
     * 根据itemid得到Tbitem对象
     * @param itemId
     * @return TbItem
     * **/
    public TbItem getItemById(Long itemId);

    /**
     * 根据itemid得到TbitemDesc对象
     * @param itemId
     * @return TbItemDesc
     * **/
    public TbItemDesc getItemDescById(Long itemId);

    /**
     * 根据itemid得到list
     * @param itemId
     * @return List<String>
     * **/
    public List<String> getItemCatById(Long itemId);
}
