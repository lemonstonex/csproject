package cs.project.evolt.DTO;

import java.time.LocalDateTime;


public class ReviewRequest {
        private Long review_id;
        private String comment;
        private LocalDateTime create_date;
        private Long stationId;
        private Long userId;

    public ReviewRequest(Long review_id, String comment, LocalDateTime create_date, Long stationId, Long userId) {
        this.review_id = review_id;
        this.comment = comment;
        this.create_date = create_date;
        this.stationId = stationId;
        this.userId = userId;
    }

    public Long getReview_id() {
        return review_id;
    }

    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

