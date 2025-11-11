package mn.io.polaris.model.polaris.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLoanListProd {

    // private String field;
    // private String operation;
    // private Integer type;
    // private String value;
    public String field;
    public String operation;
    public Integer type;
    public Object value; // Can be String or List<String>
}
