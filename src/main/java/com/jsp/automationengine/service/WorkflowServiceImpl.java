package com.jsp.automationengine.service;

import com.jsp.automationengine.DTO.WorkflowDTO;
import com.jsp.automationengine.model.WorkflowModel;
import com.jsp.automationengine.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Date;

public class WorkflowServiceImpl implements WorkflowService {
    @Autowired
    private WorkflowRepository repository;

    @Override
    public WorkflowModel createWorkflow(WorkflowDTO dto){

        //Created new object for workflow model
        WorkflowModel wf = new WorkflowModel();

        //Primary Key
        wf.setAltkey(BigInteger.valueOf(System.currentTimeMillis()));

        // Mapping from DTO
        wf.setWorkflowCode(dto.getWorkflowCode());
        wf.setWorkflowName(dto.getWorkflowName());
        wf.setTenantId(dto.getTenantId());
        wf.setUniqueField(dto.getUniqueField());
        wf.setEntityCode(dto.getEntityCode());
        wf.setSourceData(dto.getSourceData());

        // default values
        wf.setWorkflowVersion(0);
        wf.setStatusFlag("Draft");

        // WFID = wfcode_version
        wf.setWorkflowId(dto.getWorkflowCode() + "_0");

        wf.setCreatedDate(new Date());

        return repository.save(wf);
    }
}
