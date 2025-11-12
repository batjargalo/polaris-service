package mn.io.polaris.model.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLARepaymentRes {

    private String acntCode;
    private String startDate;
    private BigDecimal calcAmt;
    private Integer payType;
    private String payFreq;
    private Integer payMonth;
    private Integer payDay1;
    private Integer payDay2;
    private Integer holidayOption;
    private Integer shiftPartialPay;
    private Integer shiftType;
    private Integer termFreeTimes;
    private String intTypeCode;
    private String endDate;
    private String advDate;
    private String description;
    private String escapeMonths;
    private String listNrs;
}
