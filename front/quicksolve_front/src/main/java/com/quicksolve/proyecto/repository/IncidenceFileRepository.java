package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.IncidenceFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface IncidenceFileRepository extends JpaRepository<IncidenceFiles, Long> {

    Set<IncidenceFiles> findAllByIncidenceId(long id);
    void deleteByIdAndIncidenceId(long id, long incidenceId);
    IncidenceFiles findByIdAndIncidenceId(long id, long incidenceId);
    void deleteAllByIncidenceId(long id);
    void deleteByFilePathAndIncidenceId(String src, long incidenceId);

}
