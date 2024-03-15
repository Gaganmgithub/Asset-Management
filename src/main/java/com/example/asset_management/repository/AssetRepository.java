package com.example.asset_management.repository;

import com.example.asset_management.entity.Asset;
import com.example.asset_management.entity.AssignmentStatus;
import com.example.asset_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    Optional<Asset> findByName(String assetName);

    List<Asset> findByAssignmentStatus(AssignmentStatus assignmentStatus);

    List<Asset> findByCategory(Category category);

}

