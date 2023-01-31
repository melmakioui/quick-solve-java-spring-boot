package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.IncidenceFiles;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface IncidenceFileService {


    List<IncidenceFiles> list();
    void validateFiles(MultipartFile[] files);
    void saveIncidenceFiles(MultipartFile[] files, Long incidenceId);
    void saveIncidenceFiles(MultipartFile[] files, FullIncidenceDTO incidenceDTO);
    List<IncidenceFiles> getAllIncidenceFilesByIncidenceId(Long id);

}
