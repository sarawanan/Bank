package com.assignment.web.repository;

import com.assignment.web.entity.Liability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiabilityRepository extends JpaRepository<Liability, Integer> {

}
