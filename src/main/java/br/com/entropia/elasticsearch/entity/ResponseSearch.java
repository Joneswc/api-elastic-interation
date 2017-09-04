package br.com.entropia.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseSearch {

    @JsonProperty(value = "_index")
    private String index;

    @JsonProperty(value = "_type")
    private String _type;

    @JsonProperty(value = "_id")
    private String _id;

    @JsonProperty(value = "_version")
    private String _version;

    @JsonProperty(value = "found")
    private String found;

    @JsonProperty(value = "_source")
    private SearchSource source;

}
