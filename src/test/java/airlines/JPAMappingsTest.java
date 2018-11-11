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
	private CarrierRepository carrierRepo;

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

		assertThat(city.getName(), is("Nairobi"));
	}

	@Test
	public void shouldGenerateDestinationById() {
		Destination city = destinationRepo.save(new Destination("Nairobi"));
		long destinationId = city.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(destinationId, is(greaterThan(0L)));

	}

	@Test
	public void shouldSaveAndLoadCarrier() {

		Carrier delta = carrierRepo.save(new Carrier("Delta", "Boeing 747 Flight #122"));
		long carrierId = delta.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Carrier> result = carrierRepo.findById(carrierId);
		delta = result.get();

		assertThat(delta.getCarrierName(), is("Delta"));
		assertThat(delta.getDescription(), is("Boeing 747 Flight #122"));

	}

	@Test
	public void shouldGenerateCarrierId() {

		Carrier delta = carrierRepo.save(new Carrier("delta", "Boeing 747 Flight #122"));
		long carrierId = delta.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(carrierId, is(greaterThan(0L)));
	}

	@Test//A Carrier can have many destinations.
	public void shouldEstablishCarrierToDestinationRelationships() {

		Destination nairobi = destinationRepo.save(new Destination("Nairobi"));
		Destination tokyo = destinationRepo.save(new Destination("Tokyo"));

		Carrier kq = new Carrier("Kenya Airways", "Flight #300", nairobi, tokyo);
		kq = carrierRepo.save(kq);
		long kqId = kq.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Carrier> result = carrierRepo.findById(kqId);
		kq = result.get();
		assertThat(kq.getDestinations(), containsInAnyOrder(nairobi, tokyo));

	}

	@Test
	public void shouldBeAbleToFindCarrierForDestination() {

		Destination london = destinationRepo.save(new Destination("London"));

		Carrier ba = carrierRepo.save(new Carrier("British Airways", "Flight #95", london));
		Carrier qatar = carrierRepo.save(new Carrier("Qatar Airways", "Flight #318", london));

		entityManager.flush();
		entityManager.clear();

		Collection<Carrier> carrierForDestination = carrierRepo.findByDestinationsContains(london);

		assertThat(carrierForDestination, containsInAnyOrder(ba, qatar));
	}

	@Test
	public void shouldBeAbleToFindCarriersForDestinationId() {

		Destination london = destinationRepo.save(new Destination("London"));
		long londonId = london.getId();

		Carrier ba = carrierRepo.save(new Carrier("British Airways", "Flight #95", london));
		Carrier qatar = carrierRepo.save(new Carrier("Qatar Airways", "Flight #318", london));

		entityManager.flush();
		entityManager.clear();

		Collection<Carrier> carrierForDestination = carrierRepo.findByDestinationsId(londonId);

		assertThat(carrierForDestination, containsInAnyOrder(ba, qatar));
	}


	@Test
	public void shouldSaveAndLoadAirlineForm() {

		Carrier delta = carrierRepo.save(new Carrier("Delta", "Boeing 747 Flight #122"));

		AirlineForm domestic = airlineFormRepo.save(new AirlineForm("domestic", delta));
		long airlineFormId = domestic.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<AirlineForm> result = airlineFormRepo.findById(airlineFormId);
		domestic = result.get();

		assertThat(domestic.getName(), is("domestic"));

	}

	@Test
	public void shouldGenerateAirlineFormId() {

		Carrier swest = carrierRepo.save(new Carrier("Delta", "Boeing 747 Flight #122"));

		AirlineForm domestic = airlineFormRepo.save(new AirlineForm("domestic", swest));
		long airlineFormId = domestic.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(airlineFormId, is(greaterThan(0L)));
	}

	@Test // A Domestic/International airline can have many Carriers.
	public void shouldEstablishAirlineFormCarrierRelationships() {

		Carrier delta = carrierRepo.save(new Carrier("Delta", "Flight #95"));
		Carrier sw = carrierRepo.save(new Carrier("South West", "Flight #455"));

		AirlineForm domestic = new AirlineForm("Domestic", delta,sw);
		airlineFormRepo.save(domestic);
		long domesticId = domestic.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<AirlineForm> result = airlineFormRepo.findById(domesticId);
		domestic = result.get();

		assertThat(domestic.getCarriers(), containsInAnyOrder(delta, sw));

	}
}