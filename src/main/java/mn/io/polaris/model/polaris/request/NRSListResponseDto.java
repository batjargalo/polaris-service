package mn.io.polaris.model.polaris.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NRSListResponseDto {

    private List<NRSListDto> nrsList;

}
