package app.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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
    public List<Person> list(){
        return personService.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Person view(@PathVariable(value="id") String id){
        return personService.find(id);
    }

    @RequestMapping(method = RequestMethod.POST )
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PATCH )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable( value="id") String id, @RequestBody Person person) {
    	personService.update(id, person);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        personService.delete(id);
    }
}