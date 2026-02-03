package mn.io.polaris.model.polaris.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GetLoanBillList {
    @Getter
    private Map<GetLoanBillListParams, Object> params;
    @Getter
    private Map<GetLoanBillListParams2, Object> params2;
    // private List<GetLoanListProd> prod;
    // private List<GetLoanListParams> params;
    private int pageNumber;
    private int pageSize;

    // Constructor to initialize maps
    public GetLoanBillList() {

        this.params = new HashMap<>();
        this.params2 = new HashMap<>();
    }

    // Getter for map

    public Map<GetLoanBillListParams, Object> getParams() {
        return params;
    }

    public Map<GetLoanBillListParams2, Object> getParams2() {
        return params2;
    }

    // Method to add to params
    public void addToParams(GetLoanBillListParams key, Object value) {
        this.params.put(key, value);
    }

    // Method to add to params
    public void addToParams2(GetLoanBillListParams2 key, Object value) {
        this.params2.put(key, value);
    }

    // Setters for pageNumber and pageSize
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
