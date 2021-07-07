package cn.edu.sdu.ise.labs.dto.query;

import lombok.Data;

/**
 * @author 李洪文
 * @description
 * @date 2019/12/3 10:18
 */
@Data
public class ProductQueryDTO extends PageQueryDTO {

    /**
     * 商品名称，模糊匹配
     */
    private String pproduct;

    /**
     * 时间范围匹配，输入起始和终时间
     */
    private String ftime;
    private String ltime;
    private Integer syear;
    private Integer smonth;
}
