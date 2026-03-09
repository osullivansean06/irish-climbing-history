
package com.example.climbing.repository;

import com.example.climbing.model.Climber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClimberRepository extends JpaRepository<Climber, Long> {}
