package com.example.climbing.service;

import com.example.climbing.exception.ResourceNotFoundException;
import com.example.climbing.model.Climber;
import com.example.climbing.model.Crag;
import com.example.climbing.model.Route;
import com.example.climbing.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository repository;
    private final ClimberService climberService;
    private final CragService cragService;

    @Autowired
    public RouteService(RouteRepository repository, ClimberService climberService, CragService cragService) {
        this.repository = repository;
        this.climberService = climberService;
        this.cragService = cragService;
    }

    public Route getById(Long id){
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Route> getByYear(Integer year) {
        return repository.getByYear(year);
    }

    public List<Route> getByClimberId(Long climberId) {
        //TODO get by climber1, climber2 or climber3?
        Climber climber = climberService.getById(climberId);
        return repository.getByClimber1(climber);
    }

    public List<Route> getByCragId(Long cragId) {
        Crag crag = cragService.getById(cragId);
        return repository.getByCrag(crag);
    }
}
