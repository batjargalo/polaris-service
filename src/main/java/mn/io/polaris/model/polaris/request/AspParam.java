package mn.io.polaris.model.polaris.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AspParam {

    private List<AspParamObject> list;
    private int operCode;

}
