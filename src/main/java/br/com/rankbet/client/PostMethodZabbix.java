package br.com.rankbet.client;


import java.util.Scanner;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import org.apache.http.HttpResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostMethodZabbix {

    private String requestUrl;

    public PostMethodZabbix( String url ) {

        this.requestUrl = url;
    }


    public String getAuth() {

        Scanner sc = null;
        String key = "";
        try {
            String endpoint = this.requestUrl;

            String jsonBody = "{\n" + "    \"jsonrpc\": \"2.0\",\n" + "    \"method\": \"user.login\",\n" + "    \"params\": {\n"
                + "        \"user\": \"algar.monitoramento\",\n" + "        \"password\": \"W65qPucLXy%I\"\n" + "    },\n" + "    \"id\": 1,\n"
                + "    \"auth\": null\n" + "}";
            // Call the request
            HttpCallActions.POST( endpoint, jsonBody, HttpCallActions.getSSLCustomClient() );
            // Get the response
            HttpResponse response = HttpCallActions.getResponse();
            // Print the response
            sc = new Scanner( response.getEntity().getContent() );
            while ( sc.hasNext() ) {
                String result = sc.next();
                JsonObject jsonObject = new JsonParser().parse( result ).getAsJsonObject();

                key = jsonObject.get( "result" ).getAsString();

            }
        } catch ( Exception e ) {
            e.getMessage();
        }
        key = "\"" + key + "\"";
        return key;
    }


    public String getGroupId( String operadora, String key ) {

        Scanner sc = null;
        String gid = "";
        operadora = "\"" + operadora + "\"";
        try {
            String endpoint = this.requestUrl;

            String jsonBody = "" + "{\n" + "\"jsonrpc\": \"2.0\",\n" + "\"method\": \"hostgroup.get\",\n" + "\"params\": {\n" + "\"output\": \"extend\",\n"
                + "\"filter\": {\n" + "\"name\": [\n" + operadora + "\n" + "]\n" + "}\n" + "},\n" + "\"auth\":" + key + ",\n" + "\"id\":2\n" + "}\n";

            // Call the request
            HttpCallActions.POST( endpoint, jsonBody, HttpCallActions.getSSLCustomClient() );
            // Get the response
            HttpResponse response = HttpCallActions.getResponse();

            // Print the response
            sc = new Scanner( response.getEntity().getContent() );
            while ( sc.hasNext() ) {
                String result = sc.next();
                JsonObject jsonObject = new JsonParser().parse( result ).getAsJsonObject();

                JsonArray jsonArray = jsonObject.get( "result" ).getAsJsonArray();

                for ( int i = 0; i < jsonArray.size(); i++ ) {
                    System.out.println( jsonArray.get( i ).getAsJsonObject().get( "groupid" ).getAsString() );
                    gid = ( jsonArray.get( i ).getAsJsonObject().get( "groupid" ).getAsString() );
                }

            }
        } catch ( Exception e ) {
            e.getMessage();
        }

        return gid;
    }


    public void getHistbyItemId( String id, String auth ) {

        Scanner sc = null;
        String key = "";
        id = "[" + id + "]";
        System.out.println( id );
        try {
            String endpoint = this.requestUrl;
            String jsonBody =
                "{\n" + "    \"jsonrpc\": \"2.0\",\n" + "    \"method\": \"history.get\",\n" + "    \"params\": {\n" + "        \"output\": \"extend\",\n"
                    + "        \"history\": 0,\n" + "        \"itemids\": " + id + ",\n" + "        \"limit\": 20,\n" + "        \"sortfield\": \"clock\",\n"
                    + "        \"sortorder\": \"DESC\"\n" + "        \n" + "    },\n" + "    \"id\": 2,\n" + "    \"auth\":" + auth + "\n" + "}";

            // Call the request
            HttpCallActions.POST( endpoint, jsonBody, HttpCallActions.getSSLCustomClient() );
            // Get the response
            HttpResponse response = HttpCallActions.getResponse();
            System.out.println( "Status-code->" + response.getStatusLine().getStatusCode() );
            // Print the response
            sc = new Scanner( response.getEntity().getContent() );
            while ( sc.hasNext() ) {
                String result = sc.next();
                JsonObject jsonObject = new JsonParser().parse( result ).getAsJsonObject();

                JsonArray jsonArray = jsonObject.get( "result" ).getAsJsonArray();

                System.out.println( jsonArray.size() );

            }
        } catch ( Exception e ) {
            e.getMessage();
        }

    }

}
