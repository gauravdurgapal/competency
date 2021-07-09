package com.capgemini.competency;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.competency.model.EmployeeDetails;
import com.capgemini.competency.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@PostMapping("/insert")
	public void addDetail(@RequestBody EmployeeDetails empDetail) {
		empService.save(empDetail);

	}
	
	@PostMapping("/addmultiple")
	public ResponseEntity<EmployeeDetails> addMultiple(@RequestBody List<EmployeeDetails> empAll) {
		
		empService.saveAll(empAll);
		return new ResponseEntity<EmployeeDetails>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam int Id) {
		empService.delete(Id);
	}
	
	@GetMapping("/getMappings")
	public List<EmployeeDetails> getEmployeeDetails(){
		return empService.getEmployeeDetails();
	}
	
	@GetMapping("/{globalId}")
    public EmployeeDetails findEmployeebyId(@PathVariable("globalId") int globalId)
    {
       
        return empService.findById(globalId);
       
    }

}
