package mn.io.polaris.model.polaris.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddParamsBill {

    private Integer contIsAcrInt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer billNo;

}
