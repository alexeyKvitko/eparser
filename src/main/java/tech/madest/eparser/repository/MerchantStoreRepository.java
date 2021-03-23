package tech.madest.eparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.madest.eparser.entity.shopizer.merchant.MerchantStore;

public interface MerchantStoreRepository extends JpaRepository< MerchantStore, Integer > {
}
