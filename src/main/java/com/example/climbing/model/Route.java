
package com.example.climbing.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Route {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String grade;
    private Integer year;

    @ManyToOne
    private Crag crag;

    @ManyToOne
    private Climber climber1;

    @ManyToOne
    private Climber climber2;

    @ManyToOne
    private Climber climber3;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Crag getCrag() { return crag; }
    public void setCrag(Crag crag) { this.crag = crag; }

    public Climber getClimber1() {
        return climber1;
    }
    public void setClimber1(Climber climber1) {
        this.climber1 = climber1;
    }

    public Climber getClimber2() {
        return climber2;
    }
    public void setClimber2(Climber climber2) {
        this.climber2 = climber2;
    }

    public Climber getClimber3() {
        return climber3;
    }
    public void setClimber3(Climber climber3) {
        this.climber3 = climber3;
    }
}
