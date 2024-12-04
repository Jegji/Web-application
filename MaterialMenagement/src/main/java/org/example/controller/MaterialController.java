package org.example.controller;

import org.example.dto.MaterialCreateDto;
import org.example.dto.MaterialReadDto;
import org.example.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<MaterialReadDto> getAllMaterials() {
        List<MaterialReadDto> materials = materialService.getAllMaterials();
        return ResponseEntity.ok(materials).getBody();
    }


    @GetMapping("/{materialId}")
    public ResponseEntity<MaterialReadDto> getMaterialById(@PathVariable UUID materialId) {
        try {
            MaterialReadDto materialDetailDTO = materialService.getMaterialById(materialId);
            return ResponseEntity.ok(materialDetailDTO);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<MaterialReadDto> updateMaterial(
            @PathVariable UUID materialId,
            @RequestBody MaterialCreateDto materialCreateDto){
        try {
            materialService.updateMaterial(materialId, materialCreateDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable UUID materialId) {
        if (materialService.deleteMaterial(materialId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MaterialReadDto> createMaterial(@RequestBody MaterialCreateDto materialCreateDto) {
        materialService.createMaterial(materialCreateDto);
        return ResponseEntity.status(201).build();
    }


}
