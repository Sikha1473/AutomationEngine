package com.jsp.automationengine.repository;

import com.jsp.automationengine.model.WorkflowModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface WorkflowRepository extends JpaRepository<WorkflowModel, BigInteger> {
}
