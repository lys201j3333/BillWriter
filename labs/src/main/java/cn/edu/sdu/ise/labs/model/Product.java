package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;
@Data
public class Product {
    private Integer id;

    private String pproduct;

    private String ptype;

    private Double price;

    private String description;

    private Date ctime;

    private Double y;

    private Integer x;
}