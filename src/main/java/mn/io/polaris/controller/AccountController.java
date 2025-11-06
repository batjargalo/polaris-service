package mn.io.polaris.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mn.io.polaris.model.polaris.AccountNo;
import mn.io.polaris.model.request.AccountCreateAllInOneRequest;
import mn.io.polaris.service.AccountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Account", description = "Харилцагчийн данс")
@RequestMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping(path = "/create/all-in-one")
    @Operation(summary = "Харилцагчийн данс үүсгэх /Бүх үйлдэл багтсан/")
    public AccountNo createAccountAllInOne(@RequestBody @Valid AccountCreateAllInOneRequest accountCreateAllInOneRequest) {
        return accountService.createAccountAllInOne(accountCreateAllInOneRequest);
    }

}