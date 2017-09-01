package br.com.entropia.elasticsearch.controller;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.util.Collections;

@RequestScope
@RestController
public class SearchDocumentController {

    @Autowired
    private RestClient restClient;

    @Value("${elastic.document.endpoint}")
    private String documentEndPoint;

    @RequestMapping(value = "/search/{id}")
    public ResponseEntity searchById(@PathVariable String id) throws IOException {
        Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
        Response response = restClient.performRequest("GET", documentEndPoint + id, headers);
        return (ResponseEntity) response.getEntity();
    }

}
