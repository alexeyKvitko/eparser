package tech.madest.eparser.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import tech.madest.eparser.dto.CompanyDto;
import tech.madest.eparser.entity.CompanyEntity;
import tech.madest.eparser.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl {

    @Autowired
    CompanyRepository companyRepo;

    public List< CompanyDto > getAllCompanies() {
        List< CompanyEntity > companyEntities = ( List< CompanyEntity > ) companyRepo.findAll();
        ModelMapper mapper = new ModelMapper();
        List< CompanyDto > companies = companyEntities.stream().map( entity -> mapper.map( entity, CompanyDto.class ) ).collect( Collectors.toList() );
        return companies;
    }

}
