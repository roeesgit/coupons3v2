/**
 * The {@code CompanyRepo} interface is a repository interface for accessing and managing company data in the database.
 * It extends the {@link JpaRepository} interface, providing basic CRUD operations for the {@link Company} entity.
 */
package com.JbSchool.coupons3.app.beans.company.config;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The {@code CompanyRepo} interface is a repository interface for accessing and managing company data in the database.
 * It extends the {@link JpaRepository} interface, providing basic CRUD operations for the {@link Company} entity.
 */
public interface CompanyRepo extends JpaRepository<Company, Integer> {
  
  /**
   * Retrieves all companies from the database.
   *
   * @return a list of all companies
   */
  List<Company> findAll();
  
  /**
   * Checks if a company with the given email exists, excluding the company with the specified ID.
   *
   * @param email the email to check
   * @param id    the ID to exclude
   * @return {@code true} if a company with the given email exists and has a different ID, {@code false} otherwise
   */
  boolean existsByEmailAndIdNot(String email, int id);
  
  /**
   * Retrieves a company by its email.
   *
   * @param email the email of the company
   * @return the company with the specified email, or {@code null} if not found
   */
  Company findByEmail(String email);
  
  /**
   * Checks if a company with the given name exists, excluding the company with the specified ID.
   *
   * @param name the name to check
   * @param id   the ID to exclude
   * @return {@code true} if a company with the given name exists and has a different ID, {@code false} otherwise
   */
  boolean existsByNameAndIdNot(String name, int id);
}

//  @Query(nativeQuery = true,value = "select (count(c.id) > 0) from Companies c where c.name = ?1 and c.name != ?1")
//  int existsByNameAndNameNot(String toString);


//  @Query(nativeQuery = true,value = "select (count(c.id) > 0) from Companies c where c.email = ?1 and c.email != ?1")
//  int existsByEmailAndNotEmail(String email);


