package airlines;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class AirlineForm {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	@ManyToOne
	private Carrier carrier;

	public long getId() {

		return id;
	}

	public String getName() {

		return name;
	}

	public AirlineForm() {

	}

	public AirlineForm(String name, Carrier carrier) {

		this.name = name;
		this.carrier = carrier;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirlineForm other = (AirlineForm) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
