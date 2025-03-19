package cs.project.evolt.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewRequest {
        private Long review_id;
        private String comment;
        private LocalDateTime create_date;
        private Long stationId;
        private Long userId;

}

