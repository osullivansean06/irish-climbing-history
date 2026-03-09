
package com.irishclimbinghistory.repository;

import com.irishclimbinghistory.model.Climber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClimberRepository extends JpaRepository<Climber, Long> {}
