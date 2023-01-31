package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.FileDTO;
import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.IncidenceFiles;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Repository
public interface IncidenceFileService {


    List<IncidenceFiles> list();
    void validateFiles(MultipartFile[] files);
    void saveIncidenceFiles(MultipartFile[] files, Long incidenceId);
    void saveIncidenceFiles(MultipartFile[] files, FullIncidenceDTO incidenceDTO);
    Set<FileDTO> findAllByIncidenceId(Long id);
    void deleteById(Long id);
    void deleteByIdAndIncidenceId(Long id, Long incidenceId);

}
