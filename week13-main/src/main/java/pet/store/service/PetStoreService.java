package pet.store.service;

import java.util.LinkedList;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.CustomerData;
import pet.store.controller.model.EmployeeData;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		setFieldsInPetStore(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	}

	private void setFieldsInPetStore(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore petStore;
		
		if(Objects.isNull(petStoreId)) {
			petStore = new PetStore();
		}
		else {
			petStore = findPetStoreById(petStoreId);
		}
		return petStore;
	}

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("PetStore with ID=" + petStoreId + " was not found."));
	}
	
	@Transactional(readOnly = true)
	public List<PetStoreData> retrieveAllPetStores() {
		List<PetStore> petStores = petStoreDao.findAll();
		List<PetStoreData> response = new LinkedList<>();
		
		for(PetStore petStore : petStores) {
			response.add(new PetStoreData(petStore));
		}
		
		
		return response;
	}

	public PetStoreData retrievePetStoreById(Long petStoreId) {
		PetStore petStore = findPetStoreById(petStoreId);
		return new PetStoreData(petStore);
	}

	@Transactional(readOnly = false)
	public void deletePetstoreById(Long petStoreId) {
		PetStore petstore = findPetStoreById(petStoreId);
		petStoreDao.delete(petstore);
		
	}
	
	@Transactional(readOnly = false)
	public EmployeeData saveEmployee(Long petStoreId, EmployeeData employeeData) {
		PetStore petStore = findPetStoreById(petStoreId);
		
		Employee employee = findOrCreateEmployee(petStoreId ,employeeData.getEmployeeId());
			setEmployeeFields(employee, employeeData);
			
			employee.setPetStore(petStore);
			petStore.getEmployees().add(employee);
			
			Employee dbEmployee = employeeDao.save(employee);
			return new EmployeeData(dbEmployee);
	}

	private void setEmployeeFields(Employee employee, EmployeeData employeeData) {
		employee.setEmployeeFirstName(employeeData.getEmployeeFirstName());
		employee.setEmployeeLastName(employeeData.getEmployeeLastName());
		employee.setEmployeeJobTitle(employeeData.getEmployeeJobTitle());
		employee.setEmployeePhone(employeeData.getEmployeePhone());
		employee.setEmployeeId(employee.getEmployeeId());

		
	}

	private Employee findOrCreateEmployee(Long petStoreId,Long employeeId) {
		Employee employee;
		
		if(Objects.isNull(employeeId)) {
			employee = new Employee();
		}
		else {
			employee = findEmployeeById(petStoreId, employeeId);
		}
		return employee;
	}

	private Employee findEmployeeById(Long petStoreId, Long employeeId) {
		return employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee with ID= " +employeeId+ " Does not Exist"));
	}
	
	@Transactional(readOnly = false)
	public CustomerData saveCustomer(Long petStoreId, CustomerData customerData) {
		PetStore petStore = findPetStoreById(petStoreId);
		
		Customer customer = findOrCreateCustomer(petStoreId ,customerData.getCustomerId());
			setCustomerields(customer, customerData);
			
			//Set<PetStore> petStores = petStoreDao.findAllByPetStoreIn(customerData.getPetStores());
			
			customer.getPetStores().add(petStore);
			petStore.getCustomers().add(customer);
			
			Customer dbCustomer = customerDao.save(customer);
			return new CustomerData(dbCustomer);
	}

	private void setCustomerields(Customer customer, CustomerData customerData) {
		customer.setCustomerFirstName(customerData.getCustomerFirstName());
		customer.setCustomerLastName(customerData.getCustomerLastName());
		customer.setCustomerEmail(customerData.getCustomerEmail());
		customer.setCustomerId(customerData.getCustomerId());
		
	}

	private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
		Customer customer;
		
		if(Objects.isNull(customerId)) {
			customer = new Customer();
		}
		else {
			customer = findCustomerById(petStoreId, customerId);
		}
		return customer;
	}

	private Customer findCustomerById(Long petStoreId, Long customerId) {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new NoSuchElementException("Customer with ID= " + customerId + " Does not Exist"));
		
		boolean found = false;
		
		for(PetStore petStore : customer.getPetStores()) {
			if(petStore.getPetStoreId() == petStoreId) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			throw new IllegalArgumentException("The customer with ID = " + customerId  + " does not shop at the pet store with ID=" + petStoreId);
		}
		return customer;
}

}
