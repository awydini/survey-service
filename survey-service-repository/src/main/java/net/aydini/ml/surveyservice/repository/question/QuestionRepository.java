package net.aydini.ml.surveyservice.repository.question;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	
	
	
	@Query("update QuestionEntity set deletedDate =:deletedDate where id = :id and enabled = true")
	@Modifying
	void deleteSoft(Long id,LocalDateTime deletedDate);
	
	@Query("select count(se) from  QuestionEntity se where deletedDate is null and enabled = true")
	Long countActive();
	
	@Query("select se from  QuestionEntity se where deletedDate is null and enabled = true")
	List<QuestionEntity> findAllActive();
	
	
	@Query("select se from  QuestionEntity se where deletedDate is null and enabled = true")
	Page<QuestionEntity> findAllActive(Pageable pageable);
	
	
	@Query("select se from  QuestionEntity se where deletedDate is null and enabled = true and se.survey.id = :id")
	Page<QuestionEntity> findSurveyQuestions(Long id, Pageable pageable);
	
	
	@Query("select count(se) from  QuestionEntity se where deletedDate is null and enabled = true and se.survey.id = :id")
	Long countSurveyQuestions(Long id);

}
