package br.com.entropia.elasticsearch.controller;

import br.com.entropia.elasticsearch.entity.ResponseIndex;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.util.Collections;

@RequestScope
@RestController
public class DocumentController {

    @Autowired
    private RestClient restClient;

    @Value("${elastic.document.endpoint}")
    private String documentEndPoint;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ResponseIndex addIndexWithoutId(@RequestBody String person) throws IOException {
        return addIndex(person, documentEndPoint);
    }

    @RequestMapping(value = "/index/{id}", method = RequestMethod.POST)
    public ResponseIndex addIndexWithId(@RequestBody String person, @PathVariable String id) throws IOException {
        return addIndex(person, documentEndPoint + id);
    }

    public ResponseIndex addIndex( String person, String endPoint) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpEntity entity = new NStringEntity(person);
        Response response = restClient.performRequest("POST", endPoint, Collections.emptyMap(), entity);
        return mapper.readValue(response.getEntity().getContent(), ResponseIndex.class);
    }

}
