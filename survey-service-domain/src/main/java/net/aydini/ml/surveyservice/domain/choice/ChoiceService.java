package net.aydini.ml.surveyservice.domain.choice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChoiceService {
	
	
	Choice findById(Long id);
	
	
	List<Choice> findAll();
	
	
	Page<Choice> findAll(Pageable page);
	
	
	void create(Choice choice);
	
	
	
	void delete(Long id);
	
	Page<Choice> findAllChoices(Long questionId,Pageable page);

}
