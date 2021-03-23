package tech.madest.eparser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.madest.eparser.AppConstants;
import tech.madest.eparser.entity.PageProductEntity;
import tech.madest.eparser.entity.shopizer.category.Category;
import tech.madest.eparser.entity.shopizer.merchant.MerchantStore;
import tech.madest.eparser.entity.shopizer.product.Product;
import tech.madest.eparser.entity.shopizer.product.availability.ProductAvailability;
import tech.madest.eparser.entity.shopizer.product.description.ProductDescription;
import tech.madest.eparser.entity.shopizer.product.image.ProductImage;
import tech.madest.eparser.entity.shopizer.product.image.ProductImageDescription;
import tech.madest.eparser.entity.shopizer.product.price.ProductPrice;
import tech.madest.eparser.entity.shopizer.product.price.ProductPriceType;
import tech.madest.eparser.entity.shopizer.reference.language.Language;
import tech.madest.eparser.model.Block;
import tech.madest.eparser.repository.*;
import tech.madest.eparser.utils.AppUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProductServiceImpl {

    @Autowired
    LanguageRepository languageRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductAvailRepository productAvailRepo;

    @Autowired
    ProductDescriptionRepository productDescriptionRepo;

    @Autowired
    ProductPriceRepository productPriceRepo;

    @Autowired
    ProductImageRepository productImageRepo;

    @Autowired
    PageProductRepository pageProductRepo;

    @Autowired
    MerchantStoreRepository merchantStoreRepo;

    @Autowired
    ProductImageDescriptionRepository productImageDescriptionRepo;

    public void addProducts( Long pageId, Long categoryId, List< Block > blocks ){
        List< PageProductEntity > links = new LinkedList<>();
        Category category = categoryRepo.findById( categoryId ).get();
        Language language = languageRepo.findById( 4 ).get();
        MerchantStore merchantStore = merchantStoreRepo.findById( 1 ).get();
        HashSet<Category> categories = new HashSet<>();
        categories.add( category );
        for( Block block: blocks ){
            if ( AppUtils.isNullOrEmpty( block.getParseItems() ) ){
                continue;
            }
            String productName = AppUtils.getFieldValueByName( AppConstants.PRODUCT_NAME, block.getParseItems() );
            String productMainDesc= AppUtils.getFieldValueByName( AppConstants.PRODUCT_MAIN_DESC, block.getParseItems() );
            String productDesc = AppUtils.getFieldValueByName( AppConstants.PRODUCT_DESCRIPTION, block.getParseItems() );
            String productMetaTag = AppUtils.getFieldValueByName( AppConstants.PRODUCT_META_TAG, block.getParseItems() );
            String price = AppUtils.getFieldValueByName( AppConstants.PRODUCT_PRICE, block.getParseItems() );
            String productQuantityAvail = AppUtils.getFieldValueByName( AppConstants.PRODUCT_QUANTITY_AVAIL, block.getParseItems() );
            String productMinOrder = AppUtils.getFieldValueByName( AppConstants.PRODUCT_MIN_ORDER, block.getParseItems() );
            String productMaxOrder = AppUtils.getFieldValueByName( AppConstants.PRODUCT_MAX_ORDER, block.getParseItems() );
            String productWeight = AppUtils.getFieldValueByName( AppConstants.PRODUCT_WEIGHT, block.getParseItems() );
            String productHeight = AppUtils.getFieldValueByName( AppConstants.PRODUCT_HEIGHT, block.getParseItems() );
            String productWidth = AppUtils.getFieldValueByName( AppConstants.PRODUCT_WIDTH, block.getParseItems() );
            String productLength = AppUtils.getFieldValueByName( AppConstants.PRODUCT_LENGTH, block.getParseItems() );
            String imageUrl = AppUtils.getFieldValueByName( AppConstants.PRODUCT_IMAGE, block.getParseItems() );
            String refSku = productName.toUpperCase()+"_"+pageId;
            Product exist = productRepo.findOneBySku( refSku );
            if ( exist == null ){
                Product product = new Product();
                product.setSku( refSku );
                product.setRefSku( refSku );
                product.setAvailable( true );
                product.setDateAvailable( new Date() );
                product.setProductHeight( AppUtils.toBigDecimal( productHeight )  );
                product.setProductLength( AppUtils.toBigDecimal( productLength ) );
                product.setProductWeight( AppUtils.toBigDecimal( productWeight ) );
                product.setProductWidth( AppUtils.toBigDecimal( productWidth ) );
                product.setCategories( categories );
                product.setMerchantStore( merchantStore );

                productRepo.save( product );

                ProductAvailability productAvailability = new ProductAvailability();
                productAvailability.setAvailable( true );
                productAvailability.setProduct( product );
                productAvailability.setProductQuantity( AppUtils.toInt(  productQuantityAvail ) );
                productAvailability.setProductQuantityOrderMax( AppUtils.toInt( productMaxOrder ) );
                productAvailability.setProductQuantityOrderMin( AppUtils.toInt( productMinOrder ) );
                productAvailability.setProductQuantityOrderMin( AppUtils.toInt( productMinOrder ) );
                productAvailRepo.save( productAvailability );

                ProductDescription productDescription = new ProductDescription();
                productDescription.setProduct( product );
                productDescription.setName( productName );
                productDescription.setDescription( productDesc );
                productDescription.setMetatagDescription( productMetaTag );
                productDescription.setProductHighlight( productDesc );
                productDescription.setSeUrl( productName.replace( " ","-" ) );
                productDescription.setLanguage( language );
                productDescriptionRepo.save( productDescription );

                ProductPrice productPrice = new ProductPrice();
                productPrice.setCode( "base" );
                productPrice.setDefaultPrice( true );
                productPrice.setProductPriceAmount( AppUtils.toBigDecimal( price ) );
                productPrice.setProductAvailability( productAvailability );
                productPrice.setProductPriceType( ProductPriceType.ONE_TIME );
                productPriceRepo.save( productPrice );

                ProductImage productImage = new ProductImage();
                productImage.setDefaultImage( true );
                productImage.setProduct( product );
                productImage.setProductImageUrl( imageUrl );
                URL urlInput = null;
                try {
                    urlInput = new URL( imageUrl );
                    BufferedImage urlImage = ImageIO.read(urlInput);
                    if ( urlImage != null ){
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        ImageIO.write(urlImage, "jpg", os);
                        InputStream is = new ByteArrayInputStream(os.toByteArray());
                        productImage.setImage( is );

                    }

                } catch ( Exception e ) {
                    e.printStackTrace();
                }
                productImageRepo.save( productImage );

                ProductImageDescription pid =  new ProductImageDescription();
                pid.setProductImage( productImage );
                pid.setDescription( "Description image for product "+productName );
                pid.setName( categoryId.toString()+"_"+pageId.toString()+"_"+product.getId().toString()+".jpg" );
                pid.setLanguage( language );
                productImageDescriptionRepo.save( pid );
                links.add( new PageProductEntity( pageId, product.getId()) );
            }
        }
        if ( !AppUtils.isNullOrEmpty( links ) ){
            pageProductRepo.saveAll( links );
        }
    }
}
