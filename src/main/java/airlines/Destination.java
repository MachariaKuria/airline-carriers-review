package airlines;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Destination {

	
	@Id
	@GeneratedValue
	private long id;
	
	
	private String name;
	
	@ManyToMany(mappedBy="destinations")
	private Collection<Carrier> carriers;

	public long getId() {
	
		return id;
	}

	public String getName() {
		
		return name;
	}

	public Collection<Carrier> getCarriers() {
		return carriers;
	}

	public Destination() {

	}

	public Destination(String name, Carrier...carriers) {

		this.name=name;
		this.carriers = new HashSet<>(Arrays.asList(carriers));
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
		Destination other = (Destination) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
