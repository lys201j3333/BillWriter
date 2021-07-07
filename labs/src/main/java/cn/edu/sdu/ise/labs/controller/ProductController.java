package cn.edu.sdu.ise.labs.controller;

import cn.edu.sdu.ise.labs.annotation.NeedNoToken;
import cn.edu.sdu.ise.labs.dto.ProductDTO;
import cn.edu.sdu.ise.labs.dto.query.ProductQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Product;
import cn.edu.sdu.ise.labs.service.ProductService;
import cn.edu.sdu.ise.labs.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品管理后端服务模块
 *
 * @author 李洪文
 * @description
 * @date 2019/12/3 11:07
 */

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @NeedNoToken
    @PostMapping("add")
    public Integer add(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }
    @NeedNoToken
    @PostMapping("update")
    public Integer update(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }
    @NeedNoToken
    @PostMapping("list1")
    public Page<ProductVO> list1(@RequestBody ProductQueryDTO queryDTO) {
        return productService.listByPage(queryDTO);
    }
    @NeedNoToken
    @PostMapping("list2")
    public Page<ProductVO> list2(@RequestBody ProductQueryDTO queryDTO) {return productService.listByTimes(queryDTO); }
    @PostMapping("bill")
    public ArrayList bill(Integer syear) {return productService.listByYears(syear); }
    @NeedNoToken
    @PostMapping("delete")
    public void delete(@RequestBody List<Integer> ids) {
        productService.deleteByCodes(ids);
    }
}
