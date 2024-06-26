package com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LegalIssueRepository extends JpaRepository<LegalIssue, Long> {
    Optional<LegalIssue> findByTitle(String title);
    boolean existsByTitle(String title);
    List<LegalIssue> findAllByLegalConsultation_Id(Long legalConsultationId);
    boolean existsByTitleAndIdIsNot(String title, Long id);
}
