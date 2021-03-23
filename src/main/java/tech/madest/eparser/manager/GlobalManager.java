package tech.madest.eparser.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.madest.eparser.entity.shopizer.merchant.MerchantStore;
import tech.madest.eparser.repository.MerchantStoreRepository;

import javax.annotation.PostConstruct;

@Component
public class GlobalManager {

    public static final Integer COMPANY_ID = 1;

    @Autowired
    MerchantStoreRepository merchantStoreRepo;

    static MerchantStore merchantStore;

    @PostConstruct
    void setMerchantStore(){
        merchantStore = merchantStoreRepo.findById( 1 ).get();
    }

    public static MerchantStore getMerchantStore() {
        return merchantStore;
    }
}
