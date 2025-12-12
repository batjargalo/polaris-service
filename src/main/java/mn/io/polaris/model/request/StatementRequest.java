package mn.io.polaris.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatementRequest {

    @NotEmpty
    private String acntCode;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    @NotNull
    private int startPagingPosition;

    @NotNull
    private int pageRowCount;

}
