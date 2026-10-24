
package lv.bootcamp.shelter.controller;

import jakarta.validation.Valid;
import lv.bootcamp.shelter.dto.AnimalCreateRequest;
import lv.bootcamp.shelter.dto.AnimalResponse;
import lv.bootcamp.shelter.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

/**
 * REST controller for shelter animal endpoints.
 * Returns JSON — does not render HTML pages.
 */

@RestController
@RequestMapping("/api/animals")
@Tag(name = "Animals", description = "Manage shelter animals and their adoption status")
public class AnimalApiController {

    private final AnimalService animalService;
    public AnimalApiController(AnimalService animalService) {
        this.animalService = animalService;
    }
    @Operation(
            summary = "List all animals",
            description = "Returns all registered shelter animals."
    )
    @ApiResponse(responseCode = "200", description = "Animals returned successfully")
    @GetMapping
    public List<AnimalResponse> findAll() {
        return animalService.findAll();
    }

    @Operation(
            summary = "Find animal by ID",
            description = "Returns a specific shelter animal using its unique ID."
    )
    @ApiResponse(responseCode = "200", description = "Animal found")
    @ApiResponse(responseCode = "404", description = "Animal not found")
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> findById(
            @Parameter(description = "Unique ID of the animal", example = "1")
            @PathVariable Long id) {

        return animalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Lists adopted animals.
     */
    @Operation(
            summary = "List adopted animals",
            description = "Returns all shelter animals that have already been adopted."
    )
    @ApiResponse(responseCode = "200", description = "Adopted animals returned successfully")
    @GetMapping("/adopted")
    public List<AnimalResponse> findAdopted() {
        return animalService.findAdopted();
    }

    /**
     * Creates a new animal.
     */
    @Operation(
            summary = "Create a new animal",
            description = "Registers a new animal in the shelter. New animals have AVAILABLE status."
    )
    @ApiResponse(responseCode = "201", description = "Animal created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid animal data")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse create(@RequestBody @Valid AnimalCreateRequest request) {
        return animalService.create(request);
    }

    /**
     * Adopts an animal
     */
    @Operation(
            summary = "Adopt an animal",
            description = "Marks an available animal as adopted."
    )
    @ApiResponse(responseCode = "200", description = "Animal adopted successfully")
    @ApiResponse(responseCode = "404", description = "Animal not found")
    @ApiResponse(responseCode = "409", description = "Animal has already been adopted")
    @PostMapping("/{id}/adopt")
    public ResponseEntity<AnimalResponse> adopt(
            @Parameter(description = "Unique ID of the animal to adopt", example = "1")
            @PathVariable Long id) {

        return animalService.adopt(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleAlreadyAdopted(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
