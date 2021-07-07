package cn.edu.sdu.ise.labs.service.utils;

import cn.edu.sdu.ise.labs.dto.ProductDTO;
import cn.edu.sdu.ise.labs.model.Product;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * @author 李洪文
 * @description
 * @date 2019/12/3 9:35
 */
public class ProductUtils {
    /**
     * 规范并校验productDTO
     *
     * @param productDTO
     */
    public static void validateProduct(ProductDTO productDTO) {
        FormatUtils.trimFieldToNull(productDTO);
        Assert.notNull(productDTO, "商品输入数据不能为空");
     //   Assert.hasText(productDTO.getUpdatedAt(), "商品1名称不能为空");
        Assert.hasText(productDTO.getPproduct(), "商品名称不能为空");
    }

    /**
     * 将实体对象转换为VO对象
     *
     * @param product 实体对象
     * @return VO对象
     */
    public static ProductVO convertToVO(Product product) {
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);
        return productVO;
    }
}
