package mn.io.polaris.model.polaris.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddParam {

    @SerializedName("ACNTTYPE")
    @JsonProperty("ACNTTYPE")
    private String acntType;

    @SerializedName("WITHOUTTBREAK")
    @JsonProperty("WITHOUTTBREAK")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer withoutTbreak;

}
