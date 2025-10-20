package com.inflatable.freelancer.service;

import com.inflatable.freelancer.dto.InflatableDTO;

import java.util.List;

public interface InflatableService {
    List<InflatableDTO> findAll();
    List<InflatableDTO> findAvailable();
    InflatableDTO findById(Long id);
    InflatableDTO create(InflatableDTO inflatableDTO);
    InflatableDTO update(Long id, InflatableDTO inflatableDTO);
    void delete(Long id);
}