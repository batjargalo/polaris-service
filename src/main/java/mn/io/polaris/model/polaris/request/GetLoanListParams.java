package mn.io.polaris.model.polaris.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLoanListParams {
    public String _iField;
    public String _iOperation;
    public Integer _iType;
    public List<String> _inValues;

}