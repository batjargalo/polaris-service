package mn.io.polaris.model.polaris.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mn.io.polaris.helper.Utils;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PayLoanRequestCustom extends DigitalLoanCustomRequest {

    private List<PayLoanCustomAddParam> addParams;

    public String toJsonStringSelf() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Utils.DateSerializer())
                .create();
        return gson.toJson(this);
    }

}
