package br.com.entropia.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IndexSource {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "lastname")
    private String lastName;

    @JsonProperty(value = "jobDescription")
    private String jobDescription;

}
