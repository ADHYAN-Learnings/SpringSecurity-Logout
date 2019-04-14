package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.model.AddUserModel;
import com.spring.service.InterfSaveService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

@Autowired	
private InterfSaveService interfSaveService;

	@GetMapping("/")
	public String userTable(Model model) {
		List<AddUserModel> displayUserModel = interfSaveService.getUserDetails();
		model.addAttribute("displayUserModel", displayUserModel);
		return "SpringSecurityTable";
	}
	
	@GetMapping("/addUser")
	public String createNewUser(Model model) {
		model.addAttribute("addUserModel", new AddUserModel());
		return "addUser";
	}
	
	@PostMapping("/save")
	public String saveNewUser(@Valid AddUserModel addUserModel ,BindingResult bindingResult , Model model) {
		interfSaveService.saveModel(addUserModel);
		List<AddUserModel> displayUserModel = interfSaveService.getUserDetails();
		
		model.addAttribute("displayUserModel",displayUserModel );
		
		return "SpringSecurityTable";
	}
	
   @GetMapping("/delete")
   public String deleteUser(@RequestParam("id") Long id,Model model) {
	   
	   interfSaveService.deleteUser(id);
	    List<AddUserModel> displayUserModel = interfSaveService.getUserDetails();
		model.addAttribute("displayUserModel",displayUserModel );
	   return "SpringSecurityTable";
   }
   
   @GetMapping("/edit")
   public String editUser(@RequestParam("id") Long id,Model model) {
	   List<AddUserModel> displayUserModel = interfSaveService.getUserDetails();
	    Long editId = id;
		model.addAttribute("displayUserModel",displayUserModel );
		model.addAttribute("editId",editId);
		 return "SpringSecurityTable";
   }
   
   @GetMapping("/saveModifiedData")
   public String updateData(@RequestParam("id") Long id,@Valid AddUserModel addUserModel ,BindingResult bindingResult , Model model) {
 
	   interfSaveService.saveModel(addUserModel);
	   if(bindingResult.hasErrors()) {
		   return "SpringSecurityTable";
	   }
	   List<AddUserModel> displayUserModel = interfSaveService.getUserDetails();
	   model.addAttribute("displayUserModel",displayUserModel );
	   return "SpringSecurityTable";
   }
   
   @GetMapping("/login")
   public String loginPage() {
	   return "loginPage";
   }

}
