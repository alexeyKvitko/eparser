package tech.madest.eparser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.madest.eparser.AppConstants;
import tech.madest.eparser.dto.CategoryDto;
import tech.madest.eparser.entity.shopizer.category.Category;
import tech.madest.eparser.model.EPBootstrap;
import tech.madest.eparser.repository.CategoryRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class BootstrapServiceImpl {

    @Autowired
    CategoryRepository categoryRepository;

    public EPBootstrap getBootstrapModel() {
        EPBootstrap bootstrap = new EPBootstrap();
        List< Category > categories = categoryRepository.findByStoreAndLanguage();
        int maxDepth = categories.stream().mapToInt( cat -> cat.getDepth() ).max().getAsInt();
        for ( int d = 0; d <= maxDepth; d++ ) {
            for ( Category category : categories ) {
                if ( category.getDepth() == d ){
                    CategoryDto dto = new CategoryDto();
                    dto.setCategoryCode( category.getCode() );
                    dto.setCategoryId( category.getId().intValue() );
                    dto.setCategoryName( category.getDescription().getName() );
                    dto.setCategoryImage( category.getCategoryImage() );
                    dto.setDepth( category.getDepth() );
                    if ( d == 0 ){
                        dto.setParentId( AppConstants.FAKE_ID );
                        bootstrap.getCategories().add( dto );
                    } else {
                        dto.setParentId( category.getParent().getId().intValue() );
                        addDtoToTree( dto, bootstrap.getCategories() );
                    }
                }
            }
        }
        List<CategoryDto> dtoAsList = new LinkedList<>();
        for( CategoryDto dto: bootstrap.getCategories() ){
            addDtoToList( dto, dtoAsList );
        }
        bootstrap.getCategories().clear();
        bootstrap.setCategories( dtoAsList );
        return bootstrap;
    }

    private void addDtoToList( CategoryDto dto, List<CategoryDto> dtolist){
        dto.setNode( dto.getChilds().size() > 0 ? true : false );
        dtolist.add( dto );
        if ( dto.getChilds().size() > 0 ){
            for( CategoryDto child: dto.getChilds() ){
                addDtoToList( child, dtolist );
            }
        }
    }

    private boolean addDtoToTree( CategoryDto addDto, List<CategoryDto> dtos ){
        boolean isAdded =  false;
        for (CategoryDto existDto: dtos ){
            if( existDto.getCategoryId().equals( addDto.getParentId() ) ){
                existDto.getChilds().add( addDto );
                isAdded = true;
                break;
            }
        }
        if ( !isAdded ){
            for( CategoryDto existDto: dtos ){
                isAdded = addDtoToTree( addDto, existDto.getChilds() );
                if ( isAdded ){
                    break;
                }
            }
        }
        return isAdded;
    }
}
