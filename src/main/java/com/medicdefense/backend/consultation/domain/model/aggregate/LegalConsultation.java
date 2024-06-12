package com.medicdefense.backend.consultation.domain.model.aggregate;

import com.medicdefense.backend.consultation.domain.model.commands.CreateLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class LegalConsultation extends AuditableAbstractAggregateRoot<LegalConsultation> {

    private Date lastConsultation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "profileId", column = @Column(name = "medic_profile_id"))
    })
    private ProfileId medicId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "profileId", column = @Column(name = "lawyer_profile_id"))
    })
    private ProfileId lawyerId;

    private String consultationNotes;  // Nuevo atributo

    public LegalConsultation() {
        this.lastConsultation = new Date(System.currentTimeMillis());
    }

    public LegalConsultation(Long medicId, Long lawyerId, String consultationNotes) {
        this();
        this.lawyerId = new ProfileId(lawyerId);
        this.medicId = new ProfileId(medicId);
        this.consultationNotes = consultationNotes;
        this.lastConsultation = new Date(System.currentTimeMillis());
    }

    public LegalConsultation(CreateLegalConsultationCommand command, String consultationNotes) {
        this();
        this.lawyerId = command.lawyerId();
        this.medicId = command.medicId();
        this.consultationNotes = consultationNotes;
        this.lastConsultation = new Date(System.currentTimeMillis());
    }

    public LegalConsultation updateInformation(Date date, String consultationNotes) {
        this.lastConsultation = new Date(System.currentTimeMillis());
        this.consultationNotes = consultationNotes;
        return this;
    }

    public Long getMedicID() {
        return this.medicId.profileId();
    }

    public Long getLawyerID() {
        return this.lawyerId.profileId();
    }

    public String getConsultationNotes() {
        return this.consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }
}
