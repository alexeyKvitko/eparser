package tech.madest.eparser.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import tech.madest.eparser.dto.ParsingPageDto;
import tech.madest.eparser.entity.ParsingPageEntity;
import tech.madest.eparser.model.PageType;
import tech.madest.eparser.repository.ParsingPageRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParsingPageServiceImpl {

    @Autowired
    ParsingPageRepository parsingPageRepo;

    @Autowired
    PageTagServiceImpl pageTagService;

    public List< ParsingPageDto > getParsingPagesByLinkIdAndPageType( Integer linkId, PageType pageType ){
        List< ParsingPageEntity > parsingPageEntities = parsingPageRepo.findAllByLinkIdAndPageType( linkId, pageType.getValue() );
        ModelMapper mapper = new ModelMapper();
        return parsingPageEntities.stream().map( entity -> mapper.map( entity, ParsingPageDto.class ) ).collect( Collectors.toList() );
    }

    @Modifying
    @Transactional
    public void upsertCompanyPage( ParsingPageDto parsingPageDto ){
        ParsingPageEntity parsingPageEntity = null;
        boolean isNewEntity = true;
        if ( parsingPageDto.getId() != null ){
            parsingPageEntity = parsingPageRepo.findById( parsingPageDto.getId() ).get();
            parsingPageEntity.setPageName( parsingPageDto.getPageName() );
            parsingPageEntity.setParseUrl( parsingPageDto.getParseUrl() );
            parsingPageEntity.setPrefixUrl( parsingPageDto.getPrefixUrl() );
            parsingPageEntity.setPageAttr( parsingPageDto.getPageAttr() );
            parsingPageEntity.setPageCount( parsingPageDto.getPageCount() );
            parsingPageEntity.setTagLessInfo( parsingPageDto.getTagLessInfo() );
            parsingPageEntity.setTagSectionStart( parsingPageDto.getTagSectionStart() );
            isNewEntity = false;
        } else {
            parsingPageEntity = new ModelMapper().map( parsingPageDto, ParsingPageEntity.class );
        }
        parsingPageRepo.save( parsingPageEntity );
        if ( isNewEntity ){
            pageTagService.addPageTags( PageType.fromValue( parsingPageDto.getPageType() ), parsingPageEntity.getId() );
        }
    }

    @Modifying
    @Transactional
    public void deleteCompanyPage( Integer companyPageId ){
        parsingPageRepo.deleteById( companyPageId );
    }
}
