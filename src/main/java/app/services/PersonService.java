package app.services;

import java.util.List;

import app.model.Person;

public interface PersonService {

	List<Person> findAll();

	Person find(String id);

	Person save(Person person);

	void update(String id, Person person);

	void delete(String id);

}
