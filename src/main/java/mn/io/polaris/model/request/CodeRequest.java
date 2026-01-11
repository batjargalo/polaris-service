package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CodeRequest {

    @NotEmpty
    private String tableName;

}
