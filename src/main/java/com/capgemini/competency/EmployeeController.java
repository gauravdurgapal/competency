package com.capgemini.competency;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.competency.model.EmployeeDetails;
import com.capgemini.competency.model.Response;
import com.capgemini.competency.service.EmployeeService;
import ch.qos.logback.classic.Logger;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	private Logger logger= (Logger) LoggerFactory.getLogger(EmployeeController.class);
	
	
	//create post mapping that create the single employee detail in the database
	@PostMapping("/addEmployee")
	public Response addDetail(@RequestBody EmployeeDetails empDetail) {
		Response response= new Response();
		logger.info("Received Create Request for single Employee");
		response=empService.save(empDetail);
		logger.info("Create Request Executed for single Employee");
		return response;
	}
	
	//create post mapping that create the multiple employee details in the database 
	@PostMapping("/addmultipleEmployee")
	public ResponseEntity<EmployeeDetails> addMultiple(@RequestBody List<EmployeeDetails> empAll) {
		logger.info("Received Create Request for multiple Employees");
		empService.saveAll(empAll);
		logger.info("Create Request Executed for multiple Employees");
		return new ResponseEntity<EmployeeDetails>(HttpStatus.OK);
	}
	
	//create a delete mapping that deletes a specified employee 
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		logger.info("Received Delete Request");
		empService.delete(id);
		logger.info("Delete Request executed");
	}
	
	//create a get mapping that retrieves all the employee detail from the database
	@GetMapping("/getAllEmployeeDetails")
	public List<EmployeeDetails> getEmployeeDetails(){
		logger.info("Received retreive Request for all employees");
		return empService.getEmployeeDetails();
	}
	
	//create a get mapping that retrieves the detail of a specific employee
	@GetMapping("/getEmployeeDetail/{globalId}")
   // public EmployeeDetails findEmployeebyId(@PathVariable("globalId") int globalId)
	public EmployeeDetails findEmployeebyId(@PathVariable int globalId)
    {
		logger.info("Received retreive Request for specific employee");
        return empService.findById(globalId);  
    }

	//create a update mapping that update the detail of a specific employee
	@PutMapping("/updateEmployee")
	 public void updateEmployeeDetails(@RequestBody EmployeeDetails empDetail)
	    {
			logger.info("Received update Request for specific employee");
	        empService.update(empDetail);  
	    }
}
