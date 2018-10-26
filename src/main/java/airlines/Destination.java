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
	
	
	@ManyToMany
	private Collection<AirlineForm> airlineForms;//Domestic/International


	public long getId() {
	
		return id;
	}

	public String getName() {
		
		return name;
	}

	public Collection<AirlineForm> getAirlineForms() {
		
		return airlineForms;
	}

	public Destination() {

	}

	public Destination(String name, AirlineForm...airlineForms) {

		this.name=name;
		this.airlineForms = new HashSet<>(Arrays.asList(airlineForms));

	}
	
}
