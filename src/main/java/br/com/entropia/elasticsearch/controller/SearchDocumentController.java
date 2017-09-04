package br.com.entropia.elasticsearch.controller;

import br.com.entropia.elasticsearch.entity.ResponseIndex;
import br.com.entropia.elasticsearch.entity.ResponseSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.util.HashMap;

@RequestScope
@RestController
public class SearchDocumentController {
//    https://www.elastic.co/blog/found-java-clients-for-elasticsearch
//    https://github.com/searchbox-io/Jest



// TODO - criar pesquisa com filtro via querystring
// TODO - DÚVIDA - consigo pesquisar com filtro por body ?
//
    @Autowired
    private RestClient restClient;

    @Value("${elastic.document.endpoint}")
    private String documentEndPoint;

    @Value("${elastic.search.querystring}")
    private String queryStringEndPoint;

    @RequestMapping(value = "/search/{id}")
    public ResponseSearch searchById(@PathVariable String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
        Response response = restClient.performRequest("GET", documentEndPoint + id, headers);
        ResponseSearch result = mapper.readValue(response.getEntity().getContent(), ResponseSearch.class);
        return result;
    }

    @RequestMapping(value = "/search/")
    public ResponseSearch searchByQueryString(@RequestBody String query) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
        HashMap<String, String> params = new ObjectMapper().readValue(query, HashMap.class);
        Response response = restClient.performRequest("GET", queryStringEndPoint, params, headers);
        ResponseSearch result = mapper.readValue(response.getEntity().getContent(), ResponseSearch.class);
        return result;
    }

}
