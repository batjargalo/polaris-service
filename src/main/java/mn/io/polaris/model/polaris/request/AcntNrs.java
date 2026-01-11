package mn.io.polaris.model.polaris.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcntNrs {

    private Date startDate;
    private BigDecimal calcAmt;
    private String payType;
    private String payFreq;
    private Integer payDay1;
    private String holidayOption;
    private Integer shiftPartialPay;
    private Integer shiftType;
    private Integer termFreeTimes;
    private String intTypeCode;
    private Date endDate;

}
