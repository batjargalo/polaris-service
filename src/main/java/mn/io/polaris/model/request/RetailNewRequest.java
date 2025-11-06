package mn.io.polaris.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RetailNewRequest {

    @NotEmpty
    private String sexCode;
    @NotNull
    private Integer industryId;
    @NotEmpty
    private String familyName;
    @NotEmpty
    private String familyName2;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String lastName2;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String firstName2;
    @NotEmpty
    private String shortName;
    @NotEmpty
    private String shortName2;
    @NotEmpty
    private String registerCode;
    @NotEmpty
    private String birthDate;
    @NotEmpty
    private String mobile;
    @NotEmpty
    private String email;
    @NotEmpty
    private String maritalStatus;
    @NotEmpty
    private String birthPlaceDetail;
    @NotEmpty
    private String phone;
    @NotEmpty
    @Schema(description = "Харилцагчийн дансны дугаар")
    private String fax;
    @NotNull
    private Integer employmentId;
    @NotNull
    private Integer titleId;
    @NotNull
    private Integer nationalityId;
    @NotNull
    private Integer educationId;
    @NotNull
    private Integer ethnicGroupId;
    @NotNull
    private Integer familyCnt;
    @NotNull
    private Integer workerCnt;

}
