/**
 * The {@code CompanyValidator} class provides validation methods for the company entity.
 */
package com.JbSchool.coupons3.app.beans.company.config;

import com.JbSchool.coupons3.app.beans.purchase.config.PurchaseRepo;
import com.JbSchool.coupons3.app.utils.CouponException;
import com.JbSchool.coupons3.app.utils.ErrorMessageProvider;
import com.JbSchool.coupons3.security.config.CouponUserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The {@code CompanyValidator} class provides validation methods for the company entity.
 */
@Component
@RequiredArgsConstructor
public class CompanyValidator {
  
  private final CompanyRepo companyRepo;
  private final PurchaseRepo purchaseRepo;
  private final CouponUserValidator couponUserValidator;
  
  /**
   * Retrieves an optional company by its ID.
   *
   * @param id the ID of the company to retrieve
   * @return the optional company
   * @throws CouponException if the company with the given ID does not exist
   */
  public Company getOptionalCompany(int id) throws CouponException {
    return this.companyRepo.findById(id).orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
  }
  
  /**
   * Validates and adds a new company to the system.
   *
   * @param company the company to add
   * @throws CouponException if an error occurs during validation or adding the company
   */
  public void addCompany(Company company) throws CouponException {
    if (company.getId() != 0) {
      throw new CouponException(ErrorMessageProvider.ID_MUST_BE_EMPTY.getMessage());
    }
    companyParams(company, company.getId(), true);
  }
  
  /**
   * Validates and updates an existing company in the system.
   *
   * @param company the company to update
   * @param id      the ID of the company to update
   * @throws CouponException if an error occurs during validation or updating the company
   */
  public void updateCompany(Company company, int id) throws CouponException {
    companyParams(company, id, false);
  }
  /**
   * Validates the company parameters based on whether it is an add or update operation.
   *
   * @param company the company entity
   * @param id      the ID of the company
   * @param isAdd   flag indicating whether it is an add operation
   * @throws CouponException if an error occurs during validation
   */
  private void companyParams(Company company, int id, boolean isAdd) throws CouponException {
    if (isAdd) {
      this.couponUserValidator.addEmail(company.getEmail());
    } else {
      Company companyFromDb = getOptionalCompany(id);
      this.couponUserValidator.updateEmail(companyFromDb.getEmail(), company.getEmail());
    }
    if (this.companyRepo.existsByNameAndIdNot(company.getName(), id)) {
      throw new CouponException(ErrorMessageProvider.COMPANY_NAME_EXIST.getMessage());
    }
  }
}
