package com.project.Myproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.Myproject.model.Employee;
import com.project.Myproject.repository.EmployeeRepository;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Home Page
    @GetMapping("/")
    public String homePage(Model model) {
        return "index";  // Ensure index.html exists
    }

    // Create Page
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "create";  // Ensure create.html exists
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);  // Save employee to database
        return "redirect:/employees/view";  // Redirect to the view page after saving
    }

    // View Employees Page
    @GetMapping("/view")
    public String viewEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());  // Fetch all employees
        return "view";  // Ensure view.html exists to display the list of employees
    }

    // Update Employee Page
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "update";  // Ensure update.html exists
        } else {
            return "redirect:/employees/view";  // Redirect if employee not found
        }
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee) {
        employee.setId(id);  // Set the existing employee ID to update
        employeeRepository.save(employee);  // Save updated employee details
        return "redirect:/employees/view";  // Redirect to view page after update
    }

    // Delete Employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);  // Delete employee by ID
        return "redirect:/employees/view";  // Redirect to view page after deletion
    }
}
