package org.example.repository;


import org.example.SimplifiedMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimplifiedMaterialRepository extends JpaRepository<SimplifiedMaterial, UUID> {
}
