package com.abhishek.zerotrustiam.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "audit_events")
public class AuditEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String eventType;

    @Column(length = 120)
    private String actor;

    @Column(nullable = false)
    private boolean success;

    @Column(length = 500)
    private String details;

    @Column(nullable = false)
    private Instant occurredAt = Instant.now();

    protected AuditEvent() {}

    public AuditEvent(String eventType, String actor, boolean success, String details) {
        this.eventType = eventType;
        this.actor = actor;
        this.success = success;
        this.details = details;
    }
}
