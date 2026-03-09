package com.irishclimbinghistory.controller;

import com.irishclimbinghistory.model.Climber;
import com.irishclimbinghistory.service.ClimberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/climber")
public class ClimberController {

    private final ClimberService service;

    @Autowired
    public ClimberController(ClimberService service) {
        this.service = service;
    }

    @GetMapping
    public List<Climber> get(){
        return service.get();
    }

    @GetMapping(value = "/id/{id}")
    public Climber getById(@PathVariable Long id){
        return service.getById(id);
    }
}
