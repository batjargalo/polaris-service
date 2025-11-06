package mn.io.polaris.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mn.io.polaris.model.polaris.*;
import mn.io.polaris.model.request.*;
import mn.io.polaris.model.response.AccountDto;
import mn.io.polaris.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Customer", description = "Харилцагч")
@RequestMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping(path = "/td/open")
    @Operation(summary = "Нээлттэй хадгаламжийн дансны жагсаалт")
    public List<AccountDto> getTdOpenList(@RequestBody @Valid AccountListRequest accountListRequest) {
        return customerService.getTdOpenList(accountListRequest);
    }

    @PostMapping(path = "/td/history")
    @Operation(summary = "Нээлттэй бус хадгаламжийн дансны жагсаалт")
    public List<Account> getTdNotOpenList(@RequestBody @Valid AccountListRequest accountListRequest) {
        return customerService.getTdNotOpenList(accountListRequest);
    }

    @PostMapping(path = "/loan/open")
    @Operation(summary = "Нээлттэй зээлийн дансны жагсаалт")
    public List<AccountDto> getLoanOpenList(@RequestBody @Valid AccountListRequest accountListRequest) {
        return customerService.getLoanOpenList(accountListRequest);
    }

    @PostMapping(path = "/loan/history")
    @Operation(summary = "Нээлттэй бус зээлийн дансны жагсаалт")
    public List<Account> getLoanNotOpenList(@RequestBody @Valid AccountListRequest accountListRequest) {
        return customerService.getLoanNotOpenList(accountListRequest);
    }

    @PostMapping(path = "/retail/info/edit")
    @Operation(summary = "Харилцагч засах")
    public void editRetailInfo(@RequestBody @Valid CustomerEditRequest customerEditRequest) {
        customerService.editRetailInfo(customerEditRequest);
    }

    @PostMapping(path = "/retail/info")
    @Operation(summary = "Хувь хүний дэлгэрэнгүй")
    public Retail getRetailInfo(@RequestBody @Valid CustomerCodeRequest customerCodeRequest) {
        return customerService.getRetailInfo(customerCodeRequest);
    }

    @PostMapping(path = "/address")
    @Operation(summary = "Харилцагчийн байнгын хаяг")
    public RetailAddress getRetailAddress(@RequestBody @Valid CustomerCodeRequest customerCodeRequest) {
        return customerService.getRetailAddress(customerCodeRequest);
    }

    @PostMapping(path = "/address/edit")
    @Operation(summary = "Харилцагчийн байнгын хаяг засах")
    public void editRetailAddress(@RequestBody @Valid RetailAddressEditRequest retailAddressEditRequest) {
        customerService.editRetailAddress(retailAddressEditRequest);
    }

    @PostMapping(path = "/retail/registration-id")
    @Operation(summary = "Хувь хүний дэлгэрэнгүй /Регистрээр/")
    public CustomerInfo getCustomerInfoByRegistrationId(@RequestBody @Valid CustomerInfoRequest customerInfoRequest) {
        return customerService.getCustomerInfoByRegistrationId(customerInfoRequest);
    }

    @PostMapping(path = "/corporate/new")
    @Operation(summary = "Харилцагч бүртгэх – Байгууллага")
    public CustomerCIF createCorporate(@RequestBody @Valid CorporateNewRequest corporateNewRequest) {
        return customerService.createCorporate(corporateNewRequest);
    }

    @PostMapping(path = "/retail/new/all-in-one")
    @Operation(summary = "Харилцагч бүртгэх /Бүх үйлдэл багтсан/")
    public CustomerCIF createRetailAllInOne(@RequestBody @Valid RetailNewAllInOneRequest retailNewAllInOneRequest) {
        return customerService.createRetailAllInOne(retailNewAllInOneRequest);
    }

}