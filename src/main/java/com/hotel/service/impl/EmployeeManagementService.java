package com.hotel.service.impl;

import java.sql.SQLException;
import java.util.List;


import com.hotel.dao.IEmployeeDao;
import com.hotel.dao.impl.EmployeeDaoImpl;
import com.hotel.model.Employee;
import com.hotel.service.IEmployeeManagementService;

public class EmployeeManagementService  implements IEmployeeManagementService{
	IEmployeeDao employeeDao = new EmployeeDaoImpl();
	/**
	 * 根据函数参数employee内成员的值获取员工信息，employee的成员可以全部为空
	 * 这时候获取所有的员工信息，也可以一部分是空，这时候根据非空的信息获取员工信息
	 * XXX:	操作只涉及单张表
	 * 		eg: 类Employee中有成员Branch，但是表EMPLOYEE中仅有branch_id字段，
	 * 			则本函数只能获取branch_id字段，而不涉及其它表的查询，查询结果中Branch的
	 * 			其他字段均为空
	 * @param employee,可以有一到多个成员为空
	 * @return
	 * @throws Exception 
	 */
	public List<Employee> getEmployeeInfo(Employee employee) throws Exception{
		try {
			return employeeDao.getEmployeeInfo(employee);
		} finally {
			employeeDao.closeResource();
		}
	}
	/**
	 * 修改员工信息，参数employee的成员若为空,代表不修改数据库中对应的字段,
	 * employee.account(对应数据库中该表的主键)不能为空
	 * XXX:	仅修改Employee这张表对应的字段
	 * 		eg: 类Employee中有成员Branch，但是表EMPLOYEE中仅有branch_id字段，
	 * 			则本函数只能更改branch_id字段，而不涉及其它表的更改
	 * @param employee
	 * @throws Exception 
	 * @return 返回更新成功的数据的条数
	 */
	public int updateEmployeeInfo(Employee employee) throws Exception  {
		try {
			return employeeDao.updateEmployeeInfo(employee);
		} finally {
			// TODO Auto-generated catch block
			employeeDao.closeResource();
		}
	}
	/**
	 * 插入一条新员工的信息
	 * 根据数据库约束的设定，请保持employee中各字段均非空
	 * @param employee
	 * @throws Exception 
	 * @return 返回成功插入的条数
	 */
	public int insertEmployeeInfo(Employee employee) throws Exception {
		try {
			return employeeDao.insertEmployeeInfo(employee);
		} finally {
			// TODO Auto-generated catch block
			employeeDao.closeResource();
		}
	}
	/**
	 * 删除员工
	 * @param account 员工的帐号
	 * @return 删除成功的条数
	 * @throws Exceptio
	 */
	public int deleteEmployee(String account) throws Exception {
		try {
			return employeeDao.deleteEmployee(account);
		} finally {
			// TODO: handle exception
			employeeDao.closeResource();
		}
	}
	
}
