package cs.project.evolt.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RatingDTO {
    @JsonProperty("user_id")
    private long userId;  // เช็คว่า rate ไปหรือยัง -> PUT instead
    private double rating;
}
