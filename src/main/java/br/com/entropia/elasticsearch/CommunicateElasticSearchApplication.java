package br.com.entropia.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
		return RestClient.builder(
				new HttpHost(hostname, port, scheme)
		).build();
	}

}
