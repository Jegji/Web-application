package org.example.repository;

import org.example.Filament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilamentRepository extends JpaRepository<Filament, UUID> {
    List<Filament> findByMaterial_Type(String type);
    List<Filament> findAllByMaterial_Id(UUID materialId);

    List<Filament> findByMaterial_Id(UUID materialId);
}