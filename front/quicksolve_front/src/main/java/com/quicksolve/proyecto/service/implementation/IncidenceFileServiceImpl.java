package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceFiles;
import com.quicksolve.proyecto.mapper.IncidenceMapper;
import com.quicksolve.proyecto.repository.IncidenceFileRepository;
import com.quicksolve.proyecto.service.IncidenceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class IncidenceFileServiceImpl implements IncidenceFileService {

    private final int MAX_FILE_QUANTITY = 5;

    @Autowired
    private IncidenceFileRepository incidenceFileRepository;

    @Autowired
    private Environment env;

    @Override
    public List<IncidenceFiles> list() {
        return incidenceFileRepository.findAll();
    }

    @Override
    public void validateFiles(MultipartFile[] files) {
        if(files.length > MAX_FILE_QUANTITY){
            throw new RuntimeException("No se pueden subir mas de 5 archivos");
        }

        for (MultipartFile file : files) {
            if (!Objects.equals(file.getContentType(), "image/jpeg") && !Objects.equals(file.getContentType(), "image/png")) {
                throw new IllegalArgumentException("Solo se pueden subir archivos de tipo imagen");
            }
        }
    }

    @Override
    public void saveIncidenceFiles(MultipartFile[] files, Long incidenceId) {
        //incidenceFileRepository.save()
    }

    @Override
    public void saveIncidenceFiles(MultipartFile[] files, FullIncidenceDTO incidenceDTO) {

        Arrays.stream(files).toList().forEach(file -> {
            try{
                byte[] bytes = file.getBytes();
                Path completePath = Paths.get(env.getProperty("upload.path") + file.getOriginalFilename()); //funciona
                Files.write(completePath, bytes);
            }catch (IOException e){
                e.printStackTrace();
            }

            Incidence incidence = IncidenceMapper.INSTANCE.dtoToIncidence(incidenceDTO);
            IncidenceFiles incidenceFiles = new IncidenceFiles();
            incidenceFiles.setFilePath("/uploads/" + file.getOriginalFilename());
            incidenceFiles.setIncidence(incidence);
            incidenceFileRepository.save(incidenceFiles);
        });
    }

    @Override
    public List<IncidenceFiles> getAllIncidenceFilesByIncidenceId(Long id) {
        return incidenceFileRepository.findAllByIncidenceId(id);
    }


}
