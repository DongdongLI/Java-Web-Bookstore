package com.dongdong.bookstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dongdong.bookstore.db.JdbcUtils;
import com.dongdong.bookstore.utils.ReflectionUtils;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

public class BaseDao<T> implements Dao<T> {
	private QueryRunner queryRunner=new QueryRunner();
	private Class<T> clazz;
	
	public BaseDao(){
		clazz=ReflectionUtils.getSuperGenericType(getClass());
	}
	@Override
	public long insert(String sql, Object... args) { 
		long id=0;
		
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			conn=JdbcUtils.getConnection();
			preparedStatement=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			if(args!=null){
				for(int i=0;i<args.length;i++){
					preparedStatement.setObject(i+1, args[i]);
				}
			}
			preparedStatement.executeUpdate();
			// get the key
			resultSet=preparedStatement.getGeneratedKeys();
			
			if(resultSet.next())
			{
				id=resultSet.getLong(1);
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(conn);
			JdbcUtils.release(resultSet, preparedStatement);
		}
		
		return id;
	}

	@Override
	public void update(String sql, Object... args) {
		Connection conn=null;
		try{
			conn=JdbcUtils.getConnection();
			queryRunner.update(conn,sql,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JdbcUtils.releaseConnection(conn);
	}

	// return an object of T
	@Override
	public T query(String sql, Object... args) {
		Connection conn=null;
		try{
			conn=JdbcUtils.getConnection();
			return queryRunner.query(conn, sql,new BeanHandler<>(clazz),args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JdbcUtils.releaseConnection(conn);
		return null;
	}

	// return a list of objects of T
	@Override
	public List<T> queryForList(String sql, Object... args) {
		Connection conn=null;
		try{
			conn=JdbcUtils.getConnection();
			return queryRunner.query(conn, sql, new BeanListHandler<>(clazz),args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JdbcUtils.releaseConnection(conn);
		return null;
	}

	@Override
	public <V> V getSingleVal(String sql, Object... args) {
		Connection conn=null;
		try{
			conn=JdbcUtils.getConnection();
			return (V)queryRunner.query(conn, sql, new ScalarHandler<>(),args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JdbcUtils.releaseConnection(conn);
		return null;
	}

	@Override
	public void batch(String sql, Object[]... params) {
		Connection conn=null;
		try{
			conn=JdbcUtils.getConnection();
			queryRunner.batch(conn,sql,params);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		JdbcUtils.releaseConnection(conn);
	}

}
