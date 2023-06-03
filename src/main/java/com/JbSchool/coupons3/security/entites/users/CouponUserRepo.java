package com.JbSchool.coupons3.security.entites.users;

import org.springframework.data.jpa.repository.*;

/**
 * Repository interface for managing CouponUser entities.
 */
public interface CouponUserRepo extends JpaRepository<CouponUser,Integer> {
  
  /**
   * Finds a coupon user by their username.
   *
   * @param username The username of the coupon user.
   * @return The found coupon user.
   */
  CouponUser findByUsername(String username);
  
  /**
   * Checks if a coupon user exists with the given username.
   *
   * @param username The username to check.
   * @return true if the username exists, false otherwise.
   */
  boolean existsByUsername(String username);
  
  /**
   * Deletes a coupon user by their username.
   *
   * @param username The username of the coupon user to delete.
   */
  void deleteByUsername(String username);
  
  /**
   * Checks if a coupon user exists with the given username and ID not equal to the specified ID.
   *
   * @param email The username to check.
   * @param id    The ID to exclude from the check.
   * @return true if the username exists and the ID is not equal, false otherwise.
   */
  boolean existsByUsernameAndIdNot(String email, int id);
}