package mn.io.polaris.model.polaris.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GetLoanList {
    @Getter
    private Map<GetLoanListCust, Object> map;
    @Getter
    private Map<GetLoanListCust, Object> map2;
    @Getter
    private Map<GetLoanListParams, Object> params;
    // private List<GetLoanListProd> prod;
    // private List<GetLoanListParams> params;
    private int pageNumber;
    private int pageSize;

    // Constructor to initialize maps
    public GetLoanList() {
        this.map = new HashMap<>();
        this.map2 = new HashMap<>();
        this.params = new HashMap<>();
    }

    // Getter for map
    public Map<GetLoanListCust, Object> getMap() {
        return map;
    }

    public Map<GetLoanListCust, Object> getMap2() {
        return map2;
    }

    public Map<GetLoanListParams, Object> getParams() {
        return params;
    }

    // Method to add to map
    public void addToMap(GetLoanListCust key, Object value) {
        this.map.put(key, value);
    }

    // Method to add to params
    public void addToParams(GetLoanListParams key, Object value) {
        this.params.put(key, value);
    }

    // Setters for pageNumber and pageSize
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
