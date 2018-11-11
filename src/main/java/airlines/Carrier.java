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
public class Carrier {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String carrierName;
	private String description;
	
	@ManyToMany
	private Collection<Destination> destinations;
	
	@ManyToOne
	private AirlineForm airlineForm;
	
	
//	@OneToMany(mappedBy="carrier")
//	private Collection<AirlineForm> airlineForms;
	
	public long getId() {

		return id;
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
	
	public Carrier() {
		
	}

	public Carrier(String carrierName, String description, Destination...destinations) {
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
		Carrier other = (Carrier) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
	
}
