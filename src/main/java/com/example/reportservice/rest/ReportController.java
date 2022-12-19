package com.example.reportservice.rest;

import com.example.reportservice.command.CreateReportCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final CommandGateway commandGateway;

    @Autowired
    public ReportController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createReport(@RequestBody CreateReportModel model){
        CreateReportCommand command = CreateReportCommand.builder()
                ._id(UUID.randomUUID().toString())
                .userReportId(model.getUserReportId())
                .userReportName(model.getUserReportName())
                .typeReport(model.getTypeReport())
                .itemIdReport(model.getItemIdReport())
                .reportHeader(model.getReportHeader())
                .reportDescription(model.getReportDescription())
                .judge(model.isJudge()).build();
        String result;
        try {
            result = commandGateway.sendAndWait(command);

        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
