package app.mongoclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import app.mongoclient.model.embedded.Identifier;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonEntity {

	@JsonProperty("_id")
	private Identifier id;

	private String firstName;
	private String lastName;
	
	public PersonEntity() {
		
	}
	public PersonEntity(@JsonProperty("_id") Identifier id, @JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
