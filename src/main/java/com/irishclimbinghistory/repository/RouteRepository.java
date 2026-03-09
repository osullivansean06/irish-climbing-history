
package com.irishclimbinghistory.repository;

import com.irishclimbinghistory.model.Climber;
import com.irishclimbinghistory.model.Crag;
import com.irishclimbinghistory.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> getByYear(Integer year);

    List<Route> getByClimber1(Climber climber);

    List<Route> getByCrag(Crag crag);
}
