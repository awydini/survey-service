package net.aydini.ml.surveyservice.repository.question;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aydini.ml.surveyservice.repository.survey.SurveyEntity;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour</a>
 *
 */


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "QUESTIONS")
public class QuestionEntity {

	
    @Id
    @GeneratedValue
    private Long id;
    
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Version
    private int version;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SURVEY_ID")
    private SurveyEntity survey;
    
    private String title;
    
    
    private String description;
    
    private boolean showDescription;
    
    private boolean enabled;
    
    private LocalDateTime deletedDate;
    
}
