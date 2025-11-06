package mn.io.polaris.model.polaris.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterResponse {

    private List<DictData> dictData;
    private String name;

}
