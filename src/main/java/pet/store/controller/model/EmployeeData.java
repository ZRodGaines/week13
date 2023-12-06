package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class EmployeeData {
	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private Integer employeePhone;
	private String employeeJobTitle;
	private PetStoreEmployee petStore;
		
		public EmployeeData(Employee employee) {
			employeeId = employee.getEmployeeId();
			employeeFirstName = employee.getEmployeeFirstName();
			employeeLastName = employee.getEmployeeLastName();
			employeePhone = employee.getEmployeePhone();
			employeeJobTitle = employee.getEmployeeJobTitle();
			
			petStore = new PetStoreEmployee(employee.getPetStore());
		}

		@Data
		@NoArgsConstructor
		public static class PetStoreEmployee {
			private Long petStoreId;
			private String petStoreName;
			private String petStoreEmail;
			private String petStoreAddress;
			private String petStoreCity;
			private String petStoreState;
			private int petStoreZip;
			private int petStorePhone;
			
			public PetStoreEmployee(PetStore petstore) {
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
