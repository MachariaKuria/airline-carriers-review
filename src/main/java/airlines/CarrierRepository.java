package airlines;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CarrierRepository extends CrudRepository<Carrier, Long> {

	Collection<Carrier> findByDestinationsContains(Destination destination);

	Collection<Carrier> findByDestinationsId(long id);


}
