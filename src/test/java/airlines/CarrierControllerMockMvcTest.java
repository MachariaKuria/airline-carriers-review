package airlines;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CarrierController.class)
public class CarrierControllerMockMvcTest {

	@Resource
	private MockMvc mvc;

	@MockBean
	private CarrierRepository carrierRepo;

	@MockBean
	private DestinationRepository destinationRepo;

	@MockBean
	private AirlineFormRepository airlineFormRepo;

	@Mock
	private Destination destination;
	
	@Mock
	private Carrier anotherDestination;	


	@Mock
	private Carrier carrier;
	
	@Mock
	private Carrier anotherCarrier;	
	@Test
	public void shouldRouteToSingleCarrierView() throws Exception {

		long airlineId = 1;
		when(carrierRepo.findById(airlineId)).thenReturn(Optional.of(carrier));
		mvc.perform(get("/carrier?id=1")).andExpect(view().name(is("carrier")));

	}

	@Test
	public void shouldBeOkForSingleCarrier() throws Exception {

		long airlineId = 1;
		when(carrierRepo.findById(airlineId)).thenReturn(Optional.of(carrier));
		mvc.perform(get("/carrier?id=1")).andExpect(status().isOk());

	}

	@Test
	public void shouldNotBeOkForSingleCarrier() throws Exception {
		
		mvc.perform(get("/carrier?id=4")).andExpect(status().isNotFound());
		
	}
	
	@Test
	public void shouldPutSingleCarrierToModel() throws Exception {
		
		when(carrierRepo.findById(2L)).thenReturn(Optional.of(carrier));
		mvc.perform(get("/carrier?id=2")).andExpect(status().isOk());				
	}
	
	@Test
	public void shouldRouteToAllCarriersView() throws Exception {
		
		mvc.perform(get("/show-carriers")).andExpect(view().name(is("carriers")));
	}
	
	@Test
	public void shouldBeOkForAllCarriers() throws Exception {

		mvc.perform(get("/show-carriers")).andExpect(status().isOk());

	}
	@Test
	public void shouldPutAllCoursesIntoModel() throws Exception {
		
		Collection<Carrier> allCarriers = Arrays.asList(carrier, anotherCarrier);
		when(carrierRepo.findAll()).thenReturn(allCarriers);
		
		mvc.perform(get("/show-carriers")).andExpect(model().attribute("carriers", allCarriers));
	}
	
	@Test
	public void shouldRouteToSingleDestinationView() throws Exception {

		long destinationId = 1;
		when(destinationRepo.findById(destinationId)).thenReturn(Optional.of(destination));
		mvc.perform(get("/destination?id=1")).andExpect(view().name(is("destination")));

	}

}
