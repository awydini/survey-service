package net.aydini.ml.surveyservice.domain.question;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;
import net.aydini.ml.surveyservice.domain.choice.Choice;

@Value
@Builder
public class Question {

	private Long id;
	
	@NotNull
	private String title;

	
	@NotNull
	private String description;

	private boolean showDescription;

	private boolean enabled;
	@NotNull
	private Long surveyId;
	
	private Set<Choice> choices;
}
