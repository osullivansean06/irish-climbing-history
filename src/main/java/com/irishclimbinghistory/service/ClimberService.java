package com.irishclimbinghistory.service;

import com.irishclimbinghistory.exception.ResourceNotFoundException;
import com.irishclimbinghistory.model.Climber;
import com.irishclimbinghistory.repository.ClimberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClimberService {

    private final ClimberRepository repository;

    @Autowired
    public ClimberService(ClimberRepository repository) {
        this.repository = repository;
    }

    public List<Climber> get() {
        return repository.findAll();
    }

    public Climber getById(Long id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
