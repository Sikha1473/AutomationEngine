package com.jsp.automationengine.service;


import com.jsp.automationengine.DTO.WorkflowDTO;
import com.jsp.automationengine.model.WorkflowModel;

public interface WorkflowService {
    
    WorkflowModel createWorkflow(WorkflowDTO dto);
}
