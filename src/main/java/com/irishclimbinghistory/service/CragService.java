package com.irishclimbinghistory.service;

import com.irishclimbinghistory.exception.ResourceNotFoundException;
import com.irishclimbinghistory.model.Crag;
import com.irishclimbinghistory.repository.CragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CragService {

    private final CragRepository repository;

    @Autowired
    public CragService(CragRepository repository) {
        this.repository = repository;
    }

    public List<Crag> get() {
        return repository.findAll();
    }

    public Crag getById(Long id){
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
