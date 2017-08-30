package br.com.entropia.elasticsearch.entity;

import lombok.Data;

@Data
public class ResponseIndex {

    private String index;
    private String type;
    private String id;
    private String version;
    private String found;
    private IndexSource source;

}
