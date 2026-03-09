package com.example.climbing.service;

import com.example.climbing.exception.ResourceNotFoundException;
import com.example.climbing.model.Climber;
import com.example.climbing.repository.ClimberRepository;
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
