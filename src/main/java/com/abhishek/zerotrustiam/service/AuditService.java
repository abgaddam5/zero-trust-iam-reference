package com.abhishek.zerotrustiam.service;

import com.abhishek.zerotrustiam.entity.AuditEvent;
import com.abhishek.zerotrustiam.repository.AuditEventRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private final AuditEventRepository repository;

    public AuditService(AuditEventRepository repository) {
        this.repository = repository;
    }

    public void record(String eventType, String actor, boolean success, String details) {
        repository.save(new AuditEvent(eventType, actor, success, details));
    }
}
