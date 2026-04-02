package com.jsp.automationengine.service;


import com.jsp.automationengine.DTO.WorkflowDTO;
import com.jsp.automationengine.model.WorkflowModel;

public interface WorkflowService {


        WorkflowModel createWorkflow(WorkflowDTO dto);

        WorkflowModel activateWorkflow(String wfCode, String tenantId);

        void inactivateWorkflow(String wfCode, String tenantId);

      WorkflowModel getWorkflowByCode(String wfCode, String tenantId);

}
