package tech.madest.eparser.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import tech.madest.eparser.dto.CompanyDto;
import tech.madest.eparser.dto.CompanyPageDto;
import tech.madest.eparser.entity.CompanyEntity;
import tech.madest.eparser.entity.CompanyPageEntity;
import tech.madest.eparser.repository.CompanyPageRepository;
import tech.madest.eparser.repository.CompanyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl {

    @Autowired
    CompanyRepository companyRepo;

    @Autowired
    CompanyPageRepository companyPageRepo;

    public List< CompanyDto > getAllCompanies() {
        List< CompanyEntity > companyEntities = ( List< CompanyEntity > ) companyRepo.findAll();
        ModelMapper mapper = new ModelMapper();
        List< CompanyDto > companies = companyEntities.stream().map( entity -> mapper.map( entity, CompanyDto.class ) ).collect( Collectors.toList() );
        return companies;
    }

    public void upsertCompany( CompanyDto companyDto){
        CompanyEntity companyEntity = null;
        if ( companyDto.getId() != null ){
            companyEntity = companyRepo.findById( companyDto.getId() ).get();
            companyEntity.setCompanyName( companyDto.getCompanyName() );
            companyEntity.setUrl( companyDto.getUrl() );
            companyEntity.setThumb( companyDto.getThumb() );
            companyEntity.setCountry( companyDto.getCountry() );
            companyEntity.setLanguage( companyDto.getLanguage() );
        } else {
            companyEntity = new ModelMapper().map( companyDto, CompanyEntity.class );
        }
        companyRepo.save( companyEntity );
    }

    public void deleteCompany( Integer companyId ){
        companyRepo.deleteById( companyId );
    }

    @Modifying
    @Transactional
    public void upsertCompanyPage( CompanyPageDto companyPageDto){
        CompanyPageEntity companyPageEntity = null;
        if ( companyPageDto.getId() != null ){
            companyPageEntity = companyPageRepo.findById( companyPageDto.getId() ).get();
            companyPageEntity.setPageName( companyPageDto.getPageName() );
            companyPageEntity.setParseUrl( companyPageDto.getParseUrl() );
            companyPageEntity.setPageAttr( companyPageDto.getPageAttr() );
            companyPageEntity.setPageCount( companyPageDto.getPageCount() );
            companyPageEntity.setTagLessInfo( companyPageDto.getTagLessInfo() );
            companyPageEntity.setTagSectionStart( companyPageDto.getTagSectionStart() );
        } else {
            companyPageEntity = new ModelMapper().map( companyPageDto, CompanyPageEntity.class );
        }
        companyPageRepo.save( companyPageEntity );
    }

    @Modifying
    @Transactional
    public void deleteCompanyPage( Integer companyPageId ){
        companyPageRepo.deleteById( companyPageId );
    }

}
