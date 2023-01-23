package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserIncidence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserIncidenceRepository extends JpaRepository<UserIncidence, Long> {
    List<UserIncidence> findAllByUser(User user);
}
