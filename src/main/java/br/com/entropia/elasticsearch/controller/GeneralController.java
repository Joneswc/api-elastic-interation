package br.com.entropia.elasticsearch.controller;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Scope("request")
@RestController
public class GeneralController {

    private final RestClient restClient;

    @Autowired
    public GeneralController(RestClient restClient) {
        this.restClient = restClient;
    }

    @RequestMapping(value = "/elasticserver")
    public String testElasticServer() throws IOException {
        Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
        Response response = restClient.performRequest("GET", "/", headers);
        return EntityUtils.toString(response.getEntity());
    }

    @PreDestroy
    public void closeUsedBeans() throws Exception {
        this.restClient.close();
    }

}
