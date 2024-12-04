package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.Material;

import java.util.UUID;
@Getter
@Setter
@Builder
public class MaterialSummeryDto {
    private UUID id;
    private String type;

    public static MaterialSummeryDto from(Material material) {
        return MaterialSummeryDto.builder()
            .id(material.getId())
            .type(material.getType())
            .build();
    }
}
