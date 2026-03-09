
package com.example.climbing.repository;

import com.example.climbing.model.Climber;
import com.example.climbing.model.Crag;
import com.example.climbing.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> getByYear(Integer year);

    List<Route> getByClimber1(Climber climber);

    List<Route> getByCrag(Crag crag);
}
