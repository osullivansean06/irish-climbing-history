package com.example.climbing.service;

import com.example.climbing.exception.ResourceNotFoundException;
import com.example.climbing.model.Route;
import com.example.climbing.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository repository;

    @Autowired
    public RouteService(RouteRepository repository) {
        this.repository = repository;
    }

    public List<Route> get(){
        return repository.findAll();
    }

    public Route getById(Long id){
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
