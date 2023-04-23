package com.JbSchool.coupons3.app.beans.purchase.config;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {

}
