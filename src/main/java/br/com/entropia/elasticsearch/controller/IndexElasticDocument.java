package br.com.entropia.elasticsearch.controller;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController()
public class IndexElasticDocument {

    @Autowired
    RestClient restClient;

    @RequestMapping(value = "/index/{id}", method = RequestMethod.POST)
//    public Response addIndex(@RequestBody Map<String, String> params) throws IOException {
    public String addIndex(@RequestBody String person, @PathVariable String id) throws IOException {
        HttpEntity entity = new NStringEntity(person);
        Response response = restClient.performRequest(
                "POST",
                "/accounts/person/" + id,
                Collections.<String, String>emptyMap(), // param maps to querystring
                entity);
        return EntityUtils.toString(response.getEntity());
    }

    @RequestMapping(value = "/elasticserver")
    public String testElasticServer() throws IOException {
        Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
        Response response = restClient.performRequest("GET", "/", headers);
        return EntityUtils.toString(response.getEntity());
    }

    @RequestMapping(value = "/search/{id}")
    public String searchById(@PathVariable String id) throws IOException {
        Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
        Response response = restClient.performRequest("GET", "accounts/person/" + id, headers);
        return EntityUtils.toString(response.getEntity());
    }

}
