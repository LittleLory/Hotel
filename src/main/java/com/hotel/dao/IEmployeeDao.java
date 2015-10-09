package com.hotel.dao;

import java.sql.SQLException;
import java.util.List;

import com.hotel.model.Employee;

public interface IEmployeeDao extends IBaseDao{
	/**
	 * 获取根据函数参数employee内成员的值获取员工信息，employee的成员可以全部为空
	 * 这时候获取所有的员工信息，也可以一部分是空，这时候根据非空的信息获取员工信息
	 * @param employee,可以有一到多个成员非空
	 * @return
	 * @throws java.sql.SQLException
	 * @throws ClassNotFoundException 
	 */
	public List<Employee> getEmployeeInfo(Employee employee) throws ClassNotFoundException, SQLException;
	/**
	 * 修改员工信息，参数employee的成员若为空,代表不修改数据库中对应的字段,
	 * employee.account不能为空
	 *
	 * @param employee
	 * @throws Exception 
	 * @return 返回更新成功的数据的条数
	 */
	public int updateEmployeeInfo(Employee employee) throws Exception;
	/**
	 * 插入一条新员工的信息
	 * 根据数据库约束的设定，请保持employee中各字段均非空
	 * @param employee
	 * @throws Exception 
	 * @return 返回成功插入的条数
	 */
	public int insertEmployeeInfo(Employee employee) throws Exception;
	/**
	 * 删除员工
	 * @param account 员工的帐号
	 * @return 删除成功的条数
	 * @throws Exceptio
	 */
	public int deleteEmployee(String account) throws Exception;
}
