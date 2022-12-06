package net.aydini.ml.surveyservice.domain.exception;

public class NotFoundException extends SurveyServiceBaseException
{

    /**
     * 
     */
    private static final long serialVersionUID = 6653413429882331831L;
    
    
    
    public static final String DATA_WITH_ID_NOT_FOUND= "DATA WITH ID NOT FOUND. ID = ";
    
    public static final String NOT_FOUND= " NOT FOUND";

    public NotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NotFoundException(String message)
    {
        super(message);
    }
    
    

}
