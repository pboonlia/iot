package app.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.model.Person;
import app.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Person>> list() {
		List<Person> personList = personService.findAll();
		for (Person person : personList) {
			person.add(linkTo(methodOn(PersonController.class).view(person.getPersonId())).withRel("person"));
		}
		return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> view(@PathVariable(value = "id") String id) {
		Person person = new Person();
		person = personService.find(id);
		person.add(linkTo(methodOn(PersonController.class).view(person.getPersonId())).withRel("person"));
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Person> create(@RequestBody Person person) {
		Person createdPerson = personService.save(person);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdPerson.getPersonId()).toUri());
		return new ResponseEntity<Person>(httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Person> update(@PathVariable(value = "id") String id, @RequestBody Person person) {
		personService.update(id, person);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Person> delete(@PathVariable("id") String id) {
		personService.delete(id);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}
}