package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.entity.Customer;
import pet.store.entity.PetStore;


@Data
@NoArgsConstructor
public class CustomerData {
	private Long customerId;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmail;
	private PetStoreCustomer petStore;
	private Set<PetStore> petStores = new HashSet<>();
	
	public CustomerData(Customer customer) {
		customerId = customer.getCustomerId();
		customerFirstName = customer.getCustomerFirstName();
		customerLastName = customer.getCustomerLastName();
		customerEmail = customer.getCustomerEmail();
		
		for(PetStore petstore: customer.getPetStores()) {
			petStores.add(petstore);
		}
		
	}
	
	@Data
	@NoArgsConstructor
	public static class PetStoreCustomer {
		private Long petStoreId;
		private String petStoreName;
		private String petStoreEmail;
		private String petStoreAddress;
		private String petStoreCity;
		private String petStoreState;
		private int petStoreZip;
		private int petStorePhone;
		
		
		public PetStoreCustomer(PetStore petstore) {
			petStoreId = petstore.getPetStoreId();
			petStoreName = petstore.getPetStoreName();
			petStoreAddress = petstore.getPetStoreAddress();
			petStoreCity = petstore.getPetStoreCity();
			petStoreState = petstore.getPetStoreState();
			petStoreZip = petstore.getPetStoreZip();
			petStorePhone = petstore.getPetStorePhone();
		}
	}
}
