package org.application.openschoolhomeworkaop.repositories;

import org.application.openschoolhomeworkaop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
