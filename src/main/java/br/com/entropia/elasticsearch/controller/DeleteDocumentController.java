package br.com.entropia.elasticsearch.controller;

import br.com.entropia.elasticsearch.entity.ResponseIndex;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.util.Collections;

@RequestScope
@RestController
public class DeleteDocumentController {

    @Autowired
    private RestClient restClient;

    @Value("${elastic.document.deleteall}")
    private String deleteAllEndPoint;

    @Value("${elastic.document.endpoint}")
    private String deleteByIdEndPoint;

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseIndex deleteAll() throws IOException {
        return deleteDoc(deleteAllEndPoint);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseIndex deleteById(@PathVariable String id) throws IOException {
        return deleteDoc(deleteByIdEndPoint + id);
    }

    public ResponseIndex deleteDoc(String endPoint) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Response response = restClient.performRequest("DELETE", endPoint, Collections.emptyMap());
        return mapper.readValue(response.getEntity().getContent(), ResponseIndex.class);
    }

}
