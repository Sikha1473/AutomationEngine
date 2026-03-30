package com.jsp.automationengine.controller;

import com.jsp.automationengine.DTO.WorkflowDTO;
import com.jsp.automationengine.model.WorkflowModel;
import com.jsp.automationengine.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {
    @Autowired
    private WorkflowService workflowService;

    @PostMapping("/create")
    public WorkflowModel create(@RequestBody WorkflowDTO dto){
        return workflowService.createWorkflow(dto);
    }
}
