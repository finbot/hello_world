package pl.kwisniewski.daos.plain;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;


import pl.kwisniewski.daos.plain.SimpleDao;
import pl.kwisniewski.entities.plain.SimpleEntity;
import pl.kwisniewski.test.abstr.AbstractDBUnitTestUnit;

public class SimpleDaoTest extends AbstractDBUnitTestUnit {

	static SimpleDao simpleDao;
	
	@BeforeMethod
	public void setUp(){
		simpleDao = new SimpleDao();
		simpleDao.setEm(getEm());
	}

	@Test
	public void create() {

		executeDataFile("dbunit/input.xml");
		long expected = 0;

		SimpleEntity entity = new SimpleEntity();
		entity.setName("Name4");
		simpleDao.create(entity);

		long actual = entity.getId();

		Assert.assertNotSame(expected, actual);

	}

	@Test
	public void read() {
		
		executeDataFile("dbunit/input.xml");
		String expected = "Name1";
		
		long id = 1;
		SimpleEntity entity = simpleDao.read(id, SimpleEntity.class);
		String actual = entity.getName();
		
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void update() {

		executeDataFile("dbunit/input.xml");
		String expected = "Name2";

		int id = 1;
		SimpleEntity entity = simpleDao.read(id, SimpleEntity.class);
		entity.setName(expected);
		simpleDao.update(entity);

		entity = simpleDao.read(id, SimpleEntity.class);
		String actual = entity.getName();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void delete() {
		
		executeDataFile("dbunit/input.xml");
		int id = 1;
		
		SimpleEntity entity = simpleDao.read(id, SimpleEntity.class);
		simpleDao.delete(entity);

		entity = simpleDao.read(id, SimpleEntity.class);
		Assert.assertNull(entity);

	}
	
	@Test
	public void getAllSimpleEntity(){
		
		executeDataFile("dbunit/input.xml");
		
		List<SimpleEntity> list = simpleDao.getAllSimpleEntity();
		
		Assert.assertEquals(3, list.size());
		
	}

}
