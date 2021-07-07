package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.dto.query.ProductQueryDTO;
import cn.edu.sdu.ise.labs.model.Product;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品数据访问组件
 *
 * @author 李洪文
 * @date 2019/11/14 10:38
 */
public interface ProductMapper {
    Product selectByPrimaryKey(Integer id);

    /**
     * 新增记录
     *
     * @param record
     * @return
     */
    int insert(Product record);

    /**
     * 根据主键更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Product record);

    /**
     * 根据商品编码获取商品信息详情
     *
     * @param productCode 商品编码
     * @param tenantCode  租户代码
     * @return 门信息详情
     */
    Product getByCode(
            @Param("productCode") String productCode,
            @Param("tenantCode") String tenantCode);


    /**
     * 根据查询条件获取命中个数
     *
     * @param queryDTO 查询条件
     * @return 命中数量
     */
    Integer count(ProductQueryDTO queryDTO);

    /**
     * 根据查询条件获取商品列表
     *
     * @param queryDTO 查询条件
     * @param offset   开始位置
     * @param limit    记录数量
     * @return 商品列表
     */
    List<Product> list1(ProductQueryDTO queryDTO, Integer offset, Integer limit
    );
    Integer count3(ProductQueryDTO queryDTO);
    /**
     * 根据查询条件获取命中个数
     *
     * @param queryDTO 查询条件
     * @return 命中数量
     */
    Integer count2(ProductQueryDTO queryDTO);

    /**
     * 根据查询条件获取商品列表
     *
     * @param queryDTO 查询条件
     * @param offset   开始位置
     * @param limit    记录数量
     * @return 商品列表
     */
    List<Product> list2(ProductQueryDTO queryDTO, Integer offset, Integer limit
    );
    /**
     * 根据代码列表批量删除商品
     *
     * @param ids id列表
     */

    void deleteByCodes(
            @Param("codeList") List<Integer> ids);

    ArrayList bill(ProductQueryDTO queryDTO);

}
