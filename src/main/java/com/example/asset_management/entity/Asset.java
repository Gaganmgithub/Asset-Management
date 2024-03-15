package com.example.asset_management.entity;

import jakarta.persistence.*;
//import jdk.jfr.Category;

import java.util.Date;
@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
//    @Column(nullable = false, unique = true)
//    private String serialNumber;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String conditionNotes;
    @Column(nullable = false)
    private String purchaseDate;
    @ManyToOne
    @JoinColumn(name = "catId")
    private Category category;
    @Enumerated(EnumType.STRING)
    private AssignmentStatus assignmentStatus;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConditionNotes() {
        return conditionNotes;
    }

    public void setConditionNotes(String conditionNotes) {
        this.conditionNotes = conditionNotes;
    }


    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AssignmentStatus getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(AssignmentStatus assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }
}
