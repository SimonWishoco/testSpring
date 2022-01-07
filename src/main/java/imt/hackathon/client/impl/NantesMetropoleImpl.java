package imt.hackathon.client.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import imt.hackathon.client.NantesMetropole;
import reactor.core.publisher.Mono;

@Service
public class NantesMetropoleImpl implements NantesMetropole {
	
	private final static String URL = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_etablissements-enseignement-superieur-recherche-nantes-metropole";

	@Bean
	public WebClient localApiClient() {
		return WebClient.create(URL);
	}

	@Override
	public String getUniversities() {
		Mono<String> result = localApiClient()
				.get()
				.retrieve()
				.bodyToMono(String.class);
		return result.block();
	}

}
