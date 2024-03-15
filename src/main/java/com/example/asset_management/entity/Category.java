package com.example.asset_management.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

    @Entity
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long catId;

//    @Column(name = "category_id", columnDefinition = "INTEGER")
//    private Integer categoryId;

        @Column(nullable = false)

        private String name;
        @Column(length = 1000)
        private String description;

        public Long getId() {
            return catId;
        }

        public void setId(Long id) {
            catId = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
