package com.juan.inter.digitoUnico.digitoUnico.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juan.inter.digitoUnico.digitoUnico.model.DigitoUnico;

public interface DigitoUnicoRepository extends JpaRepository<DigitoUnico, Long> {
	
	@Query("select d from DigitoUnico d")
    List<DigitoUnico> findAllResults(Pageable pageable);
	
	default List<DigitoUnico> ultimosResultados(int qtde) {
        return findAllResults(PageRequest.of(0, qtde, Sort.by("id").descending()));
    }

}
