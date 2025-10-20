package com.inflatable.freelancer.repository;

import com.inflatable.freelancer.model.Inflatable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InflatableRepository extends JpaRepository<Inflatable, Long> {
    List<Inflatable> findByAvailableTrue();
    List<Inflatable> findByNameContainingIgnoreCase(String name);
}