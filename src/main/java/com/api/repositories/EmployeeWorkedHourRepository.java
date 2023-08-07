package com.api.repositories;

import com.api.models.EmployeeWorkedHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeWorkedHourRepository extends JpaRepository<EmployeeWorkedHour, Long> {
    @Query("SELECT ewh.workedDate FROM EmployeeWorkedHour ewh WHERE ewh.employeeId = :employeeId AND ewh.workedDate = :workedDate")
    List<EmployeeWorkedHour> getWorkedHourByDateAndEmployeeId(Long employeeId, String workedDate);

    @Query("SELECT SUM(ewh.workedHours) FROM EmployeeWorkedHour ewh WHERE ewh.employeeId = :employeeId AND ewh.workedDate BETWEEN :startDate AND :endDate")
    Integer getTotalWorkedHoursByDateRange(Long employeeId, String startDate, String endDate);
}
