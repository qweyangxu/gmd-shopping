package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2281236879693007932L;

	private Integer orderDetailId;

    private Integer orderId;

    private Integer goodId;

    private String goodName;

    private Double transactionPrice;

    private Integer transactionCount;

}