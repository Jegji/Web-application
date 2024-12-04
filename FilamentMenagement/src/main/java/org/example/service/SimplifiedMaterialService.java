package org.example.service;

import org.example.Filament;
import org.example.SimplifiedMaterial;
import org.example.repository.SimplifiedMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SimplifiedMaterialService {
    private final SimplifiedMaterialRepository simplifiedMaterialRepository;

    @Autowired
    public SimplifiedMaterialService(SimplifiedMaterialRepository repository) {
        this.simplifiedMaterialRepository = repository;
    }

    public void createSimplifiedMaterial(UUID id, String name) {
        SimplifiedMaterial material = new SimplifiedMaterial(id,name);
        simplifiedMaterialRepository.save(material);
    }

    public boolean deleteSimplifiedMaterial(UUID id) {
        if (simplifiedMaterialRepository.existsById(id)) {
            simplifiedMaterialRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public SimplifiedMaterial getSimplifiedMaterialById(UUID id) {
        return simplifiedMaterialRepository.findById(id).orElse(null);
    }

    public void updateSimplifiedMaterial(UUID id, String name) {
        SimplifiedMaterial material = simplifiedMaterialRepository.findById(id).orElse(null);
        if (material != null) {
            material.setType(name);
            simplifiedMaterialRepository.save(material);
        }
    }

}
