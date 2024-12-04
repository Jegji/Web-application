package org.example.controller;

import org.example.SimplifiedMaterial;
import org.example.dto.FilamentCreateDto;
import org.example.dto.FilamentReadDto;
import org.example.service.FilamentService;
import org.example.service.SimplifiedMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/filaments")
public class FilamentController {

    private final FilamentService filamentService;
    private final SimplifiedMaterialService materialService;

    @Autowired
    public FilamentController(FilamentService filamentService, SimplifiedMaterialService materialService) {
        this.filamentService = filamentService;
        this.materialService = materialService;
    }
    @GetMapping
    public ResponseEntity<List<FilamentReadDto>> getAllFilaments() {
        List<FilamentReadDto> filaments = filamentService.getAllFilaments();
        return ResponseEntity.ok(filaments);
    }
    
    @GetMapping("/{filamentId}")
    public ResponseEntity<FilamentReadDto> getFilamentByID(@PathVariable UUID filamentId){
        try {
            FilamentReadDto filaments = filamentService.getFilamentById(filamentId);
            return ResponseEntity.ok(filaments);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/material/{filamentId}")
    public ResponseEntity<List<FilamentReadDto>> getMaterialById(@PathVariable UUID filamentId) {
        try {
            List<FilamentReadDto> filaments = filamentService.getFilamentsByMaterialId(filamentId);
            return ResponseEntity.ok(filaments);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/{materialId}")
    public ResponseEntity<FilamentReadDto> createFilament(
            @PathVariable UUID materialId,
            @RequestBody FilamentCreateDto filamentCreateDto) {
        try {
            filamentService.createFilament(materialId, filamentCreateDto);
            return ResponseEntity.status(201).build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{filamentId}")
    public ResponseEntity<Void> deleteFilament(@PathVariable UUID filamentId) {
        if (filamentService.deleteFilament(filamentId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{filamentId}")
    public ResponseEntity<FilamentReadDto> updateFilament(
            @PathVariable UUID filamentId,
            @RequestBody FilamentCreateDto filamentCreateDto) {
        try {
            filamentService.updateFilament(filamentId, filamentCreateDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/material/{materialId}")
    public ResponseEntity<Void> deleteFilamentsByMaterialId(@PathVariable UUID materialId) {
        filamentService.deleteFilamentsByMaterialId(materialId);
        materialService.deleteSimplifiedMaterial(materialId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/material/{materialId}")
    public ResponseEntity<Void> addSimplifiedMaterial(@PathVariable UUID materialId, @RequestBody SimplifiedMaterial materialDto) {
        System.out.print(materialDto.getType());
        try {
            materialService.createSimplifiedMaterial(materialId, materialDto.getType());
            return ResponseEntity.status(201).build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/material/{materialId}")
    public ResponseEntity<Void> updateSimplifiedMaterial(@PathVariable UUID materialId, @RequestBody SimplifiedMaterial materialDto) {
        System.out.print("dupa");
        try {
            materialService.updateSimplifiedMaterial(materialId, materialDto.getType());
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}
