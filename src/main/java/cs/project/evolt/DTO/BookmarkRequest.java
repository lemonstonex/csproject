package cs.project.evolt.DTO;

import lombok.Data;

@Data
public class BookmarkRequest {
    private long bookmark_id;
    private long station_id;
    private String station_name;

    private long user_id;

    public BookmarkRequest(long bookmark_id,long station_id, String station_name, long user_id) {
        this.bookmark_id = bookmark_id;
        this.station_id = station_id;
        this.station_name = station_name;
        this.user_id = user_id;
    }

    public long getBookmark_id() {
        return bookmark_id;
    }

    public void setBookmark_id(long bookmark_id) {
        this.bookmark_id = bookmark_id;
    }

    public long getStation_id() {
        return station_id;
    }

    public void setStation_id(long station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
