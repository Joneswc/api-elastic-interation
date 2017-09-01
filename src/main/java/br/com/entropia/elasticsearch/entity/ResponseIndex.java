package br.com.entropia.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseIndex {

    @JsonProperty(value = "_index")
    private String index;

    @JsonProperty(value = "_type")
    private String type;

    @JsonProperty(value = "_id")
    private String id;

    @JsonProperty(value = "_version")
    private String version;

    @JsonProperty(value = "found")
    private String found;

    @JsonProperty(value = "_source")
    private IndexSource source;

}
