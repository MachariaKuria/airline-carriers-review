package airlines;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CarrierControllerTest {
	
	@InjectMocks
	private CarrierController underTest;
	
	@Mock
	private Carrier carrier;
	
	@Mock
	private Carrier anotherCarrier;
	
	@Mock
	private CarrierRepository carrierRepo;

	@Mock
	private Destination destination;
	
	@Mock
	private Destination anotherDestination;
	
	@Mock
	private DestinationRepository destinationRepo;
	
	@Mock
	private AirlineForm airlineForm;
	
	@Mock
	private AirlineForm anotherAirlineForm;
	
	@Mock
	private AirlineFormRepository airlineFormRepo;	
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleCarrierModel() throws CarrierNotFoundException {
		
		long anyId = 1;
		
		when(carrierRepo.findById(anyId)).thenReturn(Optional.of(carrier));
		
		underTest.findOneCarrier(anyId, model);
		
		verify(model).addAttribute("carriers", carrier);
	}
	
	@Test
	public void shouldAddAllCarriersToModel() {
		Collection<Carrier> allCarriers = Arrays.asList(carrier, anotherCarrier);
		
		when(carrierRepo.findAll()).thenReturn(allCarriers);
		
		underTest.findAllCarriers(model);
		
		verify(model).addAttribute("carriers", allCarriers);
	}

	@Test
	public void shouldAddSingleDestinationToModel() throws DestinationNotFoundException {
		
		long anyId = 1;
		
		when(destinationRepo.findById(anyId)).thenReturn(Optional.of(destination));
		
		underTest.findOneDestination(anyId, model);
		
		verify(model).addAttribute("destinations", destination);
	}
	
	@Test
	public void shouldAddAllDestinationsToModel() {
		Collection<Destination> allDestinations = Arrays.asList(destination, anotherDestination);
		
		when(destinationRepo.findAll()).thenReturn(allDestinations);
		
		underTest.findAllDestinations(model);
		
		verify(model).addAttribute("destinations", allDestinations);
	}
	
	
	
	@Test
	public void shouldAddSingleAirlineFormToModel() throws AirlineFormNotFoundException {
		
		long anyId = 1;
		
		when(airlineFormRepo.findById(anyId)).thenReturn(Optional.of(airlineForm));
		
		underTest.findOneAirlineForm(anyId, model);
		
		verify(model).addAttribute("airlineForms", airlineForm);
	}
	
	@Test
	public void shouldAddAllAirlineFormsToModel() {
		Collection<AirlineForm> allAirlineForms = Arrays.asList(airlineForm, anotherAirlineForm);
		
		when(airlineFormRepo.findAll()).thenReturn(allAirlineForms);
		
		underTest.findAllAirlineForms(model);
		
		verify(model).addAttribute("airlineForms", allAirlineForms);
	}
	
	
	
	
	
	
	
}
