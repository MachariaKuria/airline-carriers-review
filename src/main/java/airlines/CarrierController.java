package airlines;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarrierController {

	@Resource
	CarrierRepository carrierRepo;
	
	@Resource
	DestinationRepository destinationRepo;
	
	@Resource
	AirlineFormRepository airlineFormRepo;
	
	@RequestMapping("/carrier")
	public String findOneCarrier(@RequestParam(value="id")long id, Model model) throws CarrierNotFoundException {
		
		Optional<Carrier> carrier = carrierRepo.findById(id);
		
		if(carrier.isPresent()) {
			model.addAttribute("carriers",carrier.get());
			return "carrier";
		}
		throw new CarrierNotFoundException();
	}

	@RequestMapping("/show-carriers")
	public String findAllCarriers(Model model) {
		
		model.addAttribute("carriers", carrierRepo.findAll());
		return ("carriers");
		
	}

	@RequestMapping("/destination")
	public String findOneDestination(@RequestParam(value="id")long id, Model model) throws DestinationNotFoundException {
		
		Optional<Destination> nairobi = destinationRepo.findById(id);
		if(nairobi.isPresent()) {
			model.addAttribute("destinations",nairobi.get());
			model.addAttribute("carriers", carrierRepo.findByDestinationsContains(nairobi.get()));
			return "destination";
		}		
		throw new DestinationNotFoundException();
		
	}

	@RequestMapping("/show-destinations")
	public String findAllDestinations(Model model) {
		model.addAttribute("destinations", destinationRepo.findAll());
		return ("destinations");		
		
	}


	@RequestMapping("/show-airlineForms")
	public String findAllAirlineForms(Model model) {
		model.addAttribute("airlineForms", airlineFormRepo.findAll());
		return ("airlineForms");		
	}

}
