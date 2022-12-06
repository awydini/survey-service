package net.aydini.ml.surveyservice.domain.choice;


import lombok.Builder;
import lombok.Value;


/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour</a>
 *
 */
@Value
@Builder
public class Choice {

	private Long id;
	
	private String title;
	
	private Long questionId;

}
