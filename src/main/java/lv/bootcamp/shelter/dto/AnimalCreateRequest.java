
package lv.bootcamp.shelter.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lv.bootcamp.shelter.model.AnimalType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * JSON request body for creating a new animal via the REST API.
 * Status is not included; all new animals start as AVAILABLE.
 */
public record AnimalCreateRequest(

        @Schema(description = "Name of the animal", example = "Buddy")
        @NotBlank(message = "Name is required")
        String name,

        @Schema(description = "Animal type", example = "DOG")
        @NotNull(message = "Type is required")
        AnimalType type,

        @Schema(description = "Breed of the animal", example = "Labrador")
        String breed,

        @Schema(description = "Age of the animal in years", example = "2", minimum = "0")
        @Min(value = 0, message = "Age cannot be negative")
        Integer age,

        @Schema(description = "Short description of the animal", example = "Friendly and energetic dog")
        String description,

        @Schema(description = "Animal image filename or URL", example = "Luna.jpeg")
        String imageUrl

) {}
