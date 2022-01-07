package imt.hackathon.client.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import imt.hackathon.client.Wikipedia;
import imt.hackathon.controllers.JsonResult;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
public class WikipediaImpl implements Wikipedia {
	
	private final static String URL = "https://en.wikipedia.org/api/rest_v1/page/summary/";

	@Bean
	public WebClient localApiWikiClient() {
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(true))).baseUrl(URL).build();
	}

	@Override
	public JsonResult getInformations(String name) {
		Mono<JsonResult> result = localApiWikiClient()
				.get()
				.uri(uriBuilder -> uriBuilder
						.path(name)
						.build())
				.retrieve()
				.bodyToMono(JsonResult.class);
		return new JsonResult(result.block().getDescription());
	}

}
