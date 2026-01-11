package mn.io.polaris.model.polaris.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayLoanAddParam {

    @SerializedName("PAYCUSTCODE")
    @JsonProperty("PAYCUSTCODE")
    private String payCustCode;

    private String contAcntType;

}
