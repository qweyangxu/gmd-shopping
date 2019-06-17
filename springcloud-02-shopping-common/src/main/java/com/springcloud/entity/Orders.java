package com.springcloud.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7841501930285774280L;

	private Integer orderId;

    private Users user;

    private String consigneeName;

    private String consigneeSite;
    
    private String consigneeNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;

    private Double orderAmount;

    private Integer orderStatus;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDateMin;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDateMax;
    
    private String startMonth;
    
    private String endMonth;
    
    private String orderMonth;
    
    private Double orderPrice;

   
}