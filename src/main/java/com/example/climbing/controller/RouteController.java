package com.example.climbing.controller;

import com.example.climbing.model.Route;
import com.example.climbing.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/route")
public class RouteController {

    private final RouteService service;

    @Autowired
    public RouteController(RouteService service) {
        this.service = service;
    }

    @GetMapping(value = "/id/{id}")
    public Route getById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping(value = "/year/{year}")
    public List<Route> getByYear(@PathVariable Integer year){
        return service.getByYear(year);
    }

    @GetMapping(value = "/climber/{climberId}")
    public List<Route> getByClimberId(@PathVariable Long climberId){
        return service.getByClimberId(climberId);
    }

    @GetMapping(value = "/crag/{cragId}")
    public List<Route> getByCragId(@PathVariable Long cragId){
        return service.getByCragId(cragId);
    }
}
