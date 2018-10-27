package airlines;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AirlineForm {
	
	@Id
	@GeneratedValue
	private long id;
	
	
	private String name;
	private String carrierName;
	private String description;

	@OneToMany
	private Collection<Destination> destinations;
	//Domestic(Columbus, New York)
	//International(London,Tokyo)


	public long getId() {

		return id;
	}


	public String getName() {

		return name;
	}


	public String getCarrierName() {

		return carrierName;
	}

	public String getDescription() {
		
		return description;
	}
	
	
	public Collection<Destination> getDestinations() {
		
		return destinations;
	}
	
	public AirlineForm() {

	}

	public AirlineForm(String name, String carrierName, String description, Destination...destinations) {

		this.name = name;
		this.carrierName = carrierName;
		this.description = description;
		this.destinations = new HashSet<>(Arrays.asList(destinations));

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
