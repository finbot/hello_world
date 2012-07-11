package pl.kwisniewski.daos.plain;

import java.util.List;

import javax.persistence.Query;

import pl.kwisniewski.database.abstr.AbstractDao;
import pl.kwisniewski.entities.plain.SimpleEntity;

public class SimpleDao extends AbstractDao<SimpleEntity> {		

	public List<SimpleEntity> getAllSimpleEntity(){
		
		Query q = getEm().createNamedQuery("SimpleEntity.getAllSimpleEntity");
		return q.getResultList();
		
	}
	
}
