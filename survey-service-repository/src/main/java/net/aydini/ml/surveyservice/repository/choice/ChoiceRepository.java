package net.aydini.ml.surveyservice.repository.choice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChoiceRepository extends JpaRepository<ChoiceEntity, Long> {
	
	
	@Query("update ChoiceEntity set deletedDate =:deletedDate where id = :id")
	@Modifying
	void deleteSoft(Long id,LocalDateTime deletedDate);
	
	@Query("select count(se) from  ChoiceEntity se where deletedDate is null")
	Long countActive();
	
	@Query("select se from  ChoiceEntity se where deletedDate is null")
	List<ChoiceEntity> findAllActive();
	
	
	@Query("select se from  ChoiceEntity se where deletedDate is null")
	Page<ChoiceEntity> findAllActive(Pageable pageable);
	
	
	
	@Query("select se from  ChoiceEntity se where deletedDate is null  and se.question.id = :surveyId")
	Page<ChoiceEntity> findQuestionChoicess(Long surveyId, Pageable pageable);
	
	
	@Query("select count(se) from  ChoiceEntity se where deletedDate is null and se.question.id = :id")
	Long countQuestionChoicess(Long id);

}
