package br.com.entropia.elasticsearch.controller;

import br.com.entropia.elasticsearch.entity.ResponseIndex;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;

@RequestScope
@RestController
public class SearchDocumentController {

    @Autowired
    private RestClient restClient;

    @Value("${elastic.document.endpoint}")
    private String documentEndPoint;

    @RequestMapping(value = "/search/{id}")
    public ResponseIndex searchById(@PathVariable String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
        Response response = restClient.performRequest("GET", documentEndPoint + id, headers);
        return mapper.readValue(response.getEntity().getContent(), ResponseIndex.class);
    }

}
