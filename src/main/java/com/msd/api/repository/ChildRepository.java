package com.msd.api.repository;

import com.msd.api.domain.Child;
import com.msd.api.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child,Integer>{
}
