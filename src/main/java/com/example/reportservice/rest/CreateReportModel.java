package com.example.reportservice.rest;

import lombok.Data;

@Data
public class CreateReportModel {
    private String userReportId;
    private String userReportName;
    private String typeReport;
    private String itemIdReport;
    private String reportHeader;
    private String reportDescription;
    private boolean judge;
}
