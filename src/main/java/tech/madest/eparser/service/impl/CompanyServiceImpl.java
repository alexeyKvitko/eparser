package tech.madest.eparser.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import tech.madest.eparser.dto.CompanyDto;
import tech.madest.eparser.dto.ParsingPageDto;
import tech.madest.eparser.entity.CompanyEntity;
import tech.madest.eparser.entity.ParsingPageEntity;
import tech.madest.eparser.manager.GlobalManager;
import tech.madest.eparser.model.PageType;
import tech.madest.eparser.repository.CategoryRepository;
import tech.madest.eparser.repository.ParsingPageRepository;
import tech.madest.eparser.repository.CompanyRepository;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl {

    @Autowired
    CompanyRepository companyRepo;

    @Autowired
    ParsingPageServiceImpl parsingPageService;

    @Autowired
    PageTagServiceImpl pageTagService;

    @Autowired
    CategoryRepository categoryRepo;

    public List< CompanyDto > getAllCompanies() {
        CompanyEntity companyEntity =  companyRepo.findById( GlobalManager.COMPANY_ID ).get();
        ModelMapper mapper = new ModelMapper();
        CompanyDto  company =  mapper.map( companyEntity, CompanyDto.class );
        company.setCompanyPages( parsingPageService.getParsingPagesByLinkIdAndPageType( GlobalManager.COMPANY_ID, PageType.PAGE_MANUFACTURER ) );
        List<CompanyDto> companies = new LinkedList<>();
        companies.add( company );
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



}
