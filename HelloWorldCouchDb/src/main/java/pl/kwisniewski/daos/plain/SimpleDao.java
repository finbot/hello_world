package pl.kwisniewski.daos.plain;


import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.*;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import pl.kwisniewski.entities.plain.SimpleEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleDao {


    private String HOST_PATH = "http://127.0.0.1:5984/";
    private String BASE_NAME = "data_base";

    HttpClient client = new HttpClient();


    protected String getNewUUIDS() throws IOException, JSONException {

        GetMethod getMethod = new GetMethod(getHOST_PATH() + "_uuids");
        client.executeMethod(getMethod);

        JSONObject jsonObject = new JSONObject(getMethod.getResponseBodyAsString());

        return jsonObject.getString("uuids").replace("[\"", "").replace("\"]", "");

    }

    public void createSimpleEntity(SimpleEntity entity) {

        try{

            String uuids = getNewUUIDS();
            String json = convertSimpleEntityToJsonString(entity);

            PutMethod putMethod = new PutMethod(getDATA_BASE_PATH() + uuids);
            putMethod.setRequestEntity(new StringRequestEntity(json, "application/json", "UTF-8"));
            client.executeMethod(putMethod);

            JSONObject jsonObject = new JSONObject(putMethod.getResponseBodyAsString());

            String _id = jsonObject.getString("id");
            String _rev = jsonObject.getString("rev");

            entity.set_id(_id);
            entity.set_rev(_rev);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public SimpleEntity getSimpleEntity(String id) {

        SimpleEntity entity = null;

        try{

            GetMethod getMethod = new GetMethod(getDATA_BASE_PATH() + id);
            client.executeMethod(getMethod);

            entity = convertJsonStringToSimpleEntity(getMethod.getResponseBodyAsString());

        }catch (Exception e){
            e.printStackTrace();
        }

        return entity;

    }

    public void updateSimpleEntity(SimpleEntity entity){

        try{

            String json = convertSimpleEntityToJsonString(entity);

            PutMethod putMethod = new PutMethod(getDATA_BASE_PATH() + entity.get_id());
            putMethod.setRequestEntity(new StringRequestEntity(json, "application/json", "UTF-8"));
            client.executeMethod(putMethod);

            JSONObject jsonObject = new JSONObject(putMethod.getResponseBodyAsString());

            String _rev = jsonObject.getString("rev");

            entity.set_rev(_rev);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void deleteSimpleEntity(SimpleEntity entity){

        try{

            String id = entity.get_id();
            String rev = entity.get_rev();

            DeleteMethod deleteMethod = new DeleteMethod(getDATA_BASE_PATH() + id + "?rev=" + rev);
            client.executeMethod(deleteMethod);

            JSONObject jsonObject = new JSONObject(deleteMethod.getResponseBodyAsString());

            boolean isOk = jsonObject.getBoolean("ok");
            if(!isOk){
                throw new Exception("Problem with deleting entity. Response from server: " + jsonObject.toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<SimpleEntity> getSimpleEntityList() {

        List<SimpleEntity> resultList = new ArrayList<SimpleEntity>();

        try{

            GetMethod getMethod = new GetMethod(getDATA_BASE_PATH() + "_all_docs");
            client.executeMethod(getMethod);

            JSONObject jsonObject = new JSONObject(getMethod.getResponseBodyAsString());
            JSONArray rows = jsonObject.getJSONArray("rows");
            for (int i = 0; i < rows.length() ; i++) {
                JSONObject jo = rows.getJSONObject(i);
                String id = jo.getString("id");
                resultList.add(getSimpleEntity(id));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return resultList;

    }


    // ***************** HELP METHODS *********************** //


    protected String convertSimpleEntityToJsonString(SimpleEntity entity){

        Gson gson = new Gson();
        return gson.toJson(entity);

    }

    protected SimpleEntity convertJsonStringToSimpleEntity(String json){

        Gson gson = new Gson();
        return gson.fromJson(json, SimpleEntity.class);

    }

    public String getHOST_PATH() {
        return HOST_PATH;
    }
    public void setHOST_PATH(String HOST_PATH) {
        this.HOST_PATH = HOST_PATH;
    }

    public String getBASE_NAME() {
        return BASE_NAME;
    }
    public void setBASE_NAME(String BASE_NAME) {
        this.BASE_NAME = BASE_NAME;
    }

    public String getDATA_BASE_PATH() {
        return HOST_PATH + BASE_NAME + "/";
    }

}
