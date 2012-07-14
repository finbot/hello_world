package pl.kwisniewski.services;

import java.util.List;


import pl.kwisniewski.daos.plain.SimpleDao;
import pl.kwisniewski.entities.plain.SimpleEntity;


public class SimpleService {

    SimpleDao dao = new SimpleDao();
	
	public List<SimpleEntity> getSimpleEntityList(){
          return dao.getSimpleEntityList();
	}
	
	public void createSimpleEntity(SimpleEntity entity){
          dao.createSimpleEntity(entity);
	}
	
	public SimpleEntity getSimpleEntity(String id){
         return dao.getSimpleEntity(id);
	}
	
	public void updateSimpleEntity(SimpleEntity entity){
        dao.updateSimpleEntity(entity);
	}
	
	public void deleteSimpleEntity(SimpleEntity entity){
        dao.deleteSimpleEntity(entity);
	}

}
