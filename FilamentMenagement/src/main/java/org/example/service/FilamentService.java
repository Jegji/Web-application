package org.example.service;

import org.example.Filament;
import org.example.SimplifiedMaterial;
import org.example.dto.FilamentCreateDto;
import org.example.dto.FilamentReadDto;
import org.example.repository.FilamentRepository;
import org.example.repository.SimplifiedMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilamentService {
    private final FilamentRepository filamentRepository;
    private final SimplifiedMaterialRepository materialRepository;
    @Autowired
    public FilamentService(FilamentRepository filamentRepository,SimplifiedMaterialRepository materialRepository) {
        this.filamentRepository = filamentRepository;
        this.materialRepository = materialRepository;
    }
    public List<Filament> getFilamentsByMaterialType(String type) {
        return filamentRepository.findByMaterial_Type(type);
    }

    public List<FilamentReadDto> getFilamentsByMaterialId(UUID id) {
        return filamentRepository.findByMaterial_Id(id).stream()
                .map(FilamentReadDto::from)
                .collect(Collectors.toList());
    }

    public Filament saveFilament(Filament filament) {
        return filamentRepository.save(filament);
    }
    public FilamentReadDto getFilamentById(UUID id) {
        Filament filament = filamentRepository.findById(id).orElse(null);
        return filament != null ? FilamentReadDto.from(filament) : null;
    }

    public boolean deleteFilament(UUID id) {
        if (filamentRepository.existsById(id)) {
            filamentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public void createFilament(UUID materialId, FilamentCreateDto filamentCreateDto) {
        SimplifiedMaterial material = materialRepository.findById(materialId)
                .orElseThrow(() -> new IllegalArgumentException("Material not found with id: " + materialId));

        Filament filament = new Filament();
        filament.setBrand(filamentCreateDto.getBrand());
        filament.setColor(filamentCreateDto.getColor());
        filament.setWeight(filamentCreateDto.getWeight());
        filament.setDiameter(filamentCreateDto.getDiameter());
        filament.setMaterial(material);
        filament.setId(UUID.randomUUID());

        filamentRepository.save(filament);

    }
    public List<FilamentReadDto> getAllFilaments() {
        return filamentRepository.findAll().stream()
                .map(FilamentReadDto::from)
                .collect(Collectors.toList());
    }

    public void updateFilament(UUID id, FilamentCreateDto filamentCreateDto) {
        Filament filament = filamentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filament not found with id: " + id));
        filament.setBrand(filamentCreateDto.getBrand());
        filament.setColor(filamentCreateDto.getColor());
        filament.setWeight(filamentCreateDto.getWeight());
        filament.setDiameter(filamentCreateDto.getDiameter());
        filamentRepository.save(filament);
    }

    public void deleteFilamentsByMaterialId(UUID materialId) {
        List<Filament> filaments = filamentRepository.findByMaterial_Id(materialId);
        filamentRepository.deleteAll(filaments);
    }
}

