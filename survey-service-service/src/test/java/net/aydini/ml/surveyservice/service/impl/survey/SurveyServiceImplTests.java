package net.aydini.ml.surveyservice.service.impl.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.aydini.ml.surveyservice.domain.exception.NotFoundException;
import net.aydini.ml.surveyservice.domain.survey.Survey;
import net.aydini.ml.surveyservice.domain.survey.SurveyService;
import net.aydini.ml.surveyservice.repository.survey.SurveyEntity;
import net.aydini.ml.surveyservice.repository.survey.SurveyRepository;
import net.aydini.ml.surveyservice.service.factory.survey.SurveyFactory;
import net.aydini.ml.surveyservice.service.factory.survey.SurveyFactoryImpl;


@ExtendWith(MockitoExtension.class) 
public class SurveyServiceImplTests  {
	
	
	@Mock
	private SurveyRepository surveyRepository;
	
    private SurveyFactory surveyFactory = new SurveyFactoryImpl();
    
    private SurveyService surveyService;
    
    
    @BeforeEach
    public void init()
    {
    	surveyService = new SurveyServiceImpl(surveyRepository,surveyFactory);
    }
    
    
    
    
    @Test
    @DisplayName("tests findById method and finds the result")
    public void findById_Found()
    {
    	SurveyEntity entity = SurveyEntity.builder().id(1L).title("title").description("description").build();
    	when(surveyRepository.findById(1L)).thenReturn(Optional.of(entity));
    	
    	Survey survey = surveyService.findById(1l);
    	
    	assertNotNull(survey);
    	assertEquals(survey.getId(), entity.getId());
    	assertEquals(survey.getTitle(), entity.getTitle());
    	
    }
    
    
    @Test
    @DisplayName("tests findById method and does not find the result")
    public void findById_NotFound_shouldThrowNotfoundException()
    {
    	when(surveyRepository.findById(anyLong())).thenReturn(Optional.empty());
    	
    	assertThrows(NotFoundException.class, () ->surveyService.findById(1l));
    }


    
    
    
    @Test
    @DisplayName("tests findAll method and finds the result")
    public void findAll_Found()
    {
    	SurveyEntity entity = SurveyEntity.builder().id(1L).title("title").description("description").build();
    	when(surveyRepository.countActive()).thenReturn(1l);
    	when(surveyRepository.findAllActive()).thenReturn(Arrays.asList(entity));
    	
    	List<Survey> surveys = surveyService.findAll();
    	
    	assertNotNull(surveys);
    	assertEquals(surveys.size(), 1);
    	assertEquals(surveys.get(0).getTitle(), entity.getTitle());
    	
    }
    
    
    
    @Test
    @DisplayName("tests findAll method and does not find any  result")
    public void findAll_NotFound()
    {
    	when(surveyRepository.countActive()).thenReturn(0L);
    	assertThrows(NotFoundException.class, () ->surveyService.findAll());
    }

}
