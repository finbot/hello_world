package pl.kwisniewski.daos.plain;

import junit.framework.Assert;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.codehaus.jettison.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import pl.kwisniewski.entities.plain.SimpleEntity;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/13/12
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDaoTest {


    private String HOST_PATH = "http://127.0.0.1:5984/";
    private String BASE_NAME = "data_base_test";

    SimpleDao dao;


    @Before
    public void setUp() throws IOException {
        dao = new SimpleDao();
        dao.setHOST_PATH(HOST_PATH);
        dao.setBASE_NAME(BASE_NAME);
        deleteDb();
        createDb();
    }

    private void deleteDb() throws IOException {

        HttpClient client = new HttpClient();
        DeleteMethod deleteMethod = new DeleteMethod(HOST_PATH + BASE_NAME);
        client.executeMethod(deleteMethod);

    }

    private void createDb() throws IOException {

        HttpClient client = new HttpClient();
        PutMethod putMethod = new PutMethod(HOST_PATH + BASE_NAME);
        client.executeMethod(putMethod);

    }

    @Test
    public void getNewUUIDS() throws IOException, JSONException {

        String uuids = dao.getNewUUIDS();
        Assert.assertNotNull(uuids);

    }

    @Test
    public void createSimpleEntity() throws IOException, JSONException {

        SimpleEntity entity = new SimpleEntity();
        entity.setName("Chris");

        Assert.assertNull(entity.get_id());
        Assert.assertNull(entity.get_rev());

        dao.createSimpleEntity(entity);

        Assert.assertNotNull(entity.get_id());
        Assert.assertNotNull(entity.get_rev());
        Assert.assertEquals("Chris", entity.getName());

    }

    @Test
    public void getSimpleEntity() throws IOException, JSONException {

        SimpleEntity entity = new SimpleEntity();
        entity.setName("Chris");

        Assert.assertNull(entity.get_id());
        Assert.assertNull(entity.get_rev());

        dao.createSimpleEntity(entity);

        String id = entity.get_id();
        String rev = entity.get_rev();

        entity = dao.getSimpleEntity(id);

        Assert.assertEquals(id, entity.get_id());
        Assert.assertEquals(rev, entity.get_rev());
        Assert.assertEquals("Chris", entity.getName());

    }

    @Test
    public void updateSimpleEntity() throws IOException, JSONException {

        SimpleEntity entity = new SimpleEntity();
        entity.setName("Chris");
        dao.createSimpleEntity(entity);

        String oldRev = entity.get_rev();

        entity.setName("Jacek");
        dao.updateSimpleEntity(entity);

        String newRev = entity.get_rev();
        Assert.assertNotSame(oldRev, newRev);

        entity = dao.getSimpleEntity(entity.get_id());
        Assert.assertEquals("Jacek", entity.getName());

    }

    @Test
    public void deleteSimpleEntity() throws Exception {

        SimpleEntity entity = new SimpleEntity();
        entity.setName("Chris");
        dao.createSimpleEntity(entity);

        Assert.assertNotNull(entity.get_id());
        Assert.assertNotNull(entity.get_rev());

        dao.deleteSimpleEntity(entity);

    }

    @Test
    public void getSimpleEntityList() throws IOException, JSONException {

        SimpleEntity entity = null;

        entity = new SimpleEntity();
        entity.setName("Chris");
        dao.createSimpleEntity(entity);

        entity = new SimpleEntity();
        entity.setName("Jacek");
        dao.createSimpleEntity(entity);

        entity = new SimpleEntity();
        entity.setName("Ola");
        dao.createSimpleEntity(entity);

        List<SimpleEntity> list = dao.getSimpleEntityList();

        Assert.assertEquals(3, list.size());
        Assert.assertEquals("Chris", list.get(0).getName());
        Assert.assertEquals("Jacek", list.get(1).getName());
        Assert.assertEquals("Ola", list.get(2).getName());

    }

    @Test
    public void convertSimpleEntityToJsonString(){

        SimpleEntity entity = new SimpleEntity();
        entity.set_id("1");
        entity.set_rev("");
        entity.setName("Chris");

        String json = dao.convertSimpleEntityToJsonString(entity);

        Assert.assertEquals("{\"_id\":\"1\",\"_rev\":\"\",\"name\":\"Chris\"}", json);

    }

    @Test
    public void convertJsonStringToSimpleEntity(){

        String json = "{\"_id\":\"1\",\"_rev\":\"2\",\"name\":\"Chris\"}";
        SimpleEntity entity = dao.convertJsonStringToSimpleEntity(json);

        Assert.assertEquals("1", entity.get_id());
        Assert.assertEquals("2", entity.get_rev());
        Assert.assertEquals("Chris", entity.getName());

    }




}
