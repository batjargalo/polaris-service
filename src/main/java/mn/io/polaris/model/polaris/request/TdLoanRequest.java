package mn.io.polaris.model.polaris.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TdLoanRequest {

    private BigDecimal txnAmount;
    private String curCode;
    private BigDecimal rate;
    private String contAcntCode;
    private BigDecimal contAmount;
    private String contCurCode;
    private BigDecimal contRate;
    private String rateTypeId;
    private String txnDesc;
    private String sourceType;
    private Integer isPreview;
    private Integer isPreviewFee;
    private Integer isBlockInt;
    private CollAcnt collAcnt;
    private LoanAcnt loanAcnt;
    private AcntNrs acntNrs;
    private AcntInt acntInt;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
