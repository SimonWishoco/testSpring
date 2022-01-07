package imt.hackathon.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imt.hackathon.client.NantesMetropole;
import imt.hackathon.client.Wikipedia;
import imt.hackathon.client.impl.WikipediaImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prototype")
@RequiredArgsConstructor
public class Prototype {
	
	private final NantesMetropole client;
	
	private final Wikipedia wikiClient;
	
	@GetMapping(params = {"name"})
	@CrossOrigin(origins = "*")
	public ResponseEntity<JsonResult> wiki(@RequestParam(value = "name") String name) {
		return ResponseEntity.ok(wikiClient.getInformations(name));
	}
	
	@GetMapping("universities")
	public ResponseEntity<String> getUniversities() {
		return ResponseEntity.ok(client.getUniversities());
	}

}