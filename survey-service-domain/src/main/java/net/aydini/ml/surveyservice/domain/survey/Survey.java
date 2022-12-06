package net.aydini.ml.surveyservice.domain.survey;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;
import net.aydini.ml.surveyservice.domain.question.Question;

@Value
@Builder
public class Survey {
	
	private Long id;

	@NotNull
	private String title;

	@NotNull
	private String description;

	private Set<Question> questions;
}
