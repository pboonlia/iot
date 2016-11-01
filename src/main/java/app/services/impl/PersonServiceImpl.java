package app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Person;
import app.mongoclient.impl.PersonMongoImpl;
import app.mongoclient.model.PersonEntity;
import app.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMongoImpl personMongoImpl;

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		List<PersonEntity> personEntityList = personMongoImpl.findAll();
		List<Person> personList = new ArrayList<>();
		for (PersonEntity personEntity : personEntityList) {
			Person person = new Person();
			person.setPersonId(personEntity.getId().getOid());
			person.setFirstName(personEntity.getFirstName());
			person.setLastName(personEntity.getLastName());
			personList.add(person);
		}
		return personList;
	}

	@Override
	public Person find(String id) {
		// TODO Auto-generated method stub
		PersonEntity personEntity = personMongoImpl.findById(id);
		Person person = new Person();
		person.setPersonId(personEntity.getId().getOid());
		person.setFirstName(personEntity.getFirstName());
		person.setLastName(personEntity.getLastName());
		return person;
	}

	@Override
	public Person save(Person person) {
		// TODO Auto-generated method stub
		PersonEntity personEntity = new PersonEntity();
		personEntity.setFirstName(person.getFirstName());
		personEntity.setLastName(person.getLastName());
		personEntity = personMongoImpl.save(personEntity);
		person.setPersonId(personEntity.getId().getOid());
		return person;
	}

	@Override
	public void update(String id, Person person) {
		PersonEntity personEntity = personMongoImpl.findById(id);
		if (personEntity == null) {
			return;
		}

		if (person.getFirstName() != null) {
			personEntity.setFirstName(person.getFirstName());
		}

		if (person.getLastName() != null) {
			personEntity.setLastName(person.getLastName());
		}

		personMongoImpl.save(personEntity);

	}

	@Override
	public void delete(String id) {
		personMongoImpl.delete(id);
	}

}
