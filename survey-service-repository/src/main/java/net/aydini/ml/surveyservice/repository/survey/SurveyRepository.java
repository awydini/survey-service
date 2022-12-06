package net.aydini.ml.surveyservice.repository.survey;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {
	
	
	@Query("update SurveyEntity set deletedDate =:deletedDate where id = :id")
	@Modifying
	void deleteSoft(Long id,LocalDateTime deletedDate);
	
	@Query("select count(se) from  SurveyEntity se where deletedDate is null")
	Long countActive();
	
	@Query("select se from  SurveyEntity se where deletedDate is null")
	List<SurveyEntity> findAllActive();
	
	
	@Query("select se from  SurveyEntity se where deletedDate is null")
	Page<SurveyEntity> findAllActive(Pageable pageable);

}
