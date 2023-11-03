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

		Set<PetStoreCustomer> customers = new HashSet<>();
		Set<PetStoreEmployee> employees = new HashSet<>();
		
		public PetStoreData(PetStore petstore) {
			petStoreId = petstore.getPetStoreId();
			petStoreName = petstore.getPetStoreName();
			petStoreAddress = petstore.getPetStoreAddress();
			petStoreCity = petstore.getPetStoreCity();
			petStoreState = petstore.getPetStoreState();
			petStoreZip = petstore.getPetStoreZip();
			petStorePhone = petstore.getPetStorePhone();
			
			for(Customer customer : petstore.getCustomers()) {
				customers.add(new PetStoreCustomer(customer));
			}
			for(Employee employee : petstore.getEmployees()) {
				employees.add(new PetStoreEmployee(employee));
			}
		}
		
		@Data
		@NoArgsConstructor
		static class PetStoreCustomer{
			private Long customerId;
			private String customerFirstName;
			private String customerLastName;
			private String customerEmail;
			
			PetStoreCustomer(Customer customer) {
				customerId = customer.getCustomerId();
				customerFirstName = customer.getCustomerFirstName();
				customerLastName = customer.getCustomerLastName();
				customerEmail = customer.getCustomerEmail();
			}
		}
		@Data
		@NoArgsConstructor
		static class PetStoreEmployee{
			private Long employeeId;
			private String employeeFirstName;
			private String employeeLastName;
			private Integer employeePhone;
			private String employeeJobTitle;
			
			PetStoreEmployee(Employee employee){
				employeeId = employee.getEmployeeId();
				employeeFirstName = employee.getEmployeeFirstName();
				employeeLastName = employee.getEmployeeLastName();
				employeePhone = employee.getEmployeePhone();
				employeeJobTitle = employee.getEmployeeJobTitle();
			}
		}
		
}
