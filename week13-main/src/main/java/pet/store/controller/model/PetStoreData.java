package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {
		private Long petStoreId;
		private String petStoreName;
		private String petStoreAddress;
		private String petStoreCity;
		private String petStoreState;
		private Integer petStoreZip;
		private Integer petStorePhone;

		private Set<CustomerData> customers = new HashSet<>();
		private Set<EmployeeData> employees = new HashSet<>();
		
		public PetStoreData(PetStore petstore) {
			petStoreId = petstore.getPetStoreId();
			petStoreName = petstore.getPetStoreName();
			petStoreAddress = petstore.getPetStoreAddress();
			petStoreCity = petstore.getPetStoreCity();
			petStoreState = petstore.getPetStoreState();
			petStoreZip = petstore.getPetStoreZip();
			petStorePhone = petstore.getPetStorePhone();
			
			for(Customer customer : petstore.getCustomers()) {
				customers.add(new CustomerData(customer));
			}
			for(Employee employee : petstore.getEmployees()) {
				employees.add(new EmployeeData(employee));
			}
		}
		
		
}
