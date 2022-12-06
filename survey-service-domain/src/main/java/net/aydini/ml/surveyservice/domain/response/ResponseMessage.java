package net.aydini.ml.surveyservice.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage
{
    
    private String message;
    
    private Integer code;

    
}
