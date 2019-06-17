package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.service.UserService;
import com.springcloud.vo.ResultValue;

/**
 * 控制层：接收模型层的数据，接收视图层的数据，并调用相应方法
 * 
 * @author 123
 *
 */
@RestController
public class UsersController {

	@Autowired
	private UserService userService;

	/**
	 * 用户信息
	 * 
	 * @param userId       用户编号
	 * @param userPassword 用户密码
	 * @param jdictionId   权限编号
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ResultValue login(@RequestParam("userId") Integer userId, @RequestParam("userPassword") String userPassword,
			@RequestParam("jdictionId") Integer jdictionId) {
		ResultValue rv = new ResultValue();
		try {
			Users login = this.userService.login(userId, userPassword, jdictionId);
			if (login != null) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("loginUser", login);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("你输入的账号信息不正确！请重新输入！");
		return rv;
	}

	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Users insert = this.userService.insert(users);
			if (insert != null) {
				rv.setCode(0);
				rv.setMessage("用户录入成功！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户录入失败！");
		return rv;
	}

	/**
	 * 查询满足条件的用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/select")
	public ResultValue select(Users users, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			Page<Users> page = this.userService.select(users, pageNumber);
			// 获得分页的数据
			List<Users> list = page.getContent();
			// 判断是否查询到了数据
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				// 将分页的数据添加到Map中
				map.put("userList", list);

				PageUtils pageUtils = new PageUtils((int) page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				// 将分页信息添加到Map中
				map.put("pageUtils", pageUtils);
				// 将Map添加到ResultValue对象中
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}

	/**
	 * 修改指定Id的状态
	 * 
	 * @param userId     用户编号
	 * @param userStatus 用户状态
	 * @return
	 */
	@RequestMapping(value = "/updateStatus")
	public ResultValue updateStatus(@RequestParam("userId") Integer userId,
			@RequestParam("userStatus") Integer userStatus) {
		ResultValue rv = new ResultValue();
		try {
			Integer status = this.userService.updateStatus(userId, userStatus);
			if (status > 0) {
				rv.setCode(0);
				rv.setMessage("用户修改成功！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户修改失败！！");
		return rv;
	}

	/**
	 * 查询指定编号的用户信息
	 * 
	 * @param userId 用户编号
	 * @return
	 */
	@RequestMapping(value = "select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		try {
			// 调用service中的方法，并获得查询的结果
			Users users = this.userService.selectById(userId);
			// 如果成功
			if (users != null) {
				// 设置成功的状态为0
				rv.setCode(0);
				// 创建Map集合保存查询结果
				Map<String, Object> map = new HashMap<>();
				// 将查询结果保存到Map集合中
				map.put("user", users);
				// 将Map集合添加到ResultValue对象中
				rv.setDataMap(map);
				// 返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("获得用户信息失败！！");
		return rv;
	}
	
	@RequestMapping(value="/update")
	public ResultValue update(Users users) {
		ResultValue rv = new ResultValue();
		
		try {
			//调用service中相应的方法修改用户信息，并获得修改是否成功
			Integer update = this.userService.update(users);
			//如果修改成功
			if(update > 0) {
			//设置结果的状态为0
				rv.setCode(0);
			//返回ResultValue的对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息修改失败！！");
		return rv;
	}
	
	@RequestMapping(value="/updatePhoto")
	public ResultValue updatePhoto(Users users) {
		ResultValue rv = new ResultValue();
		
		try {
			//调用service中相应的方法修改用户头像，并获得修改是否成功
			Integer update = this.userService.updatePhoto(users);
			//如果修改成功
			if(update > 0) {
			//设置结果的状态为0
				rv.setCode(0);
				rv.setMessage("用户头像修改成功！！");
			//返回ResultValue的对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户头像修改失败！！");
		return rv;
	}
	
	@RequestMapping(value="/updatePassword")
	public ResultValue updatePassword(Users users) {
		ResultValue rv = new ResultValue();
		
		try {
			//调用service中相应的方法修改用户密码，并获得修改是否成功
			Integer update = this.userService.updatePassword(users);
			//如果修改成功
			if(update > 0) {
			//设置结果的状态为0
				rv.setCode(0);
				rv.setMessage("用户密码修改成功！！");
			//返回ResultValue的对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户密码修改失败！！");
		return rv;
	}
	
	@RequestMapping(value="/updateUserName")
	public ResultValue updateUserName(Users users) {
		ResultValue rv = new ResultValue();
		
		try {
			//调用service中相应的方法修改用户名称，并获得修改是否成功
			Integer update = this.userService.updateUserName(users);
			//如果修改成功
			if(update > 0) {
			//设置结果的状态为0
				rv.setCode(0);
				rv.setMessage("用户名称修改成功！！");
			//返回ResultValue的对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户名称修改失败！！");
		return rv;
	}
}
