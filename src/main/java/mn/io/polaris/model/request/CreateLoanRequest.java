package mn.io.polaris.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
// import java.util.Date;

@Data
public class CreateLoanRequest {

    @NotEmpty
    private String custCode;
    @NotEmpty
    private String name;
    @NotEmpty
    private String prodCode;
    @NotEmpty
    private String purpose;
    @NotEmpty
    private String subPurpose;
    @NotEmpty
    private String dailyBasisCode;
    @NotEmpty
    private String curCode;
    @NotEmpty
    private String approvDate;
    @NotEmpty
    private String brchCode;
    @NotEmpty
    private String startDate;
    @NotEmpty
    private String endDate;
    @NotEmpty
    private String repayAcntCode;

    @NotNull
    private BigDecimal approvAmount;
    @NotNull
    private Integer acntManager;
    @NotNull
    private Integer isGetBrchFromOutside;
    @NotNull
    private Integer termLen;

}
