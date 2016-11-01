package app.mongoclient.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import app.mongoclient.model.PersonEntity;

@Service
public class PersonMongoImpl {

	@Value("${mongolab.rest.url}")
	private String resourceUrl;

	@Value("${mongolab.rest.api.key}")
	private String apiKey;

	public PersonEntity findById(String id) {
		RestTemplate restTemplate = new RestTemplate();
		String url = resourceUrl + "predix-test/collections/personEntity/" + id + "?apiKey=" + apiKey;
		PersonEntity personEntity = restTemplate.getForObject(url, PersonEntity.class);
		return personEntity;
	}

	public List<PersonEntity> findAll() {
		RestTemplate restTemplate = new RestTemplate();
		String url = resourceUrl + "predix-test/collections/personEntity" + "?apiKey=" + apiKey;
		PersonEntity[] list = restTemplate.getForObject(url, PersonEntity[].class);
		List<PersonEntity> personEntityList = Arrays.asList(list);
		return personEntityList;
	}

	public PersonEntity save(PersonEntity personEntity) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<PersonEntity> request = new HttpEntity<PersonEntity>(personEntity);
		String url = resourceUrl + "predix-test/collections/personEntity/" + "?apiKey=" + apiKey;
		PersonEntity createdPersonEntity = restTemplate.postForObject(url, request, PersonEntity.class);
		return createdPersonEntity;
	}
	
	public void delete(String id) {
		RestTemplate restTemplate = new RestTemplate();
		String url = resourceUrl + "predix-test/collections/personEntity/" + id + "?apiKey=" + apiKey;
		restTemplate.delete(url);;
	}
}
