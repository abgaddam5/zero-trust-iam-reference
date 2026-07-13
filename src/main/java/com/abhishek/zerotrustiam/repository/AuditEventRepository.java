package com.abhishek.zerotrustiam.repository;

import com.abhishek.zerotrustiam.entity.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditEventRepository extends JpaRepository<AuditEvent, Long> {}
