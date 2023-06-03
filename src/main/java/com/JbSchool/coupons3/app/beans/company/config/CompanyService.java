/**
 * The {@code CompanyService} interface defines the operations that can be performed on company entities.
 */
package com.JbSchool.coupons3.app.beans.company.config;

import com.JbSchool.coupons3.app.utils.CouponException;

import java.util.List;

/**
 * The {@code CompanyService} interface defines the operations that can be performed on company entities.
 */
public interface CompanyService {
  
  /**
   * Adds a new company to the system.
   *
   * @param company the company to add
   * @return the created company as a {@link CompanyDto}
   * @throws CouponException if an error occurs while adding the company
   */
  CompanyDto addCompany(Company company) throws CouponException;
  
  /**
   * Updates an existing company in the system.
   *
   * @param company   the company to update
   * @param companyId the ID of the company to update
   * @throws CouponException if an error occurs while updating the company
   */
  void updateCompany(Company company, int companyId) throws CouponException;
  
  /**
   * Retrieves a single company by its ID.
   *
   * @param id the ID of the company to retrieve
   * @return the company with the specified ID as a {@link CompanyDto}
   * @throws CouponException if the company with the given ID does not exist
   */
  CompanyDto getSingleCompany(int id) throws CouponException;
  
  /**
   * Deletes a company from the system by its ID.
   *
   * @param id the ID of the company to delete
   * @throws CouponException if an error occurs while deleting the company
   */
  void deleteCompany(int id) throws CouponException;
  
  /**
   * Retrieves all companies from the system.
   *
   * @return a list of all companies as {@link CompanyDto}s
   */
  List<CompanyDto> getAllCompanies();
  
  /**
   * Retrieves the company currently logged in.
   *
   * @return the logged-in company as a {@link CompanyDto}
   * @throws CouponException if no company is currently logged in
   */
  CompanyDto getLoggedCompany() throws CouponException;
  
  /**
   * Finds a company by its email.
   *
   * @param email the email of the company to find
   * @return the company with the specified email as a {@link CompanyDto}, or {@code null} if not found
   */
  CompanyDto findByEmail(String email);
}
