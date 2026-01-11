package mn.io.polaris.model.polaris.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mn.io.polaris.helper.Utils;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditRepaymentRequest {

    private String acntCode;
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
    private String startDate;
    private String endDate;
    private String advDate;
    private String description;
    private String escapeMonths;
    private Integer isFixedPayment;
    private BigDecimal payAmt;
    private Integer addAccrIntAmt;

    public String toJsonStringSelf() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .serializeNulls()
                .create();
        return gson.toJson(this);
    }

}
