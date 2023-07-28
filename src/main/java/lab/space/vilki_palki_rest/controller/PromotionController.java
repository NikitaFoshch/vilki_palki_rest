package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("promotions")
@AllArgsConstructor
@Tag(name = "Promotions", description = "Operations related to Promotions")
public class PromotionController {
    private final PromotionService promotionService;

    @Operation(summary = "Get all promotions", description = "This controller returns a total of 10 objects " +
            "according to pagination (first page = 0)")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("get-all-promotions/{page}")
    public ResponseEntity<?> getAllPromotions(@PathVariable Integer page) {
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        }
        return ResponseEntity.ok(promotionService.getAllPromotions(page));
    }

    @Operation(summary = "Get promotion by id", description = "Enter your value")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized"),
            @ApiResponse(responseCode = "404",description = "Not found")
    })
    @GetMapping("get-promotion/{id}")
    public ResponseEntity<?> getPromotion(@PathVariable Long id) {
        if (id < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Promotion Id must be >=1");
        }
        try {
            return ResponseEntity.ok(promotionService.getPromotionById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Promotion not found with id " + id);
        }
    }
}
