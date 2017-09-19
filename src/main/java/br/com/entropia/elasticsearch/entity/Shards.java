package br.com.entropia.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Shards {

    @JsonProperty(value = "total")
    private String total;

    @JsonProperty(value = "successful")
    private String successful;

    @JsonProperty(value = "failed")
    private String failed;

}
