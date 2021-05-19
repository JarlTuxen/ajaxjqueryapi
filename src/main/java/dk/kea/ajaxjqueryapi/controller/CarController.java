package dk.kea.ajaxjqueryapi.controller;

import dk.kea.ajaxjqueryapi.model.Car;
import dk.kea.ajaxjqueryapi.repository.CarRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    private CarRespository carRespository;

    public CarController(CarRespository carRespository){
        this.carRespository = carRespository;
    }

    // HTTP Get List
    @GetMapping("/car")
    public ResponseEntity<List<Car>> findAll(){
        //findAll recipes and return
        List<Car> carList =new ArrayList<>();
        for (Car car:carRespository.findAll()){
            carList.add(car);
        }
        return ResponseEntity.status(HttpStatus.OK).body(carList);
    }

    // HTTP Get by ID
    @GetMapping("/car/{id}")
    public ResponseEntity<Optional<Car>> findById(@PathVariable Long id)
    {
        Optional<Car> optionalCar = carRespository.findById(id);
        if (optionalCar.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalCar);
        }
        else{
            //Not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalCar);
        }
    }

    // HTTP Post, ie. create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/car", consumes = "application/json")
    public ResponseEntity<Car> create(@RequestBody Car car) {
        Car newCar = carRespository.save(car);
        return ResponseEntity.ok(newCar);
    }

    //HTTP PUT, ie. update
    @PutMapping("/car/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Car car) {
        Optional<Car> optionalCar = carRespository.findById(id);
        if (!optionalCar.isPresent()) {
            //id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'car " + id + " not found'}");
        }
        carRespository.save(car);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'msg' : 'updated' }");

    }

    //HTTP DELETE
    @DeleteMapping("/car/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Car> optionalCar = carRespository.findById(id);
        if (!optionalCar.isPresent()) {
            //id findes ikke
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'car " + id + " not found'}");
        }
        carRespository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{ 'msg' : 'deleted' }");
    }

}
