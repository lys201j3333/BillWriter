package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.ProductDTO;
import cn.edu.sdu.ise.labs.dto.query.ProductQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Product;
import cn.edu.sdu.ise.labs.vo.ProductVO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品模块服务接口
 *
 * @author 李洪文
 * @date 2019-12-3
 */
public interface ProductService {
    Page<ProductVO> listByPage(ProductQueryDTO queryDTO);
    Page<ProductVO> listByTimes(ProductQueryDTO queryDTO);
    ArrayList listByYears(Integer syear);
    /**
     * 新建商品
     *
     * @param productDTO 商品输入对象
     * @return 商品编码
     */
    Integer addProduct(ProductDTO productDTO);

    /**
     * 更新商品数据
     *
     * @param productDTO 商品输入对象
     * @return 商品编码
     */
    Integer updateProduct(ProductDTO productDTO);

    /**
     * 根据编码列表，批量删除商品
     *
     * @param ids 编码列表
     */
    void deleteByCodes(List<Integer> ids);
}
