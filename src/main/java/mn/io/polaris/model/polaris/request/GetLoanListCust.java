package mn.io.polaris.model.polaris.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLoanListCust {

    // private String field;
    // private String operation;
    // private Integer type;
    // private String value;
    public String field;
    public String operation;
    public Integer type;
    public Object value; // Can be String or List<String>

    // For underscore-prefixed fields

    // Getters and setters (or use Lombok @Data)
    // Add setters for convenience
    public void setField(String field) {
        this.field = field;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    // // Override toString for printing
    // @Override
    // public String toString() {
    // return "GetLoanListCust{field='" + field + "', operation='" + operation + "',
    // type=" + type + ", value=" + value
    // + "}";
    // }

}
