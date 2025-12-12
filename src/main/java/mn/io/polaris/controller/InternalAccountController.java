package mn.io.polaris.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mn.io.polaris.model.polaris.response.DepositTdAccountResponseDto;
import mn.io.polaris.model.request.BetweenAccountsRequestDto;
import mn.io.polaris.model.request.DepositTdAccountRequestDto;
import mn.io.polaris.model.request.LoanBetweenAccountsRequestDto;
import mn.io.polaris.model.request.LoanNonCashRequestDto;
import mn.io.polaris.service.InternalAccountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Internal Account", description = "Дотоодын данс")
@RequestMapping(path = "/internal-account", produces = MediaType.APPLICATION_JSON_VALUE)
public class InternalAccountController {

    @Resource
    private InternalAccountService internalAccountService;

    @PostMapping(path = "/deposit-td-account")
    @Operation(summary = "Дотоодын данснаас хугацаат хадгаламж руу шилжүүлэг хийх")
    public DepositTdAccountResponseDto depositTdAccount(@RequestBody @Valid DepositTdAccountRequestDto depositTdAccountRequestDto) {
        return internalAccountService.depositTdAccount(depositTdAccountRequestDto);
    }

    @PostMapping(path = "/between-accounts")
    @Operation(summary = "Дотоодын данснаас дотоод данс руу шилжүүлэг")
    public DepositTdAccountResponseDto betweenAccounts(@RequestBody @Valid BetweenAccountsRequestDto betweenAccountsRequestDto) {
        return internalAccountService.betweenAccounts(betweenAccountsRequestDto);
    }

    @PostMapping(path = "/loan-non-cash")
    @Operation(summary = "Зээл олгох - Бэлэн бус")
    public DepositTdAccountResponseDto loanNonCash(@RequestBody @Valid LoanNonCashRequestDto loanNonCashRequestDto) {
        return internalAccountService.loanNonCash(loanNonCashRequestDto);
    }

    @PostMapping(path = "/loan-between-accounts")
    @Operation(summary = "Дотоодын данснаас дотоод данс руу шилжүүлэг /Зээл/")
    public DepositTdAccountResponseDto loanBetweenAccounts(@RequestBody @Valid LoanBetweenAccountsRequestDto loanBetweenAccountsRequestDto) {
        return internalAccountService.loanBetweenAccounts(loanBetweenAccountsRequestDto);
    }

}