package com.hotel.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.dao.IEmployeeDao;
import com.hotel.model.BranchInfo;
import com.hotel.model.City;
import com.hotel.model.Employee;
import com.hotel.model.Province;
import com.hotel.model.Role;

public class EmployeeDaoImpl extends BaseDao implements IEmployeeDao{
	/**
	 * 获取根据函数参数employee内成员的值获取员工信息，employee的成员可以全部为空
	 * 这时候获取所有的员工信息，也可以一部分是空，这时候根据非空的信息获取员工信息
	 * @param employee,可以有一到多个成员非空
	 * @return
	 * @throws java.sql.SQLException
	 * @throws ClassNotFoundException 
	 */
	public List<Employee> getEmployeeInfo(Employee employee) throws ClassNotFoundException, SQLException {
		List<Employee> list = new ArrayList<Employee>();
		String sql = "select * from employee where 1=1";
		if(employee.getAccount() != null) 
			sql = sql + " and ACCOUNT='"+ employee.getAccount() +"'";
		if(employee.getPassword() != null) 
			sql = sql + " and PASSWORD='"+ employee.getPassword() +"'";
		if(employee.getName() != null)
			sql = sql + " and NAME='" + employee.getName() + "'";
		if(employee.getSex() != null)
			sql = sql + " and SEX='" + employee.getSex() + "'";
		if(employee.getSpecificAddress() != null)
			sql = sql + " and SPECFIC_ADDRESS like '%" + employee.getSpecificAddress() + "%'";
		if(employee.getIDNumber() != null)
			sql = sql + " and ID_NUMBER='" + employee.getIDNumber() + "'";
		if(employee.getProvince() != null)
			sql = sql + " and PROVINCE_ID=" + employee.getProvince().getProvinceId();
		if(employee.getCity() != null)
			sql = sql + " and CITY_ID=" + employee.getCity().getCityId();
		if(employee.getRole() != null)
			sql = sql + " and ROLE_ID=" + employee.getRole().getRoleId();
		if(employee.getBranch() != null)
			sql = sql + " and BRANCH_ID=" + employee.getBranch().getBranchId();
		Date tmp = null;
		if(employee.getWorkDate() != null){
			 tmp = new Date(employee.getWorkDate().getTime() + 24*3600*1000);
			 tmp.setHours(0);
			 tmp.setMinutes(0);
			 sql = sql + " and WORK_DATE >= ? and WORK_DATE < ?";
		}
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		if(employee.getWorkDate() != null){
			ps.setTimestamp(1, new Timestamp(employee.getWorkDate().getTime()));
			ps.setTimestamp(2, new Timestamp(tmp.getTime()));
		}
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Employee employeeReturned = new Employee();
			employeeReturned.setAccount(rs.getString("ACCOUNT"));
			
			BranchInfo branchInfo = new BranchInfo();
			branchInfo.setBranchId(rs.getInt("BRANCH_ID"));
			employeeReturned.setBranch(branchInfo);
			
			City city = new City();
			city.setCityId(rs.getInt("CITY_ID"));
			employeeReturned.setCity(city);
			
			employeeReturned.setIDNumber(rs.getString("ID_NUMBER"));
			employeeReturned.setName(rs.getString("NAME"));
			employeeReturned.setPassword(rs.getString("PASSWORD"));
			
			Province province = new Province();
			province.setProvinceId(rs.getInt("PROVINCE_ID"));
			employeeReturned.setProvince(province);
			
			Role role = new Role();
			role.setRoleId(rs.getInt("ROLE_ID"));
			employeeReturned.setRole(role);
			
			employeeReturned.setSex(rs.getString("SEX"));
			employeeReturned.setSpecificAddress(rs.getString("SPECFIC_ADDRESS"));
			employeeReturned.setWorkDate(rs.getTimestamp("WORK_DATE"));
			
			list.add(employeeReturned);
		}
		rs.close();
		ps.close();
		return list;
	}
	/**
	 * 删除员工
	 * @param account 员工的帐号
	 * @return 删除成功的条数
	 * @throws Exception
	 */
	public int deleteEmployee(String account) throws Exception {
		String sql = "delete from EMPLOYEE where account='"+ account +"'";
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		int result = ps.executeUpdate();
		ps.close();
		return result;
	}
	/**
	 * 修改员工信息，参数employee的成员若为空,代表不修改数据库中对应的字段,
	 * employee.account(对应数据库中该表的主键)不能为空
	 *
	 * @param employee
	 * @throws Exception 
	 * @return 返回更新成功的数据的条数
	 */
	public int updateEmployeeInfo(Employee employee) throws Exception{
		String sql = "update EMPLOYEE set ";
		if(employee.getAccount() == null) throw new Exception("account can't be null");
		sql = sql + " ACCOUNT=" + "'" + employee.getAccount() + "'";
		
		if(employee.getPassword() != null) 
			sql = sql + ", PASSWORD=?";
		if(employee.getName() != null)
			sql = sql + ", NAME=?";
		if(employee.getSex() != null)
			sql = sql + ", SEX=?";
		if(employee.getSpecificAddress() != null)
			sql = sql + ", SPECFIC_ADDRESS=?";
		if(employee.getIDNumber() != null)
			sql = sql + ", ID_NUMBER=?";
		if(employee.getProvince() != null)
			sql = sql + ", PROVINCE_ID=?";
		if(employee.getCity() != null)
			sql = sql + ", CITY_ID=?";
		if(employee.getRole() != null)
			sql = sql + ", ROLE_ID=?";
		if(employee.getBranch() != null)
			sql = sql + ", BRANCH_ID=?";
		if(employee.getWorkDate() != null)
			 sql = sql + ", WORK_DATE=?";
		sql = sql + "where ACCOUNT=" + "'"+ employee.getAccount() + "'";
		
		int currentArgs = 1;
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		
		if(employee.getPassword() != null) 
			ps.setString(currentArgs++, employee.getPassword());
		if(employee.getName() != null)
			ps.setString(currentArgs++, employee.getName());
		if(employee.getSex() != null)
			ps.setString(currentArgs++, employee.getSex());
		if(employee.getSpecificAddress() != null)
			ps.setString(currentArgs++, employee.getSpecificAddress());
		if(employee.getIDNumber() != null)
			ps.setString(currentArgs++, employee.getIDNumber());
		if(employee.getProvince() != null)
			ps.setInt(currentArgs++, employee.getProvince().getProvinceId());
		if(employee.getCity() != null)
			ps.setInt(currentArgs++, employee.getCity().getCityId());
		if(employee.getRole() != null)
			ps.setInt(currentArgs++, employee.getRole().getRoleId());
		if(employee.getBranch() != null)
			ps.setInt(currentArgs++, employee.getBranch().getBranchId());
		if(employee.getWorkDate() != null)
			ps.setTimestamp(currentArgs++, new Timestamp(employee.getWorkDate().getTime()));
		
		int result = ps.executeUpdate();
		ps.close();
		return result;
		
		
	}
	/**
	 * 插入一条新员工的信息
	 * 根据数据库约束的设定，请保持employee中各字段均非空
	 * @param employee
	 * @throws Exception 
	 * @return 返回成功插入的条数
	 */
	public int insertEmployeeInfo(Employee employee) throws Exception {
		String sql = "insert into EMPLOYEE (ACCOUNT,PASSWORD,NAME,SEX,SPECFIC_ADDRESS,ID_NUMBER,WORK_DATE,PROVINCE_ID,CITY_ID,ROLE_ID,BRANCH_ID)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?)";
		this.openconnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		
		ps.setString(1, employee.getAccount());
		ps.setString(2, employee.getPassword());
		ps.setString(3, employee.getName());
		ps.setString(4, employee.getSex());
		ps.setString(5, employee.getSpecificAddress());
		ps.setString(6, employee.getIDNumber());
		ps.setTimestamp(7, new Timestamp(employee.getWorkDate().getTime()));
		ps.setInt(8, employee.getProvince().getProvinceId());
		ps.setInt(9, employee.getCity().getCityId());
		ps.setInt(10, employee.getRole().getRoleId());
		ps.setInt(11, employee.getBranch().getBranchId());
		
		int result = ps.executeUpdate();
		ps.close();
		return result;
	}
}

