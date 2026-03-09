package com.irishclimbinghistory.controller;

import com.irishclimbinghistory.model.Crag;
import com.irishclimbinghistory.service.CragService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/crag")
public class CragController {

    private final CragService service;

    @Autowired
    public CragController(CragService service) {
        this.service = service;
    }

    @GetMapping
    public List<Crag> get(){
        return service.get();
    }

    @GetMapping(value = "/id/{id}")
    public Crag getById(@PathVariable Long id){
        return service.getById(id);
    }


}
