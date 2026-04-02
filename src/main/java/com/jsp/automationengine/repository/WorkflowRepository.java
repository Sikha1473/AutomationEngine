package com.jsp.automationengine.repository;

import com.jsp.automationengine.model.WorkflowModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
public interface WorkflowRepository extends JpaRepository<WorkflowModel, Long> {

    WorkflowModel findByWorkflowCodeAndTenantIdAndStatusFlag(
            String workflowCode,
            String tenantId,
            String statusFlag
    );

    WorkflowModel findTopByWorkflowCodeAndTenantIdOrderByWorkflowVersionDesc(
            String workflowCode,
            String tenantId
    );

    @Modifying
    @Transactional
    @Query("UPDATE WorkflowModel w SET w.statusFlag='INACTIVE' " +
            "WHERE w.workflowCode=?1 AND w.tenantId=?2 AND w.statusFlag='ACTIVE'")
    void inactivateAllActive(String workflowCode, String tenantId);
}
