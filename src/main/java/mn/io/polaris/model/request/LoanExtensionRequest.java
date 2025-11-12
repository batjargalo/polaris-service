package mn.io.polaris.model.request;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mn.io.polaris.helper.Utils;

@Data
public class LoanExtensionRequest {

    @NotEmpty
    private String acntCode;
    @NotEmpty
    private String termLen;
    @NotEmpty
    private String endDate;
    @NotNull
    private String txnDesc;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }
}
