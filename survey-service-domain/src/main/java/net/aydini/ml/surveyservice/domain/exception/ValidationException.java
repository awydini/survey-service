package net.aydini.ml.surveyservice.domain.exception;

public class ValidationException extends SurveyServiceBaseException
{

    /**
     * 
     */
    private static final long serialVersionUID = 6653413429882331831L;

    public ValidationException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ValidationException(String message)
    {
        super(message);
    }

	public ValidationException() {
		super();
	}

}
