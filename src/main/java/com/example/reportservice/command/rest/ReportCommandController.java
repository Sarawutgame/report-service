package com.example.reportservice.command.rest;

import com.example.reportservice.command.CreateReportCommand;
import com.example.reportservice.command.UpdateReportCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public ReportCommandController(CommandGateway commandGateway){
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
                .banDescription(model.getBanDescription())
                .judge(model.isJudge()).build();
        String result;
        try {
            result = commandGateway.sendAndWait(command);

        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @PutMapping(value = "/update")
    public String updateReport(@RequestBody UpdateReportModel model){
        UpdateReportCommand command = UpdateReportCommand.builder()
                ._id(model.get_id())
                .userReportId(model.getUserReportId())
                .userReportName(model.getUserReportName())
                .typeReport(model.getTypeReport())
                .itemIdReport(model.getItemIdReport())
                .reportHeader(model.getReportHeader())
                .reportDescription(model.getReportDescription())
                .banDescription(model.getBanDescription())
                .judge(model.isJudge()).build();
        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            result = "Update Complete ID: " + idUpdate;
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
