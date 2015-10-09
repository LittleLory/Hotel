package com.hotel.dao;

public interface IBaseDao {

	public abstract void closeResource();

	public abstract void beginTransaction() throws Exception;

	public abstract void commit() throws Exception;

	public abstract void rollback() throws Exception;

}