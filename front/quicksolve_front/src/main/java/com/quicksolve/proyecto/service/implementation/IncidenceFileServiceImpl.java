package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.FileDTO;
import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceFiles;
import com.quicksolve.proyecto.mapper.IncidenceFileMapper;
import com.quicksolve.proyecto.mapper.IncidenceMapper;
import com.quicksolve.proyecto.repository.IncidenceFileRepository;
import com.quicksolve.proyecto.service.IncidenceFileService;
import jakarta.transaction.Transactional;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IncidenceFileServiceImpl implements IncidenceFileService {

    private final int MAX_FILE_QUANTITY = 5;
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

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

        if(files[0].getOriginalFilename().equals("")){
            return;
        }
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

        if(files[0].getOriginalFilename().equals("")){
            return;
        }

        Arrays.stream(files).toList().forEach(file -> {

            String fileName = generateRandomString(10) + file.getOriginalFilename();

            try{
                byte[] bytes = file.getBytes();
                Path completePath = Paths.get(env.getProperty("upload.path") + fileName);
                Files.write(completePath, bytes);
            }catch (IOException e){
                e.printStackTrace();
            }

            Incidence incidence = IncidenceMapper.INSTANCE.dtoToIncidence(incidenceDTO);
            IncidenceFiles incidenceFiles = new IncidenceFiles();
            incidenceFiles.setFilePath("/uploads/" + fileName);
            incidenceFiles.setIncidence(incidence);
            incidenceFileRepository.save(incidenceFiles);
        });
    }

    @Override
    public Set<FileDTO> findAllByIncidenceId(Long id) {
        return incidenceFileRepository.findAllByIncidenceId(id).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteById(Long id) {
        incidenceFileRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByIdAndIncidenceId(Long id, Long incidenceId) {
        IncidenceFiles incidenceFile = incidenceFileRepository.findByIdAndIncidenceId(id, incidenceId);

        if (incidenceFile == null) {
            throw new RuntimeException("No se ha encontrado el archivo");
        }

        incidenceFileRepository.deleteByIdAndIncidenceId(id, incidenceId);
    }

    private FileDTO convertToDTO(IncidenceFiles incidenceFiles){
        return IncidenceFileMapper.INSTANCE.fileToDTO(incidenceFiles);
    }

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (CHARACTERS.length() * Math.random());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
