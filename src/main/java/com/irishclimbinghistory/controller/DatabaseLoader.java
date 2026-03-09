package com.irishclimbinghistory.controller;

import com.irishclimbinghistory.model.Climber;
import com.irishclimbinghistory.model.Crag;
import com.irishclimbinghistory.model.Route;
import com.irishclimbinghistory.repository.ClimberRepository;
import com.irishclimbinghistory.repository.CragRepository;
import com.irishclimbinghistory.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/load")
public class DatabaseLoader {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

    private final CragRepository cragRepository;
    private final ClimberRepository climberRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public DatabaseLoader(CragRepository cragRepository, ClimberRepository climberRepository, RouteRepository routeRepository) {
        this.cragRepository = cragRepository;
        this.climberRepository = climberRepository;
        this.routeRepository = routeRepository;
    }

//    @GetMapping
    public void load() throws Exception {

        load("data-mournes.csv", "Mourne Mountains");

    }

    public void load(String file, String region) throws Exception {
        List<Map<String, String>> data = readCsv(file);

        data.stream()
                .map(row -> row.get("Crag"))
                .collect(Collectors.toSet())
                .forEach(
                        cragName -> {
                            Crag crag = new Crag();
                            crag.setName(cragName);
                            crag.setRegion(region);
                            cragRepository.save(crag);
                        });

        Set<String> climberNames = data.stream()
                .map(row -> row.get("Climber1"))
                .collect(Collectors.toSet());

        climberNames.addAll(data.stream()
                .map(row -> row.get("Climber2"))
                .collect(Collectors.toSet()));

        climberNames.addAll(data.stream()
                .map(row -> row.get("Climber3"))
                .collect(Collectors.toSet()));

        climberNames.stream().
                forEach(climberName -> {
                    Climber climber = new Climber();
                    climber.setName(climberName);
                    climberRepository.save(climber);
        });

        List<Crag> cragData = cragRepository.findAll();
        List<Climber> climberData = climberRepository.findAll();

        data.stream().forEach(row -> {
            Route route = new Route();

            String name = row.get("Route");
            String cragName = row.get("Crag");
            String grade = row.get("Grade");
            Integer year = Integer.valueOf(row.get("Year"));
            String climber1Name = row.get("Climber1");
            String climber2Name = row.get("Climber2");
            String climber3Name = row.get("Climber3");

            Optional<Crag> crag = cragData.stream()
                    .filter(thisOne -> cragName.equals(thisOne.getName()))
                    .findFirst();

            Optional<Climber> climber1 = climberData.stream()
                    .filter(thisOne -> climber1Name.equals(thisOne.getName()))
                    .findFirst();

            Optional<Climber> climber2 = climberData.stream()
                    .filter(thisOne -> climber2Name.equals(thisOne.getName()))
                    .findFirst();

            Optional<Climber> climber3 = climberData.stream()
                    .filter(thisOne -> climber3Name.equals(thisOne.getName()))
                    .findFirst();

            route.setName(name);
            route.setGrade(grade);
            route.setYear(year);
            route.setCrag(crag.get());
            route.setClimber1(climber1.get());
            route.setClimber2(climber2.get());
            route.setClimber3(climber3.get());

            routeRepository.save(route);
        });

    }


    public static List<Map<String, String>> readCsv(String filePath) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IOException("CSV file is empty");
            }

            String[] headers = headerLine.split(",", -1);

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1);
                Map<String, String> rowMap = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    String value = i < values.length ? values[i].trim() : "";
                    rowMap.put(headers[i].trim(), value);
                }

                rows.add(rowMap);
            }
        }

        return rows;
    }

}

