package net.aydini.ml.surveyservice.domain.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */


@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseData<T> extends ResponseMessage
{

    private T data;


    public ResponseData()
    {
        super();
    }

    public ResponseData(String message, Integer code, T data)
    {
        super(message, code);
        this.data = data;
    }


}
