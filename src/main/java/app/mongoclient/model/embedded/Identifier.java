package app.mongoclient.model.embedded;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Identifier {
	@JsonProperty("$oid")
	String oid;
	
   

	public Identifier(@JsonProperty("$oid") String oid) {
		super();
		this.oid = oid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
}
