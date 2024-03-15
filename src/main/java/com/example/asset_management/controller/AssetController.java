package com.example.asset_management.controller;

import com.example.asset_management.entity.Asset;
import com.example.asset_management.entity.AssignmentStatus;
import com.example.asset_management.exception.AssetManagementException;
import com.example.asset_management.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    private AssetService assetService;

//    @PostMapping("/createAssets")
//    public ResponseEntity<?> createAsset(@RequestBody Asset asset) {
//        try {
//            Asset createdAsset = assetService.createAsset(asset);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdAsset);
//        } catch (AssetManagementException ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }

    @PostMapping("/createAssets")
    public ResponseEntity<?> createAssets(@RequestBody List<Asset> assets) {
        try {
            List<Asset> createdAssets = assetService.createAssets(assets);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAssets);
        } catch (AssetManagementException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/getAssets")
    public ResponseEntity<List<Asset>> getAllAssets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Asset> assets = assetService.getAllAssets(page, size);
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/id/{assetId}")
    public Asset getAssetById(@PathVariable Long assetId){

        return assetService.getAssetById(assetId);
    }

    @GetMapping("/name/{assetName}")
    public Asset getAssetByName(@PathVariable String assetName){

        return assetService.getAssetByName(assetName);
    }

    @PutMapping("/{assetId}/assign")
    public ResponseEntity<Void> assignAsset(@PathVariable Long assetId, @RequestBody AssignmentStatus assignmentStatus){
        assetService.assignAsset(assetId,assignmentStatus);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{assetId}/recover")
    public ResponseEntity<Void> recoverAsset(@PathVariable Long assetId){
        assetService.recoverAsset(assetId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{assetId}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long assetId, @RequestBody Asset updatedAsset){
        Asset asset = assetService.updateAsset(assetId, updatedAsset);
        if (asset != null) {
            return ResponseEntity.ok(asset);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{assetId}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long assetId){
        assetService.deleteAsset((assetId));
        return ResponseEntity.noContent().build();
    }
}

//    @PostMapping("/createAssets")
//    public ResponseEntity<?> createAssets(@RequestBody List<Asset> assets) {
//        try {
//            List<Asset> createdAssets = assetService.createAssets(assets);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdAssets);
//        } catch (AssetManagementException ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }

//    @PostMapping("/createAssets")
//    public ResponseEntity<String> addAsset(@RequestBody List<Asset> assets)
//    {
//        ResponseEntity<String> response = assetService.addAsset(assets);
//
//        if(response.getStatusCode().is2xxSuccessful())
//        {
//            return ResponseEntity.ok(response.getBody());
//        }
//        else
//        {
//            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
//        }
//    }

//    @GetMapping("/getAssets")
//    public ResponseEntity<List<Asset>> getAllAssets() {
//        List<Asset> assets = assetService.getAllAssets(0,10);
//        return ResponseEntity.ok(assets);
//    }

//    @PostMapping("/createAsset")
//    public ResponseEntity<?> createAsset(@RequestBody Asset asset) {
////        Asset createdAsset = assetService.createAsset(asset);
////        return ResponseEntity.ok(createdAsset);
//        try {
//            Asset createdAsset = assetService.createAssets(asset);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdAsset);
//        } catch (AssetManagementException ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }
