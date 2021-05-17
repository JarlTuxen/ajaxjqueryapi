package dk.kea.ajaxjqueryapi.repository;

import dk.kea.ajaxjqueryapi.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRespository extends CrudRepository<Car, Long> {
}
