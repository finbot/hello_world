package pl.kwisniewski.services;

import java.util.List;

import javax.persistence.EntityManager;

import pl.kwisniewski.daos.plain.SimpleDao;
import pl.kwisniewski.entities.plain.SimpleEntity;
import pl.kwisniewski.util.UtilJPA;

public class SimpleService {
	
	public List<SimpleEntity> getSimpleEntityList(){
		
		List<SimpleEntity> list;
		
		EntityManager em = UtilJPA.createEntityManager();
		
		SimpleDao dao = new SimpleDao();
		dao.setEm(em);
		list = dao.getAllSimpleEntity();
		
		UtilJPA.closeEntityManager();
		
		return list;
		
	}
	
	public long createSimpleEntity(SimpleEntity entity){
		
		long id;
		
		EntityManager em = UtilJPA.createEntityManager();
		SimpleDao dao = new SimpleDao();
		
		dao.setEm(em);
		id = dao.create(entity);
		
		UtilJPA.closeEntityManager();
		
		return id;
		
	}
	
	public SimpleEntity getSimpleEntity(long id){
		
		SimpleEntity entity;
		
		SimpleDao dao = new SimpleDao();		
		EntityManager em = UtilJPA.createEntityManager();
		
		dao.setEm(em);
		entity = (SimpleEntity)dao.read(id, SimpleEntity.class);
		
		UtilJPA.closeEntityManager();
		
		return entity;
		
	}
	
	public void updateSimpleEntity(SimpleEntity entity){
		
		SimpleDao dao = new SimpleDao();		
		EntityManager em = UtilJPA.createEntityManager();
		
		dao.setEm(em);
		dao.update(entity);
		
		UtilJPA.closeEntityManager();
		
	}
	
	public void deleteSimpleEntity(SimpleEntity entity){
		
		SimpleDao dao = new SimpleDao();		
		EntityManager em = UtilJPA.createEntityManager();
		
		dao.setEm(em);
		dao.delete(entity);
		
		UtilJPA.closeEntityManager();
		
	}

}
