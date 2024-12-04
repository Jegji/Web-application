package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Material;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialReadDto {
    private UUID id;
    private String type;
    private int meltingTemp;

    public static MaterialReadDto from(Material material) {
        MaterialReadDto dto = new MaterialReadDto();
        dto.setId(material.getId());
        dto.setType(material.getType());
        dto.setMeltingTemp(material.getMeltingTemp());
        return dto;
    }
}

