
package lv.bootcamp.shelter.dto;

import lv.bootcamp.shelter.model.AnimalStatus;
import lv.bootcamp.shelter.model.AnimalType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response body for a single animal returned by the API.
 * {@code adoptionNote} is only populated for ADMIN callers (see AnimalService#toResponse) —
 * everyone else just sees the plain {@code status}.
 */
public record AnimalResponse(

        @Schema(description = "Unique animal identifier", example = "1")
        Long id,

        @Schema(description = "Name of the animal", example = "Buddy")
        String name,

        @Schema(description = "Animal type", example = "DOG")
        AnimalType type,

        @Schema(description = "Breed of the animal", example = "Labrador")
        String breed,

        @Schema(description = "Age in years", example = "2")
        Integer age,

        @Schema(description = "Description of the animal")
        String description,

        @Schema(description = "Current adoption status", example = "AVAILABLE")
        AnimalStatus status,

        @Schema(description = "Animal image URL")
        String imageUrl,

        @Schema(description = "Adoption date information, if the animal has been adopted",
                example = "adopted on 2026-10-24",
                nullable = true)
        String adoptionNote

) {}
