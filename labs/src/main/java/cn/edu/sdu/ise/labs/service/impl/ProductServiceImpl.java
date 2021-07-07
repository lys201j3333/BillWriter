package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.dao.ProductMapper;
import cn.edu.sdu.ise.labs.dto.ProductDTO;
import cn.edu.sdu.ise.labs.dto.query.ProductQueryDTO;
import cn.edu.sdu.ise.labs.model.Product;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.ProductService;
import cn.edu.sdu.ise.labs.service.utils.ProductUtils;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.PageUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author 李洪文
 * @description
 * @date 2019/12/3 9:33
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Page<ProductVO> listByPage(ProductQueryDTO queryDTO) {
        if (queryDTO == null) {
            queryDTO = new ProductQueryDTO();
        }

        queryDTO.setPproduct(FormatUtils.makeFuzzySearchTerm(queryDTO.getPproduct()));
        Token token = TokenContextHolder.getToken();

        Integer size = productMapper.count(queryDTO);
        PageUtils pageUtils = new PageUtils(queryDTO.getPage(), queryDTO.getPageSize(), size);
        Page<ProductVO> pageData = new Page<>(pageUtils.getPage(), pageUtils.getPageSize(), pageUtils.getTotal(), new ArrayList<>());
        if (size == 0) {
            // 没有命中，则返回空数据。
            return pageData;
        }

        List<Product> list = productMapper.list1(queryDTO, pageUtils.getOffset(), pageUtils.getLimit());
        for (Product product : list) {
            pageData.getList().add(ProductUtils.convertToVO(product));
        }

        return pageData;
    }

    @Override
    public Page<ProductVO> listByTimes(ProductQueryDTO queryDTO) {
        if (queryDTO == null) {
            queryDTO = new ProductQueryDTO();
        }

        queryDTO.setFtime(FormatUtils.formatFullDate(FormatUtils.parseDateTime(queryDTO.getFtime())));
        queryDTO.setLtime(FormatUtils.formatFullDate(FormatUtils.parseDateTime(queryDTO.getLtime())));
        Token token = TokenContextHolder.getToken();

        Integer size = productMapper.count2(queryDTO);
        PageUtils pageUtils = new PageUtils(queryDTO.getPage(), queryDTO.getPageSize(), size);
        Page<ProductVO> pageData = new Page<>(pageUtils.getPage(), pageUtils.getPageSize(), pageUtils.getTotal(), new ArrayList<>());
        if (size == 0) {
            // 没有命中，则返回空数据。
            return pageData;
        }

        List<Product> list = productMapper.list2(queryDTO, pageUtils.getOffset(), pageUtils.getLimit());
        for (Product product : list) {
            pageData.getList().add(ProductUtils.convertToVO(product));
        }

        return pageData;
    }

    @Override
    public ArrayList listByYears(Integer syaer) {
        ArrayList moneytongji = new ArrayList();
        ProductQueryDTO needTime = new ProductQueryDTO();
        needTime.setSyear(syaer);
        for (int i = 0; i < 12; i++) {
            needTime.setSmonth(i);
            HashMap sumprice = new HashMap();
            sumprice.put("smonth", i+1+"月");
            sumprice.put("sumprice", productMapper.bill(needTime));
            moneytongji.add(sumprice);
        }
        return moneytongji;
    }
//    @Override
//    public ArrayList listByYears(Integer syear) {
//        ArrayList bill_list = new ArrayList();
//        ProductQueryDTO queryDTO = new ProductQueryDTO();
//        queryDTO.setSyear(syear);
//        bill_list = productMapper.bill(queryDTO);
//        Token token = TokenContextHolder.getToken();
//        return bill_list;
//    }
    /**
     * 新建商品
     *
     * @param productDTO 商品输入对象
     * @return 商品编码
     */
    @Override
    public Integer addProduct(ProductDTO productDTO) {
        // 校验输入数据正确性
        ProductUtils.validateProduct(productDTO);
        // 创建实体对象，用以保存到数据库
        Product product = new Product();
        // 将输入的字段全部复制到实体对象中
        BeanUtils.copyProperties(productDTO, product);
        // 调用DAO方法保存到数据库表
        productMapper.insert(product);
        return product.getId();
    }

    /**
     * 更新商品数据
     *
     * @param productDTO 商品输入对象
     * @return 商品编码
     */
    @Override
    public Integer updateProduct(ProductDTO productDTO) {
        // 校验输入数据正确性
        Token token = TokenContextHolder.getToken();
        ProductUtils.validateProduct(productDTO);
        Assert.notNull(productDTO.getId(), "商品id不能为空");
        Product product = productMapper.selectByPrimaryKey(productDTO.getId());
        Assert.notNull(product, "没有找到商品，Id为：" + productDTO.getId());

        BeanUtils.copyProperties(productDTO, product);
        productMapper.updateByPrimaryKey(product);
        return product.getId();
    }

    /**
     * 根据编码列表，批量删除商品
     *
     * @param ids 编码列表
     */
    @Override
    public void deleteByCodes(List<Integer> ids) {
        Assert.notEmpty(ids, "商品id列表不能为空");
        productMapper.deleteByCodes(ids);
    }
}
