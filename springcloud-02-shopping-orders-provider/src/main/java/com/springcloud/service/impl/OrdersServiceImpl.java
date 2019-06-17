package com.springcloud.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;

/**
 * 
 * @author 123
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper OrdersMapper;

	@Override
	public PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber) {
		if (orders.getUser() != null) {
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}
		PageHelper.startPage(pageNumber + 1, PageUtils.PAGE_ROW_COUNT);
		List<Orders> list = this.OrdersMapper.selectOrders(orders);
		return new PageInfo<>(list);
	}

	@Transactional
	@Override
	public Integer updateOrdersStatus(Orders orders) {
		return this.OrdersMapper.updateOrdersStatus(orders);
	}
	
	@Override
	public List<Orders> selectGroup(Orders orders) {
		return this.OrdersMapper.selectGroup(orders);
	}

}
