package com.jsp.automationengine.service;

import com.jsp.automationengine.DTO.WorkflowDTO;
import com.jsp.automationengine.model.WorkflowModel;
import com.jsp.automationengine.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private WorkflowRepository repository;

    // Create/update Draft
    @Override
    public WorkflowModel createWorkflow(WorkflowDTO dto) {

        WorkflowModel draft = repository
                .findByWorkflowCodeAndTenantIdAndStatusFlag(
                        dto.getWorkflowCode(),
                        dto.getTenantId(),
                        "DRAFT"
                );

        if (draft != null) {
            // Updated existing draft here
            draft.setModifiedDate(new Date());
            mapFields(draft, dto);
            return repository.save(draft);
        }

        // Created new draft
        WorkflowModel latest = repository
                .findTopByWorkflowCodeAndTenantIdOrderByWorkflowVersionDesc(
                        dto.getWorkflowCode(),
                        dto.getTenantId()
                );

        int version = (latest != null) ? latest.getWorkflowVersion() + 1 : 0;

        WorkflowModel wf = new WorkflowModel();

        wf.setWorkflowCode(dto.getWorkflowCode());
        wf.setTenantId(dto.getTenantId());
        wf.setWorkflowVersion(version);
        wf.setWorkflowId(dto.getWorkflowCode() + "_" + version);
        wf.setStatusFlag("DRAFT");

        mapFields(wf, dto);

        return repository.save(wf);
    }

    // Activating Workflow
    @Override
    public WorkflowModel activateWorkflow(String wfCode, String tenantId) {

        WorkflowModel draft = repository
                .findByWorkflowCodeAndTenantIdAndStatusFlag(
                        wfCode, tenantId, "DRAFT"
                );

        if (draft == null) {
            throw new RuntimeException("No DRAFT found to activate");
        }

        //  Inactivating all ACTIVE here
        repository.inactivateAllActive(wfCode, tenantId);

        // To Activate draft
        draft.setStatusFlag("ACTIVE");

        // For Increment version
        int newVersion = draft.getWorkflowVersion() + 1;
        draft.setWorkflowVersion(newVersion);

        // To Update workflowId
        draft.setWorkflowId(wfCode + "_" + newVersion);

        draft.setModifiedDate(new Date());

        return repository.save(draft);
    }

    // Logic To Inactivate
    @Override
    public void inactivateWorkflow(String wfCode, String tenantId) {
        repository.inactivateAllActive(wfCode, tenantId);
    }

    // TO GET WORKFLOW
    @Override
    public WorkflowModel getWorkflowByCode(String wfCode, String tenantId){

        WorkflowModel active = repository
                .findByWorkflowCodeAndTenantIdAndStatusFlag(
                        wfCode, tenantId, "ACTIVE"
                );

        if (active != null) {
            return active;
        }

        return repository
                .findByWorkflowCodeAndTenantIdAndStatusFlag(
                        wfCode, tenantId, "DRAFT"
                );
    }

    private void mapFields(WorkflowModel wf, WorkflowDTO dto) {
        wf.setWorkflowName(dto.getWorkflowName());
        wf.setTenantId(dto.getTenantId());
        wf.setUniqueField(dto.getUniqueField());
        wf.setEntityCode(dto.getEntityCode());
        wf.setSourceData(dto.getSourceData());
    }
}
