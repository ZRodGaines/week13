package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.CustomerData;
import pet.store.controller.model.EmployeeData;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;

	@PostMapping("/petstore")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating PetStore { }", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}

	@PutMapping("/petstore/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating petstore {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}

	@GetMapping("/petstore")
	public List<PetStoreData> retrieveAllPetStores() {
		log.info("Retrieve all Petstores called.");
		return petStoreService.retrieveAllPetStores();
	}

	@GetMapping("/petstore/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieving the PetStore with the ID={}", petStoreId);
		return petStoreService.retrievePetStoreById(petStoreId);
	}

	@DeleteMapping("/petstore")
	public void deleteAllPetStores() {
		log.info("Attemping to delete all Pet Stores.");
		throw new UnsupportedOperationException("Deleting all pet stores is not allowed.");
	}

	@DeleteMapping("/petstore/{petStoreId}")
	public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting Pet Store with ID={}", petStoreId);

		petStoreService.deletePetstoreById(petStoreId);

		return Map.of("message", "Deletion of Pet Store with ID=" + petStoreId + " was" + " successful.");
	}
	@PostMapping("/petstore/{petStoreId}/employee")
	public EmployeeData InsertEmployee(@PathVariable Long petStoreId, @RequestBody EmployeeData employeeData) {
		
		log.info("Creating Employee for Pet Store with ID={}", petStoreId, employeeData);
		
		return petStoreService.saveEmployee(petStoreId, employeeData);
	}
	
	@PostMapping("/petstore/{petStoreId}/customer")
	public CustomerData InsertCustomer(@PathVariable Long petStoreId, @RequestBody CustomerData customerData) {
		
		log.info("Creating Customer for Pet Store with ID={}", petStoreId, customerData);
		
		return petStoreService.saveCustomer(petStoreId, customerData);
	}
}
