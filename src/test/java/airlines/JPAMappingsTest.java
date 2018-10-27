package airlines;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private DestinationRepository destinationRepo;
	
	@Resource
	private AirlineFormRepository airlineFormRepo;
	
	
	@Test
	public void shouldSaveAndLoadDestination() {
		
		Destination city = destinationRepo.save(new Destination("Nairobi"));
		long destinationId = city.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Destination> result = destinationRepo.findById(destinationId);
		city = result.get();
		
		assertThat(city.getName(),is("Nairobi"));
	}
	
	
	@Test
	public void shouldGenerateDestinationById() {
		Destination city = destinationRepo.save(new Destination("Nairobi"));
		long destinationId = city.getId();
		
		entityManager.flush();
		entityManager.clear();
		

		assertThat(destinationId,is(greaterThan(0L)));
		
	}
	
	@Test
	public void shouldSaveAndLoadAFormOfAirline() {
		AirlineForm domestic = new AirlineForm("domestic","delta","description");
		domestic = airlineFormRepo.save(domestic);
		long domesticId = domestic.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<AirlineForm> result = airlineFormRepo.findById(domesticId);
		domestic = result.get();
		
		assertThat(domestic.getName(), is("domestic"));
		assertThat(domestic.getCarrierName(), is("delta"));
		assertThat(domestic.getDescription(), is("description"));
		
	}

	
	@Test
	public void shouldEstablishAirlineFormToDestinationsRelationships() {
		
		//destination is not the owner so we create this first
		
		Destination nairobi = destinationRepo.save(new Destination("Nairobi"));
		Destination tokyo = destinationRepo.save(new Destination("Tokyo"));
		
		AirlineForm international = new AirlineForm("International","KQ","Boeing 747 Flight# 788", nairobi, tokyo);
		international = airlineFormRepo.save(international);
		long airlineFormId = international.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<AirlineForm> result = airlineFormRepo.findById(airlineFormId);
		international = result.get();
		
		assertThat(international.getDestinations(),containsInAnyOrder(tokyo,nairobi));
	}
	
	
	
	@Test
	public void shouldFindAirlineFormForDestination() {
		
		Destination newyork = destinationRepo.save(new Destination("New York"));
		
		AirlineForm domesticNewYork = airlineFormRepo.save(new AirlineForm("Domestic","South West","Airbus A380 Flight # 122", newyork));
		AirlineForm domesticColumbus = airlineFormRepo.save(new AirlineForm("Domestic","Delta","Bombadier Flight # 123", newyork));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<AirlineForm> airlineFormForDestination = airlineFormRepo.findByDestinationsContains(newyork);
		
		assertThat(airlineFormForDestination,containsInAnyOrder(domesticNewYork,domesticColumbus));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
