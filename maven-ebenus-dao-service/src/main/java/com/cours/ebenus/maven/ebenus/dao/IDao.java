package com.cours.ebenus.maven.ebenus.dao;

import java.util.List;


public interface IDao<T> {

	
	List<T> findAll();

	
	T findById(int id);

	
	List<T> findByCriteria(String criteria, Object valueCriteria);

	
	T create(T t);

	
	boolean delete(T t);

	
	T update(T t);

	
}
