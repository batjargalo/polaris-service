package mn.io.polaris.model.polaris.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mn.io.polaris.helper.Utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditDigitalRepaymentRequest {

    private String acntCode;
    private String startDate;
    private BigDecimal calcAmt;
    private String payType;
    private String payFreq;
    private Integer payMonth;
    private Integer payDay1;
    private Integer payDay2;
    private String holidayOption;
    private String shiftPartialPay;
    private String shiftType;
    private Integer termFreeTimes;
    private String intTypeCode;
    private String endDate;
    private String advDate;
    private String description;
    private List<String> escapeMonths;
    private List<String> listNrs;

    public String toJsonStringSelf() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .serializeNulls()
                .create();
        return gson.toJson(this);
    }

}
