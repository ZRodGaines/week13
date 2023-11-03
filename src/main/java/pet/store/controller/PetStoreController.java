package pet.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
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
	public PetStoreData insertPetStore(
			@RequestBody PetStoreData petStoreData) {
		log.info("Creating PetStore { }", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}
	
	@PutMapping("/petstore/{petstoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId,
			@RequestBody PetStoreData petStoreData) {
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
}
