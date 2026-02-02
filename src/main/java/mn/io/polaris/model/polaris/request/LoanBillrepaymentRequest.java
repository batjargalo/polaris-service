package mn.io.polaris.model.polaris.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class LoanBillrepaymentRequest {

    private String operCode;
    private String txnAcntCode;
    private BigDecimal txnAmount;
    private String txnDesc;
    private String sourceType;
    private int isPreview;
    private int isTmw;
    private List<AddParamsBill> addParams;
    private List<AspParam> aspParam;
    private String identityType;
    private String scrCode;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
