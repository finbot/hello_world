package pl.kwisniewski.test.abstr;


import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.hibernate.impl.SessionImpl;

public abstract class AbstractDBUnitTestIntg {
	
	
	private static final String PERSISTENCE_UNIT_NAME = "pu";
	private static EntityManager em;
	private static EntityTransaction tx;
	private static IDatabaseConnection connection;
	
	
	private static void beforeTransaction() {
		openEntityManager();
		openDBUnit();
	}	

	private static void afterTransaction() {
		closeEntityManager();
		closeDBUnit();
	}	
	
	public void createTransaction() {
		beforeTransaction();
		setTx(getEm().getTransaction());
		getTx().begin();
	}
	
	public void closeTransaction() {
		getTx().commit();
		afterTransaction();
	}
	
	protected static void openEntityManager(){
		setEm(Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
				.createEntityManager());
	}
	
	protected static void closeEntityManager(){
		getEm().close();
	}
	
	protected static void openDBUnit() {
		
		try {
			connection = new DatabaseConnection(((SessionImpl)getEm().getDelegate()).connection());
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		}
		
	}

	protected static void closeDBUnit() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void executeDataFile(String path) {

		try {
			IDataSet dataSet = new FlatXmlDataSet(this.getClass()
					.getClassLoader().getResourceAsStream(path));

			Assert.assertNotNull(connection);
			Assert.assertNotNull(dataSet);

			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}	
	
	
	// *************************************************** //
	// ************* GETTERS AND SETTERS ***************** //
	// *************************************************** //
	
	
	protected static EntityManager getEm() {
		return em;
	}
	protected static void setEm(EntityManager em) {
		AbstractDBUnitTestIntg.em = em;
	}
	
	protected static EntityTransaction getTx() {
		return tx;
	}
	protected static void setTx(EntityTransaction tx) {
		AbstractDBUnitTestIntg.tx = tx;
	}

}
