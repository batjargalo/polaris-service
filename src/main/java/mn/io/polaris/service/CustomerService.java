package mn.io.polaris.service;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.constant.Constants;
import mn.io.polaris.model.polaris.*;
import mn.io.polaris.model.request.*;
import mn.io.polaris.model.response.AccountDto;
import mn.io.polaris.remote.PolarisClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class CustomerService {

    @Resource
    private PolarisClient polarisClient;

    public List<AccountDto> getTdOpenList(AccountListRequest accountListRequest) {
        List<Account> accounts = polarisClient.getAccountList(accountListRequest);
        List<AccountDto> tdOpenList = new ArrayList<>();
        for (Account a : accounts) {
            if (a.getStatus().equalsIgnoreCase(Constants.STATUS_OPEN)
                    && a.getAcntType().equalsIgnoreCase(Constants.ACCOUNT_TYPE_TD)) {
                AccountDto accountDto = convertAccountToAccountDto(a);
                tdOpenList.add(accountDto);
            }
        }
        return tdOpenList;
    }

    public List<AccountDto> getLoanOpenList(AccountListRequest accountListRequest) {
        List<Account> accounts = polarisClient.getAccountList(accountListRequest);
        List<AccountDto> loanOpenList = new ArrayList<>();
        for (Account a : accounts) {
            if (a.getStatus().equalsIgnoreCase(Constants.STATUS_OPEN)
                    && (a.getAcntType().equalsIgnoreCase(Constants.ACCOUNT_TYPE_LOAN)
                    || a.getAcntType().equalsIgnoreCase(Constants.ACCOUNT_TYPE_LINE))) {
                AccountDto accountDto = convertAccountToAccountDto(a);
                loanOpenList.add(accountDto);
            }
        }
        return loanOpenList;
    }

    public AccountDto convertAccountToAccountDto(Account a) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAcntCode(a.getAcntCode());
        return accountDto;
    }

    public void editRetailInfo(CustomerEditRequest customerEditRequest) {
        Retail retail = polarisClient.getRetailInfo(customerEditRequest.getCustCode());
        if (customerEditRequest.getPhone() != null && !customerEditRequest.getPhone().isEmpty()) {
            retail.setMobile(customerEditRequest.getPhone());
        }
        if (customerEditRequest.getEmail() != null && !customerEditRequest.getEmail().isEmpty()) {
            retail.setEmail(customerEditRequest.getEmail());
        }
        polarisClient.editRetailInfo(retail);
    }

    public Retail getRetailInfo(CustomerCodeRequest customerCodeRequest) {
        return polarisClient.getRetailInfo(customerCodeRequest.getCustCode());
    }

    public RetailAddress getRetailAddress(CustomerCodeRequest customerCodeRequest) {
        List<RetailAddress> retailAddresses = polarisClient.getRetailAddresses(customerCodeRequest);
        return retailAddresses.stream()
                .filter(address -> Objects.equals(address.getIsMain(), Constants.RETAIL_ADDRESS_MAIN))
                .findFirst()
                .orElse(null);
    }

    public void editRetailAddress(RetailAddressEditRequest retailAddressEditRequest) {
        RetailAddress retailAddress = this.getRetailAddress(
                new CustomerCodeRequest(retailAddressEditRequest.getCustCode()));
        if (retailAddress == null) {
            this.createRetailAddress(retailAddressEditRequest);
        } else {
            this.updateRetailAddress(retailAddressEditRequest, retailAddress);
        }
    }

    public void createRetailAddress(RetailAddressEditRequest retailAddressEditRequest) {
        RetailAddress retailAddress = convertRetailAddressRequestToRetailAddress(retailAddressEditRequest);
        polarisClient.createRetailAddress(retailAddress);
    }

    public void updateRetailAddress(RetailAddressEditRequest retailAddressEditRequest,
                                    RetailAddress retailAddress) {
        this.setRetailAddress(retailAddressEditRequest, retailAddress);
        polarisClient.updateRetailAddress(retailAddress);
    }

    public RetailAddress convertRetailAddressRequestToRetailAddress(RetailAddressEditRequest retailAddressEditRequest) {
        RetailAddress retailAddress = new RetailAddress();
        retailAddress.setIsMain(Constants.RETAIL_ADDRESS_MAIN);
        return setRetailAddress(retailAddressEditRequest, retailAddress);
    }

    private RetailAddress setRetailAddress(RetailAddressEditRequest retailAddressEditRequest,
                                           RetailAddress retailAddress) {
        retailAddress.setStatus(Constants.RETAIL_ADDRESS_STATUS_ACTIVE);
        retailAddress.setAddrId(retailAddressEditRequest.getAddrId());
        retailAddress.setCustCode(retailAddressEditRequest.getCustCode());
        retailAddress.setBlockDoor(retailAddressEditRequest.getBlockDoor());
        retailAddress.setStreet(retailAddressEditRequest.getStreet());
        retailAddress.setAddrDetail(retailAddressEditRequest.getAddrDetail());
        retailAddress.setCityTown(retailAddressEditRequest.getCityTown());
        return retailAddress;
    }

    public CustomerInfo getCustomerInfoByRegistrationId(CustomerInfoRequest customerInfoRequest) {
        return polarisClient.getCustomerInfoByRegistrationId(customerInfoRequest.getRegistrationId());
    }

    public CustomerCIF createRetail(RetailNewAllInOneRequest retailNewAllInOneRequest) {
        return polarisClient.createRetail(convertRetailNewAllInOneRequestToRetailNew(retailNewAllInOneRequest));
    }

    public RetailNew convertRetailNewAllInOneRequestToRetailNew(RetailNewAllInOneRequest retailNewAllInOneRequest) {
        RetailNew retailNew = new RetailNew();
        retailNew.setSexCode(retailNewAllInOneRequest.getSexCode());
        retailNew.setIndustryId(retailNewAllInOneRequest.getIndustryId());
        retailNew.setFamilyName(retailNewAllInOneRequest.getFamilyName());
        retailNew.setFamilyName2(retailNewAllInOneRequest.getFamilyName2());
        retailNew.setLastName(retailNewAllInOneRequest.getLastName());
        retailNew.setLastName2(retailNewAllInOneRequest.getLastName2());
        retailNew.setFirstName(retailNewAllInOneRequest.getFirstName());
        retailNew.setFirstName2(retailNewAllInOneRequest.getFirstName2());
        retailNew.setShortName(retailNewAllInOneRequest.getShortName());
        retailNew.setShortName2(retailNewAllInOneRequest.getShortName2());
        retailNew.setRegisterCode(retailNewAllInOneRequest.getRegisterCode());
        retailNew.setBirthDate(retailNewAllInOneRequest.getBirthDate());
        retailNew.setMobile(retailNewAllInOneRequest.getMobile());
        retailNew.setEmail(retailNewAllInOneRequest.getEmail());
        retailNew.setMaritalStatus(retailNewAllInOneRequest.getMaritalStatus());
        retailNew.setBirthPlaceDetail(retailNewAllInOneRequest.getBirthPlaceDetail());
        retailNew.setPhone(retailNewAllInOneRequest.getPhone());
        retailNew.setFax(retailNewAllInOneRequest.getFax());
        retailNew.setEmploymentId(retailNewAllInOneRequest.getEmploymentId());
        retailNew.setTitleId(retailNewAllInOneRequest.getTitleId());
        retailNew.setNationalityId(retailNewAllInOneRequest.getNationalityId());
        retailNew.setEducationId(retailNewAllInOneRequest.getEducationId());
        retailNew.setEthnicGroupId(retailNewAllInOneRequest.getEthnicGroupId());
        retailNew.setFamilyCnt(retailNewAllInOneRequest.getFamilyCnt());
        retailNew.setWorkerCnt(retailNewAllInOneRequest.getWorkerCnt());

        //Тогтмол байх утгууд
        retailNew.setCustSegCode(Constants.CUST_SEG_CODE_RETAIL);
        retailNew.setTaxExemption(Constants.CUST_TAX_EXEMPTION);
        retailNew.setStatus(Constants.CUST_STATUS);
        retailNew.setIsCompanyCustomer(Constants.CUST_IS_COMPANY_CUSTOMER);
        retailNew.setRegisterMaskCode(Constants.CUST_REGISTER_MASK_CODE);
        retailNew.setCountryCode(Constants.CUST_COUNTRY_CODE);
        retailNew.setLangCode(Constants.CUST_LANG_CODE);
        return retailNew;
    }

    public CustomerCIF createCorporate(CorporateNewRequest corporateNewRequest) {
        return polarisClient.createCorporate(convertCorporateNewRequestToCorporateNew(corporateNewRequest));
    }

    public CorporateNew convertCorporateNewRequestToCorporateNew(CorporateNewRequest corporateNewRequest) {
        CorporateNew corporateNew = new CorporateNew();
        corporateNew.setFoundedDate(corporateNewRequest.getFoundedDate());
        corporateNew.setIndustryId(corporateNewRequest.getIndustryId());
        corporateNew.setName(corporateNewRequest.getName());
        corporateNew.setName2(corporateNewRequest.getName2());
        corporateNew.setShortName(corporateNewRequest.getShortName());
        corporateNew.setShortName2(corporateNewRequest.getShortName2());
        corporateNew.setCustSegCode(corporateNewRequest.getCustSegCode());
        corporateNew.setRegisterCode(corporateNewRequest.getRegisterCode());
        corporateNew.setPhone(corporateNewRequest.getPhone());
        corporateNew.setEmail(corporateNewRequest.getEmail());

        //Тогтмол байх утгууд
        corporateNew.setStatus(Constants.CUST_STATUS);
        corporateNew.setIsCompanyCustomer(Constants.CUST_IS_COMPANY_CUSTOMER);
        corporateNew.setRegisterMaskCode(Constants.CUST_REGISTER_MASK_CODE_CORPORATE);
        corporateNew.setCountryCode(Constants.CUST_COUNTRY_CODE);
        return corporateNew;
    }

    public CustomerCIF createRetailAllInOne(RetailNewAllInOneRequest retailNewAllInOneRequest) {
        CustomerCIF customerCIF = this.createRetail(retailNewAllInOneRequest);
        log.info("Харилцагч амжилттай үүссэн: {}", customerCIF.getCustCode());

        try {
            if (retailNewAllInOneRequest.getAddrId() != null) {
                RetailAddressEditRequest retailAddressEditRequest = this.getRetailAddressEditRequest(retailNewAllInOneRequest);
                retailAddressEditRequest.setCustCode(customerCIF.getCustCode());
                this.editRetailAddress(retailAddressEditRequest);
                log.info("Хаяг амжилттай үүссэн.");
            }
        } catch (Exception e) {
            log.error("Харилцагчийн хаяг үүсгэх үед алдаа гарсан: {}", e.getMessage());
        }

        return customerCIF;
    }

    private RetailAddressEditRequest getRetailAddressEditRequest(RetailNewAllInOneRequest retailNewAllInOneRequest) {
        RetailAddressEditRequest retailAddressEditRequest = new RetailAddressEditRequest();
        retailAddressEditRequest.setAddrId(retailNewAllInOneRequest.getAddrId());
        retailAddressEditRequest.setCityTown(retailNewAllInOneRequest.getCityTown());
        retailAddressEditRequest.setStreet(retailNewAllInOneRequest.getStreet());
        retailAddressEditRequest.setBlockDoor(retailNewAllInOneRequest.getBlockDoor());
        retailAddressEditRequest.setAddrDetail(retailNewAllInOneRequest.getAddrDetail());
        return retailAddressEditRequest;
    }

    public List<Account> getTdNotOpenList(@Valid AccountListRequest accountListRequest) {
        List<Account> accounts = polarisClient.getAccountListByStatus(accountListRequest);
        List<Account> tdNotOpenList = new ArrayList<>();
        for (Account a : accounts) {
            if (a.getAcntType().equalsIgnoreCase(Constants.ACCOUNT_TYPE_TD)) {
                tdNotOpenList.add(a);
            }
        }
        return tdNotOpenList;
    }

    public List<Account> getLoanNotOpenList(@Valid AccountListRequest accountListRequest) {
        List<Account> accounts = polarisClient.getAccountListByStatus(accountListRequest);
        List<Account> loanNotOpenList = new ArrayList<>();
        for (Account a : accounts) {
            if (a.getAcntType().equalsIgnoreCase(Constants.ACCOUNT_TYPE_LOAN)
                    || a.getAcntType().equalsIgnoreCase(Constants.ACCOUNT_TYPE_LINE)) {
                loanNotOpenList.add(a);
            }
        }
        return loanNotOpenList;
    }

}
