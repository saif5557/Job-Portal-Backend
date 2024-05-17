package com.saifmit.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company Created Successfully",HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable Long id){
        boolean updated = companyService.updateCompany(company,id);
        if(updated){
            return new ResponseEntity<>("Company Updated Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not Updated Successfully",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByid(@PathVariable Long id){
        boolean deleted = companyService.deleteCompanyById(id);
        if(deleted){
            return new ResponseEntity<>("Company deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not deleted Something is wrong",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> CompanyGetById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
