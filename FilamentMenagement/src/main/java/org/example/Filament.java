package org.example;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.FilamentCollectionDto;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "filaments")
public class Filament implements Serializable {
    @Id
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "diameter", nullable = false)
    private int diameter;

    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SimplifiedMaterial material;

}
