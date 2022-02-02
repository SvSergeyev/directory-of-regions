package tech.sergeyev.directoryofregions.persistence.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class Region {
    Integer id;
    String name;
    String abbreviation;
}
