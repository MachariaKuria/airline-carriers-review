package airlines;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarrierPopulator implements CommandLineRunner {

	
	@Resource
	private AirlineFormRepository airlineFormRepo;
	
	@Resource
	private CarrierRepository carrierRepo;
	
	@Resource
	private DestinationRepository destinationRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Destination nairobi = destinationRepo.save(new Destination("Nairobi, Kenya"));
		Destination tokyo = destinationRepo.save(new Destination("Tokyo, Japan"));
		Destination london = destinationRepo.save(new Destination("London, England"));
		Destination beijing = destinationRepo.save(new Destination("Beijing, China"));
		Destination columbus = destinationRepo.save(new Destination("Columbus, Ohio"));
		Destination vegas = destinationRepo.save(new Destination("Las Vegas, Nevada"));
		Destination charlotte = destinationRepo.save(new Destination("Charlotte, North Carolina"));

		Carrier kq = carrierRepo.save(new Carrier("Kenya Airways", "Flight #300", nairobi, tokyo, beijing));
		Carrier ba = carrierRepo.save(new Carrier("British Airways", "Flight #300", london, nairobi, beijing));
		Carrier sw = carrierRepo.save(new Carrier("South West Airlines", "Flight #450", columbus, vegas));
		Carrier delta = carrierRepo.save(new Carrier("Delta Airlines", "Flight #122", charlotte, vegas));

		airlineFormRepo.save(new AirlineForm("Domestic", delta, sw));
		airlineFormRepo.save(new AirlineForm("International", delta, sw, ba, kq));
	}
	
	

}

