package br.com.entropia.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class CommunicateElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunicateElasticSearchApplication.class, args);
	}

	@Value("${elastic.hostname}")
	private String hostname;

	@Value("${elastic.port}")
	private int port;

	@Value("${elastic.scheme}")
	private String scheme;

	@Bean(destroyMethod = "close")
	public RestClient client() {
		RestClient restClient = RestClient.builder(
				new HttpHost(hostname, port, scheme)
		).build();
		return restClient;
	}

}
