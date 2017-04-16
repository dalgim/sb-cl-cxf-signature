package com.dalgim.example.sb.cxf.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dalgim on 08.04.2017.
 */
@Getter
@Setter
@Builder
public class Fruit {

    private Long id;
    private String name;
    private Double kcal;
    private Double protein;
    private Double fat;
    private Double carbo;
}
