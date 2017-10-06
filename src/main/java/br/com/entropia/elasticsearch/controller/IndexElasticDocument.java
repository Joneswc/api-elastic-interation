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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController()
public class IndexElasticDocument {

    @Autowired
    private RestClient restClient;

    @Value("${elastic.document.endpoint}")
    private String documentEndPoint;

    @RequestMapping(value = "/elasticserver")
    public String testElasticServer() throws IOException {
        Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
        Response response = restClient.performRequest("GET", "/", headers);
        return EntityUtils.toString(response.getEntity());
    }

}
