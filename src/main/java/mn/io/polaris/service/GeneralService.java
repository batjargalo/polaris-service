package mn.io.polaris.service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.model.polaris.request.ParameterRequest;
import mn.io.polaris.model.polaris.response.ParameterResponse;
import mn.io.polaris.remote.PolarisClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class GeneralService {

    @Resource
    private PolarisClient polarisClient;

    public List<ParameterResponse> getParamList(ParameterRequest parameterRequest) {
        return polarisClient.getParamList(parameterRequest);
    }

}
