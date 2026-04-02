package com.jsp.automationengine.DTO;

import lombok.Data;

import java.math.BigInteger;

@Data
public class WorkflowDTO {

    private BigInteger altkey;
    private String workflowCode;
    private String sourceData;
    private String tenantId;
    private String workflowName;
    private String uniqueField;
    private String entityCode;
}




