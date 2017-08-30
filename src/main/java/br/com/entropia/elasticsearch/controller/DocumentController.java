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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Collections;

@Scope("request")
@RestController()
public class DocumentController {

    private final RestClient restClient;

    @Autowired
    public DocumentController(RestClient restClient) {
        this.restClient = restClient;
    }

    @Value("${elastic.document.endpoint}")
    private String documentEndPoint;

    @RequestMapping(value = "/index/{id}", method = RequestMethod.POST)
    public String addIndex(@RequestBody String person, @PathVariable String id) throws IOException {
        HttpEntity entity = new NStringEntity(person);
        Response response = restClient.performRequest(
                "POST",
                documentEndPoint + id,
                Collections.<String, String>emptyMap(), // if you need to send param maps to querystring
                entity);
        return EntityUtils.toString(response.getEntity());
    }

    @RequestMapping(value = "/search/{id}")
    public String searchById(@PathVariable String id) throws IOException {
        Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
        Response response = restClient.performRequest("GET", documentEndPoint + id, headers);
        return EntityUtils.toString(response.getEntity());
    }

    @PreDestroy
    public void closeUsedBeans() throws Exception {
        this.restClient.close();
    }

}
