package pl.kwisniewski.database.abstr;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDao<T extends AbstractEntity> {
	
	
	private static Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
	
	
	private EntityManager em;
	
	
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public long create(T entity) {		
		getEm().persist(entity);
		return entity.getId();
	}
	
	public T read(long id, Class<? extends AbstractEntity> entityClass){
		return (T)getEm().find(entityClass, id);
	}
	
	public T update(T entity){		
		return getEm().merge(entity);
	}
	
	public void delete(T entity){
		entity = getEm().merge(entity);
		getEm().remove(entity);
	}

}
