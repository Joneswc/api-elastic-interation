package br.com.entropia.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommunicateElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunicateElasticSearchApplication.class, args);
	}

	@Bean
	public RestClient client() {
		RestClient restClient = RestClient.builder(
				new HttpHost("localhost", 9200, "http")
		).build();
		return restClient;
	}

}
