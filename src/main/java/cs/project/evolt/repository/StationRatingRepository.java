package cs.project.evolt.repository;

import cs.project.evolt.model.StationRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRatingRepository extends JpaRepository<StationRating, Long> {
    @Query("SELECT COALESCE(AVG(s.rating), 0) FROM StationRating s WHERE s.station.stationId = :stationId")
    Double findAverageRatingByStationId(@Param("stationId") long stationId);

    /***
     *
     * coalesce คือคืนค่าแรกที่ไม่ใช่ null, ถ้าเป็น null หมดจะคืน 0
     * avg(s.rating) คือคิดค่าเฉลี่ยของ rating ของ stationId ที่กรอกไป
     *
     */

    int countByStation_StationId(long stationId);

    Optional<StationRating> findByStation_StationIdAndUser_UserId(long stationId, long userId);

}



