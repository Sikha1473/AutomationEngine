package com.jsp.automationengine.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Data
@Table(name="workflow_master")
public class WorkflowModel {
    @Id
    @Column(name = "alt_key")
    private BigInteger altkey;

    @Column(name = "workflow_version")
    private Integer workflowVersion;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "workflow_id")
    private String workflowId;

    @Column(name = "workflow_code")
    private String workflowCode;

    @Column(name = "workflow_name")
    private String workflowName;

    @Column(name = "status_flag")
    private String statusFlag = "Draft";

    @Column(name = "unique_field")
    private String uniqueField;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String sourceData;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "modifyBy")
    private String modifyBy;

    @Column(name = "entity_code")
    private String entityCode;

}
