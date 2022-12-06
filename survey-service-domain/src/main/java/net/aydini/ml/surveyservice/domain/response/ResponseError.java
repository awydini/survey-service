package net.aydini.ml.surveyservice.domain.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseError extends ResponseMessage
{
    
    private List<String> errors;


    public ResponseError(String message, Integer code, String ... errors)
    {
        super(message, code);
        this.errors = errors != null ? List.of(errors) : new ArrayList<>();
    }
    
    public ResponseError(String message, Integer code, List<String>  errors)
    {
        super(message, code);
        this.errors = errors;
    }


}
