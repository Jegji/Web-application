package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Filament;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilamentReadDto {
    private UUID id;
    private String brand;
    private String color;
    private int weight;
    private int diameter;
    private String materialType;
    public static FilamentReadDto from(Filament filament) {
        FilamentReadDto dto = new FilamentReadDto();
        dto.setId(filament.getId());
        dto.setBrand(filament.getBrand());
        dto.setColor(filament.getColor());
        dto.setWeight(filament.getWeight());
        dto.setDiameter(filament.getDiameter());
        dto.setMaterialType(filament.getMaterial().getType());
        return dto;
    }
}

