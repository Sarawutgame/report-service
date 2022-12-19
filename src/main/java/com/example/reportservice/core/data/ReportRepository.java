package com.example.reportservice.core.data;

import com.example.reportservice.core.ReportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ReportRepository extends MongoRepository<ReportEntity, String> {
    @Query(value = "{_id: ?0}")
    ReportEntity findByReportId(String reportId);
}
