package com.cours.ebenus.maven.ebenus.dao.impl;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.IDao;
import com.cours.ebenus.maven.ebenus.dao.entities.User;;


public abstract class AbstractDao<T> implements IDao<T> {
	
	
	private Class<T> myClass = null;

	
    public AbstractDao(Class<T> myClass) {
        this.myClass = myClass;
    }

    
    @Override
    public List<T> findAll() {
        return null;
    }

    
    @Override
    public T findById(int id) {
        return null;
    }

    
    @Override
    public List<T> findByCriteria(String criteria, Object valueCriteria) {
        return null;
    }

    
    @Override
    public T create(T t) {
        return null;
    }

    
    @Override
    public T update(T t) {
        return null;
    }

    
    @Override
    public boolean delete(T t) {
        return false;
    }
    

}
