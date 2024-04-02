package com.database;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class AllDao {
	
	// customer
	
	public void saveCustomer(Customer customer) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.database.CustomerMapper.insertCustomer", customer);
		session.commit();
		session.close();
	}
	
	public List<Customer> getAllCustomerData(){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Customer> customers = session.selectList("com.database.CustomerMapper.findAllCustomer");
		session.close();
		return customers;
	}
	
	public Customer getCustomerById(long customerId){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Customer customer = session.selectOne("com.database.CustomerMapper.findCustomerById", customerId);
		session.close();
		return customer;
	}
	
	// factory
	
	public void saveFactory(Factory factory) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.database.FactoryMapper.insertFactory", factory);
		session.commit();
		session.close();
	}
	
	public List<Factory> getAllFactoryData(){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Factory> factories = session.selectList("com.database.FactoryMapper.findAllFactories");
		session.close();
		return factories;
	}
	
	public Factory getFactoryById(long factoryId){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Factory factory = session.selectOne("com.database.FactoryMapper.findFactoryById", factoryId);
		session.close();
		return factory;
	}
	
	public void deleteFactoryById(long factoryId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.delete("com.database.FactoryMapper.deleteFactoryById", factoryId);
            session.commit();
        }
    }

	// selections
	
	public List<CF> findAllFactoryCustomerRelationships() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<CF> cf = session.selectList("com.database.CFMapper.findAllFactoryCustomerRelationships");
		session.close();
		return cf;
	}
	
	public List<Factory> findFactoriesWithCustomers() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Factory> factory = session.selectList("com.database.CFMapper.findFactoriesWithCustomers");
		session.close();
		return factory;
	}
	
	// relationship
	
	public void saveRelationship(CF cf) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.database.CFMapper.insertRelationship", cf);
		session.commit();
		session.close();
    }
	
	public void deleteFactoryRelationshipById(long factoryId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.delete("com.database.CFMapper.deleteFactoryRelationship", factoryId);
            session.commit();
        }
    }


}
