package net.aydini.ml.surveyservice.domain.exception;

public class SurveyServiceBaseException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = 6653413429882331831L;
    
    public SurveyServiceBaseException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SurveyServiceBaseException(String message)
    {
        super(message);
    }
    
    
    public SurveyServiceBaseException()
    {
        super();
    }
    

}
