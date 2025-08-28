package com.example.candidature.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.candidature.enums.CandidatureStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidatures")
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;  // comes from Auth service

    private String studentName;

    @Column(nullable = false)
    private Double moyenne;

    @Column(nullable = false)
    private LocalDate dateDebutMobilite;

    @Enumerated(EnumType.STRING)
    private CandidatureStatus status = CandidatureStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offre_id", nullable = false)
    private Offre offre;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Double getMoyenne() { return moyenne; }
    public void setMoyenne(Double moyenne) { this.moyenne = moyenne; }

    public LocalDate getDateDebutMobilite() { return dateDebutMobilite; }
    public void setDateDebutMobilite(LocalDate dateDebutMobilite) { this.dateDebutMobilite = dateDebutMobilite; }

    public CandidatureStatus getStatus() { return status; }
    public void setStatus(CandidatureStatus status) { this.status = status; }

    public Offre getOffre() { return offre; }
    public void setOffre(Offre offre) { this.offre = offre; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}