package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.IncidenceFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenceFileRepository extends JpaRepository<IncidenceFiles, Long> {

    List<IncidenceFiles> findAllByIncidenceId(long id);

}
