package mn.io.polaris.model.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import mn.io.polaris.helper.Utils;

import java.util.Date;

@Data
public class ChangeLoanIntRateRequest {

    @NotEmpty
    private String acntCode;

    @NotEmpty
    private String intTypeCode;

    @NotEmpty
    private String intRate;

    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
