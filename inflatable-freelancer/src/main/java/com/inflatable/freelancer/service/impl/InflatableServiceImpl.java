package com.inflatable.freelancer.service.impl;

import com.inflatable.freelancer.dto.InflatableDTO;
import com.inflatable.freelancer.model.Inflatable;
import com.inflatable.freelancer.repository.InflatableRepository;
import com.inflatable.freelancer.service.InflatableService;
import com.inflatable.freelancer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InflatableServiceImpl implements InflatableService {

    @Autowired
    private InflatableRepository inflatableRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<InflatableDTO> findAll() {
        return inflatableRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InflatableDTO> findAvailable() {
        return inflatableRepository.findByAvailableTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InflatableDTO findById(Long id) {
        Inflatable inflatable = inflatableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brinquedo inflável não encontrado"));
        return convertToDTO(inflatable);
    }

    @Override
    public InflatableDTO create(InflatableDTO inflatableDTO) {
        Inflatable inflatable = convertToEntity(inflatableDTO);
        inflatable.setCreatedBy(userService.getCurrentUser().getId());
        Inflatable savedInflatable = inflatableRepository.save(inflatable);
        return convertToDTO(savedInflatable);
    }

    @Override
    public InflatableDTO update(Long id, InflatableDTO inflatableDTO) {
        Inflatable existingInflatable = inflatableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brinquedo inflável não encontrado"));
        
        existingInflatable.setName(inflatableDTO.getName());
        existingInflatable.setDescription(inflatableDTO.getDescription());
        existingInflatable.setPricePerDay(inflatableDTO.getPricePerDay());
        existingInflatable.setSize(inflatableDTO.getSize());
        existingInflatable.setAvailable(inflatableDTO.getAvailable());
        
        Inflatable updatedInflatable = inflatableRepository.save(existingInflatable);
        return convertToDTO(updatedInflatable);
    }

    @Override
    public void delete(Long id) {
        if (!inflatableRepository.existsById(id)) {
            throw new RuntimeException("Brinquedo inflável não encontrado");
        }
        inflatableRepository.deleteById(id);
    }

    private InflatableDTO convertToDTO(Inflatable inflatable) {
        return new InflatableDTO(
            inflatable.getId(),
            inflatable.getName(),
            inflatable.getDescription(),
            inflatable.getPricePerDay(),
            inflatable.getSize(),
            inflatable.getAvailable(),
            inflatable.getCreatedBy()
        );
    }

    private Inflatable convertToEntity(InflatableDTO dto) {
        return new Inflatable(
            dto.getName(),
            dto.getDescription(),
            dto.getPricePerDay(),
            dto.getSize(),
            dto.getCreatedBy()
        );
    }
}