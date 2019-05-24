package com.app.repository;

import com.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, ExtendedRepository<User, Long> {
   Optional<User> findByUsername(String name);

   Optional<User> findOneByEmailIgnoreCase(String email);
}
