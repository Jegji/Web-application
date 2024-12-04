package org.example;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "materials")
public class Material implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "melting_temp", nullable = false)
    private int meltingTemp;

    public Material(String type, int meltingTemp) {
        this.type = type;
        this.meltingTemp = meltingTemp;
    }
}
