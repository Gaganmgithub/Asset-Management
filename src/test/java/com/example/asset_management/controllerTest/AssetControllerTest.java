package com.example.asset_management.controllerTest;


import com.example.asset_management.controller.AssetController;
import com.example.asset_management.entity.Asset;
import com.example.asset_management.entity.AssignmentStatus;
import com.example.asset_management.entity.Category;
import com.example.asset_management.exception.AssetManagementException;
import com.example.asset_management.service.AssetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssetControllerTest {

    @Mock
    private AssetService assetService;

    @InjectMocks
    private AssetController assetController;

    @Before
    public void setUp() {

        Category category = new Category();
        category.setId(1L);
        category.setName("Category 1");

        Asset asset1 = new Asset();
        asset1.setId(1L);
        asset1.setName("Asset 1");
        asset1.setCategory(category);
        asset1.setPurchaseDate("20-01-2001");
        asset1.setConditionNotes("Good condition");
        asset1.setAssignmentStatus(AssignmentStatus.Assigned);

        Asset asset2 = new Asset();
        asset2.setId(2L);
        asset2.setName("Asset 2");
        asset2.setCategory(category);
        asset2.setPurchaseDate("2023-01-02");
        asset2.setConditionNotes("Fair condition");
        asset2.setAssignmentStatus(AssignmentStatus.Available);


        when(assetService.getAllAssets(0, 5)).thenReturn(Collections.singletonList(asset1));
        when(assetService.getAssetById(1L)).thenReturn(asset1);
    }

    @Test
    public void testGetAllAssets() {

        List<Asset> mockAssets = Collections.singletonList(new Asset());
        when(assetService.getAllAssets(0, 5)).thenReturn(mockAssets);


        ResponseEntity<List<Asset>> response = assetController.getAllAssets(0, 5);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAssets, response.getBody());
    }

    @Test
    public void testGetAssetById() {
        Asset response = assetController.getAssetById(1L);


        assertNotNull(response);
        assertEquals(1L, response.getId().longValue());
        assertEquals("Asset 1", response.getName());
    }


    @Test
    public void testCreateAsset() throws AssetManagementException, ParseException {
        Asset asset1 = new Asset();
        asset1.setName("New Asset");
        asset1.setCategory(new Category());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        Date purchaseDate = null;
//        try {
//            purchaseDate = dateFormat.parse("01-01-2024");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        asset1.setPurchaseDate(purchaseDate);
        asset1.setPurchaseDate("2024-01-01");

        asset1.setConditionNotes("New condition");
        asset1.setAssignmentStatus(AssignmentStatus.Available);


        Asset asset2 = new Asset();
        asset2.setName("Asset 2");
        asset2.setCategory(new Category());
        asset2.setPurchaseDate("2024-09-01");
//        simpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        Date purchaseDate = null;
//        try {
//            purchaseDate = dateFormat.parse("01-04-2024");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        asset1.setPurchaseDate(purchaseDate);

        asset2.setConditionNotes("good");
        asset2.setAssignmentStatus(AssignmentStatus.Available);

//        when(assetService.createAsset(assetToCreate)).thenReturn(assetToCreate);
//
//        ResponseEntity<?> response = assetController.createAsset(assetToCreate);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        List<Asset> assetsToCreate = Arrays.asList(asset1, asset2);

        when(assetService.createAssets(assetsToCreate)).thenReturn(assetsToCreate);

        ResponseEntity<?> response = assetController.createAssets(assetsToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateAsset() {
        Asset updatedAsset = new Asset();
        updatedAsset.setId(1L);
        updatedAsset.setName("Updated Asset");

        when(assetService.updateAsset(1L, updatedAsset)).thenReturn(updatedAsset);

        ResponseEntity<Asset> response = assetController.updateAsset(1L, updatedAsset);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Asset", response.getBody().getName());
    }

    @Test
    public void testDeleteAsset() {
        ResponseEntity<Void> response = assetController.deleteAsset(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(assetService).deleteAsset(1L);
    }
}