package mn.io.polaris.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mn.io.polaris.model.polaris.Statement;
import mn.io.polaris.model.polaris.response.DepositTdAccountResponseDto;
import mn.io.polaris.model.request.CloseTdAccountRequestDto;
import mn.io.polaris.model.request.InfoRequest;
import mn.io.polaris.model.request.StatementRequest;
import mn.io.polaris.model.request.WithdrawTdAccountRequestDto;
import mn.io.polaris.model.response.PreviewCloseTdAccountResponseDto;
import mn.io.polaris.model.response.TdAccountDetailDto;
import mn.io.polaris.service.TdService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Td", description = "Хугацаат хадгаламж")
@RequestMapping(path = "/td", produces = MediaType.APPLICATION_JSON_VALUE)
public class TdController {

    @Resource
    private TdService tdService;

    @PostMapping(path = "/info")
    @Operation(summary = "Дансны дэлгэрэнгүй")
    public TdAccountDetailDto getTdInfo(@RequestBody @Valid InfoRequest infoRequest) {
        return tdService.getTdInfo(infoRequest);
    }

    @PostMapping(path = "/statement")
    @Operation(summary = "Дансны хуулга")
    public Statement getTdStatement(@RequestBody @Valid StatementRequest statementRequest) {
        return tdService.getTdStatement(statementRequest);
    }

    @PostMapping(path = "/close")
    @Operation(summary = "Хугацаат хадгаламжийн данс бэлэн бусаар хаах")
    public DepositTdAccountResponseDto closeTdAccount(@RequestBody @Valid CloseTdAccountRequestDto closeTdAccountRequestDto) {
        return tdService.closeTdAccount(closeTdAccountRequestDto);
    }

    @PostMapping(path = "/close/preview")
    @Operation(summary = "Хугацаат хадгаламжийн данс бэлэн бусаар хаах үйлдлийг шалгах")
    public PreviewCloseTdAccountResponseDto previewCloseTdAccount(@RequestBody @Valid CloseTdAccountRequestDto closeTdAccountRequestDto) {
        return tdService.previewCloseTdAccount(closeTdAccountRequestDto);
    }

    @PostMapping(path = "/withdraw")
    @Operation(summary = "Хугацаат хадгаламжаас дотоодын данс руу")
    public DepositTdAccountResponseDto withdrawTdAccount(@RequestBody @Valid WithdrawTdAccountRequestDto withdrawTdAccountRequestDto) {
        return tdService.withdrawTdAccount(withdrawTdAccountRequestDto);
    }

}