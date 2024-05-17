package com.saifmit.firstjobapp.company.imp;

import com.saifmit.firstjobapp.company.Company;
import com.saifmit.firstjobapp.company.CompanyRepository;
import com.saifmit.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company1 = companyOptional.get();
            company1.setdescription(company.getdescription());
            company1.setname(company.getname());
            company1.setJobs(company.getJobs());
            companyRepository.save(company1);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


}
