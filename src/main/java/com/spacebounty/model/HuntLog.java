package com.spacebounty.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "hunt_logs")
public class HuntLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criminal_id", nullable = false)
    @JsonIgnore
    private WantedCriminal criminal;

    private boolean success;
    private String notes;
    private Instant createdAt;

    protected HuntLog() {
    }

    public HuntLog(WantedCriminal criminal, boolean success, String notes) {
        this.criminal = criminal;
        this.success = success;
        this.notes = notes;
    }

    @PrePersist
    void setCreatedAt() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public Long getId() { return id; }
    public WantedCriminal getCriminal() { return criminal; }
    @JsonProperty("criminal_id")
    public Long getCriminalId() { return criminal.getId(); }
    public boolean isSuccess() { return success; }
    public String getNotes() { return notes; }
    @JsonProperty("created_at")
    public Instant getCreatedAt() { return createdAt; }
    public void setSuccess(boolean success) { this.success = success; }
    public void setNotes(String notes) { this.notes = notes; }
}

