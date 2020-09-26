package com.msd.api.repository;

import com.msd.api.domain.Parent;
import com.msd.api.dto.ParentDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParentRepository extends PagingAndSortingRepository<Parent, Integer> {

}
