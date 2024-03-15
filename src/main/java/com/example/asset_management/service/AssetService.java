package com.example.asset_management.service;


import com.example.asset_management.entity.Asset;
import com.example.asset_management.entity.AssignmentStatus;
import com.example.asset_management.exception.AssetManagementException;
import com.example.asset_management.exception.GlobalExceptionHandler;
import com.example.asset_management.repository.AssetRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.ast.OpPlus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

//    public Asset createAsset(Asset asset) {
//        return assetRepository.save(asset);
//    }
    public List<Asset> createAssets(List<Asset> assets) {
        return assetRepository.saveAll(assets);
    }

    public List<Asset> getAllAssets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Asset> assetPage = assetRepository.findAll(pageable);
        return assetPage.getContent();
    }
    public Asset getAssetById(Long assetId) {
        Optional<Asset> assetOptional = assetRepository.findById(assetId);
        return assetOptional.orElseThrow(()-> new AssetManagementException("Asset Not Found : " + assetId));
    }

    public Asset getAssetByName(String assetName){
        Optional<Asset> assetOptional = assetRepository.findByName(assetName);
        return assetOptional.orElseThrow(()-> new AssetManagementException("Asset not Found : " + assetName));
    }

    public Asset updateAsset(Long assetId, Asset updatedAsset){
        Optional<Asset> existingAsset = assetRepository.findById(assetId);
        if(existingAsset.isEmpty()){
            throw new AssetManagementException("Asset not found : "+assetId);
        }
        Asset asset = existingAsset.get();
        asset.setName(updatedAsset.getName());
//        asset.setSerialNumber(updatedAsset.getSerialNumber());
        asset.setPurchaseDate((updatedAsset.getPurchaseDate()));
//        asset.setPurchaseDate(updatedAsset.getPurchaseDate());
        asset.setConditionNotes(updatedAsset.getConditionNotes());
        asset.setCategory(updatedAsset.getCategory());
        asset.setAssignmentStatus(updatedAsset.getAssignmentStatus());
        return assetRepository.save(asset);
    }

    public void assignAsset(Long assetId, AssignmentStatus assignmentStatus){
        Optional<Asset> existingAsset = assetRepository.findById(assetId);
        if(existingAsset.isPresent()) {
            Asset asset = existingAsset.get();
            if (asset.getAssignmentStatus() == AssignmentStatus.Available || asset.getAssignmentStatus() == AssignmentStatus.Recovered) {
                asset.setAssignmentStatus(assignmentStatus);
                assetRepository.save(asset);
            } else {
                throw new AssetManagementException("Couldn't able to assign : " + assetId);
            }
        }
        else{
            throw new AssetManagementException("Asset not found : " + assetId);
        }

    }
    public void recoverAsset(Long assetId){
        Optional<Asset> existingAsset = assetRepository.findById(assetId);
        if(existingAsset.isPresent()){
            Asset asset = existingAsset.get();
            if (asset.getAssignmentStatus() == AssignmentStatus.Available){
                throw new AssetManagementException("asset "+assetId+" is already Available");
            } else if (asset.getAssignmentStatus() == AssignmentStatus.Recovered) {
                throw new AssetManagementException("Asset :" + assetId+" is already Recovered");
            }
            else {
                asset.setAssignmentStatus(AssignmentStatus.Recovered);
                assetRepository.save(asset);
            }
        }
        else {
            throw new AssetManagementException("asset not found : "+assetId);
        }
    }

    public void deleteAsset(Long assetId){
        assetRepository.deleteById(assetId);
    }
}


//    public ResponseEntity<String> addAsset(List<Asset> assets)
//    {
//        try
//        {
//            assetRepository.saveAll(assets);
//            return ResponseEntity.ok("Assets added successfully");
//        }
//        catch(AssetManagementException ex)
//        {
//            return ResponseEntity.badRequest().body("Error : A record with same batch Id already exists");
//        }
//        catch(Exception ex)
//        {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : Failed to add asset");
//        }
//
//    }

//    public List<Asset> createAssets(List<Asset> assets) {
//        return assetRepository.saveAll(assets);
//    }

//public ResponseEntity<String> createAssets(Asset asset) {
//    assetRepository.save(asset);
//    return ResponseEntity.ok("Added successfully");
//}

//    public List<Asset> getAllAssets() {
//        return assetRepository.findAll();
//    }

//    public ResponseEntity<Asset> updateProduct(Long id, Asset updatedAsset)
//    {
//        if (id == null) {
//            throw new IllegalArgumentException(
//                    "ID cannot be null");
//        }
//        Asset existingAsset = assetRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(String.valueOf(id)));
//        existingAsset.setName(updatedAsset.getName());
//        existingAsset.setPurchaseDate(updatedAsset.getPurchaseDate());
//        existingAsset.setConditionNotes(updatedAsset.getConditionNotes());
//        existingAsset.setCategory(updatedAsset.getCategory());
//        existingAsset.setAssignmentStatus(updatedAsset.getAssignmentStatus());
//        Asset savedEntity = assetRepository.save(existingAsset);
//        return ResponseEntity.ok(savedEntity);
//    }