package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author 李洪文
 * @description
 * @date 2019/12/3 9:20
 */
@Data
public class ProductDTO {
    private Integer id;

    private String pproduct;

    private String ptype;

    private Double price;

    private String description;

    private Date ctime;
}
