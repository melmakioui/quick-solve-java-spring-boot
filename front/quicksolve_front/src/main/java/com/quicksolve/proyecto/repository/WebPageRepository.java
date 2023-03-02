package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.WebPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebPageRepository extends JpaRepository<WebPage, Long> {

}
