package com.capgemini.competency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.competency.model.EmployeeDetails;
import com.capgemini.competency.model.Response;
import com.capgemini.competency.model.User;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeInterface empInt;
	
	/*public List<EmployeeDetails> listAll(){
		return empInt.findAll();
	}
	*/
	
	public Response save(EmployeeDetails empDetails) {
		
		EmployeeDetails e2=empInt.findById(empDetails.getGlobalId());
		Response response =new Response();
		if(e2!=null)
		{
		    response.setMessage("Entry already exists in the database");
		    response.setStatus("409");  
		}
		else
		{
		 empInt.save(empDetails);
		 response.setMessage("Entry successfully added in the database");
		 response.setStatus("200"); 
		}
		 return response;
	}
	
	public void saveAll(List<EmployeeDetails> empList) {
		
		empInt.saveAll(empList);
	}
	
    public void delete(Integer Id) {
	     empInt.deleteById(Id);
	} 
    
    public List<EmployeeDetails> getEmployeeDetails(){
		return empInt.findAll();
	}

    public EmployeeDetails findById(int globalId) {

     return(empInt.findById(globalId));
    }

	public void update(EmployeeDetails empDetail) {
		EmployeeDetails e2=empInt.findById(empDetail.getGlobalId());
		e2.setEmpName(empDetail.getEmpName());
		e2.setGender(empDetail.getGender());
		e2.setJobTitle(empDetail.getJobTitle());
		e2.setEmpSubGroup(empDetail.getEmpSubGroup());
		e2.setTimesheetComplaince(empDetail.getTimesheetComplaince());
		e2.setSupervisorName(empDetail.getSupervisorName());
		e2.setClientTeam(empDetail.getClientTeam());
		e2.setEmailId(empDetail.getEmailId());
		e2.setBaseLocation(empDetail.getBaseLocation());
		e2.setPersonalArea(empDetail.getPersonalArea());
		e2.setLocation(empDetail.getLocation());
		e2.setLocalId(empDetail.getLocalId());
		empInt.save(e2);
	}
}
