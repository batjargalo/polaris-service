package mn.io.polaris.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mn.io.polaris.model.polaris.request.ParameterRequest;
import mn.io.polaris.model.polaris.response.ParameterResponse;
import mn.io.polaris.service.GeneralService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "General", description = "Ерөнхий")
@RequestMapping(path = "/general", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralController {

    @Resource
    private GeneralService generalService;

    @PostMapping(path = "/params")
    @Operation(summary = "Параметрийн жагсаалт авах")
    public List<ParameterResponse> getParamList(@RequestBody @Valid ParameterRequest parameterRequest) {
        return generalService.getParamList(parameterRequest);
    }

}