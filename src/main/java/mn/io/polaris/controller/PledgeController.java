package mn.io.polaris.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mn.io.polaris.model.polaris.AccountNo;
import mn.io.polaris.model.polaris.Pledge;
import mn.io.polaris.model.polaris.PledgeInfo;
import mn.io.polaris.model.polaris.PledgeResponse;
import mn.io.polaris.model.request.PledgeInfoByAccountRequest;
import mn.io.polaris.service.PledgeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Pledge", description = "Барьцаа хөрөнгө")
@RequestMapping(path = "/pledge", produces = MediaType.APPLICATION_JSON_VALUE)
public class PledgeController {

    @Resource
    private PledgeService pledgeService;

    @PostMapping(path = "/info-by-account")
    @Operation(summary = "Барьцаа хөрөнгийн дансны дэлгэрэнгүй (Холбосон дансаар)")
    public PledgeInfo getInfoByAccount(@RequestBody @Valid PledgeInfoByAccountRequest pledgeInfoByAccountRequest) {
        return pledgeService.getInfoByAccount(pledgeInfoByAccountRequest);
    }

    @PostMapping(path = "/list")
    @Operation(summary = "Зээл барьцаа хөрөнгийн холбоосын жагсаалт")
    public List<PledgeResponse> getPledgeListFromLoanAccount(@RequestBody @Valid AccountNo accountNo) {
        return pledgeService.getPledgeListFromLoanAccount(accountNo);
    }

    @PostMapping(path = "/info")
    @Operation(summary = "Барьцаа хөрөнгийн дансны дэлгэрэнгүй")
    public Pledge getPledge(@RequestBody @Valid AccountNo accountNo) {
        return pledgeService.getPledge(accountNo);
    }

}