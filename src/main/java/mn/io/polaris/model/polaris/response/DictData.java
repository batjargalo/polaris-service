package mn.io.polaris.model.polaris.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictData {

    private Integer orderNo;
    private String extra;
    private String name;
    private String id;

}
