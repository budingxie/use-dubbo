package com.py.usedubbo.rpcentity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 彭有
 * @date 2020/6/13
 */
@Data
public class OrderRpc implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String orderName;

}
