package com.inflatable.freelancer;

import com.inflatable.freelancer.dto.InflatableDTO;
import com.inflatable.freelancer.model.Inflatable;
import com.inflatable.freelancer.repository.InflatableRepository;
import com.inflatable.freelancer.service.UserService;
import com.inflatable.freelancer.service.impl.InflatableServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InflatableServiceTest {

    @Mock
    private InflatableRepository inflatableRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private InflatableServiceImpl inflatableService;

    @Test
    void shouldFindAllInflatables() {
        Inflatable inflatable = new Inflatable();
        inflatable.setId(1L);
        inflatable.setName("Castelo Inflável");
        inflatable.setPricePerDay(new BigDecimal("150.00"));

        when(inflatableRepository.findAll()).thenReturn(Arrays.asList(inflatable));

        List<InflatableDTO> inflatables = inflatableService.findAll();

        assertFalse(inflatables.isEmpty());
        assertEquals(1, inflatables.size());
        assertEquals("Castelo Inflável", inflatables.get(0).getName());
    }

    @Test
    void shouldFindInflatableById() {
        Inflatable inflatable = new Inflatable();
        inflatable.setId(1L);
        inflatable.setName("Castelo Inflável");

        when(inflatableRepository.findById(1L)).thenReturn(Optional.of(inflatable));

        InflatableDTO found = inflatableService.findById(1L);

        assertNotNull(found);
        assertEquals("Castelo Inflável", found.getName());
    }

    @Test
    void shouldFindAvailableInflatables() {
        Inflatable availableInflatable = new Inflatable();
        availableInflatable.setId(1L);
        availableInflatable.setName("Castelo Inflável");
        availableInflatable.setAvailable(true);

        when(inflatableRepository.findByAvailableTrue()).thenReturn(Arrays.asList(availableInflatable));

        List<InflatableDTO> available = inflatableService.findAvailable();

        assertFalse(available.isEmpty());
        assertTrue(available.get(0).getAvailable());
    }
}