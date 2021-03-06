package com.app.repository;

import com.app.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
