package mn.io.polaris.model.polaris.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanExtendPResponse {

    // private String txnPreview;
    // private Integer isSupervisor;
    // private Integer isPreviewFee;
    // private String feesPreview;
    // private Integer txnJrno;
    private Map<String, List<Object>> data; // For {"haha": [], "hoho": []}

    // Getters and setters
    public Map<String, List<Object>> getData() {
        return data;
    }

    public void setData(Map<String, List<Object>> data) {
        this.data = data;
    }

}
