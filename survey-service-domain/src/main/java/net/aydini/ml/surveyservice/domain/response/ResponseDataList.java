package net.aydini.ml.surveyservice.domain.response;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseDataList<T> extends ResponseMessage
{

    private List<T> data;
    
    private Long totalElements;
    
    private Integer totalPages;
    
    private Integer numberOfElements;
    
    
    public ResponseDataList() {}

    public ResponseDataList(String message, Integer code, Page<T> page)
    {
        super(message, code);
        this.data = page != null ? page.getContent() : new ArrayList<>();
        this.numberOfElements= page != null ? page.getNumberOfElements() : 0;
        this.totalPages = page != null ? page.getTotalPages() : 0;
        this.totalElements = page != null ? page.getTotalElements() : 0;
    }


}
