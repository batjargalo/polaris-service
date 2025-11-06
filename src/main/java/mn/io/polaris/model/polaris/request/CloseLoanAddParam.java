package mn.io.polaris.model.polaris.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloseLoanAddParam {

    private String chkAcntInt;
    private String contAcntType;

}
