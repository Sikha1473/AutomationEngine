package com.jsp.automationengine.controller;

import com.jsp.automationengine.DTO.WorkflowDTO;
import com.jsp.automationengine.model.WorkflowModel;
import com.jsp.automationengine.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    @Autowired
    private WorkflowService service;

    // ✅ CREATE / UPDATE DRAFT
    @PostMapping
    public WorkflowModel create(@RequestBody WorkflowDTO dto) {
        return service.createWorkflow(dto);
    }

    // ✅ ACTIVATE
    @PutMapping("/activate")
    public WorkflowModel activate(
            @RequestParam String wfCode,
            @RequestParam String tenantId) {

        return service.activateWorkflow(wfCode, tenantId);
    }

    // ✅ INACTIVATE
    @PutMapping("/inactivate")
    public String inactivate(
            @RequestParam String wfCode,
            @RequestParam String tenantId) {

        service.inactivateWorkflow(wfCode, tenantId);
        return "Workflow inactivated";
    }

    // ✅ GET
    @GetMapping("/{wfCode}")
    public WorkflowModel get(
            @PathVariable String wfCode,
            @RequestParam String tenantId) {

        return service.getWorkflowByCode(wfCode, tenantId);
    }
}
