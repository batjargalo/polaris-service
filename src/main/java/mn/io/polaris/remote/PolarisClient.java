package mn.io.polaris.remote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.model.dao.PolarisDao;
import mn.io.polaris.model.dao.SystemDao;
import mn.io.polaris.model.polaris.*;
import mn.io.polaris.model.polaris.request.*;
import mn.io.polaris.model.polaris.response.DepositTdAccountResponseDto;
import mn.io.polaris.model.polaris.response.LoanAccountResponse;
import mn.io.polaris.model.polaris.response.LoanAcntListResponse;
import mn.io.polaris.model.polaris.response.LoanExtendPResponse;
import mn.io.polaris.model.polaris.response.ParameterResponse;
import mn.io.polaris.model.polaris.response.TempAccount;
import mn.io.polaris.model.request.*;
import mn.io.polaris.model.response.LoanAccountBalance;
import mn.io.polaris.repository.PolarisDaoRepository;
import mn.io.polaris.repository.SystemDaoRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Log4j2
@Service
public class PolarisClient {

    @Resource
    private SystemDaoRepository systemDaoRepository;

    @Resource
    private PolarisDaoRepository polarisDaoRepository;

    private String getPolarisUrl() {
        SystemDao systemDao = systemDaoRepository.findById("POLARIS_URL").orElseThrow();
        return systemDao.getValue();
    }

    private String getRole() {
        SystemDao systemDao = systemDaoRepository.findById("POLARIS_ROLE").orElseThrow();
        return systemDao.getValue();
    }

    private String getCompany() {
        SystemDao systemDao = systemDaoRepository.findById("POLARIS_COMPANY").orElseThrow();
        return systemDao.getValue();
    }

    private String getCookie() {
        SystemDao systemDao = systemDaoRepository.findById("POLARIS_TOKEN").orElseThrow();
        return systemDao.getValue();
    }

    public HttpHeaders setPolarisHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", "NESSESSION=" + getCookie());
        headers.add("role", getRole());
        headers.add("company", getCompany());
        return headers;
    }

    public List<Account> getAccountList(AccountListRequest accountListRequest) {
        List<Object> array = new ArrayList<>();
        array.add(accountListRequest.getCustCode());
        array.add(accountListRequest.getPageNumber());
        array.add(accountListRequest.getPageSize());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610312");
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // region Зээлийн дансны жагсаалт Munkh

    public List<LoanAcntListResponse> getLoanAccountList(GetLoanList getLoanList) {
        // List<Object> params = new ArrayList<>();
        List<Object> array = new ArrayList<>();
        // params.add(getLoanList.getCust());
        // params.add(getLoanList.getProd());
        // params.add(getLoanList.getParams());
        // array.add(params);

        List<Map<String, Object>> filters = new ArrayList<>();
        for (Map.Entry<GetLoanListCust, Object> entry : getLoanList.getMap().entrySet()) {
            Map<String, Object> filter = new HashMap<>();
            filter.put("field", entry.getKey().field);
            filter.put("operation", entry.getKey().operation);
            filter.put("type", entry.getKey().type);
            filter.put("value", entry.getKey().value);
            filters.add(filter);
        }
        for (Map.Entry<GetLoanListParams, Object> entry : getLoanList.getParams().entrySet()) {
            Map<String, Object> filter = new HashMap<>();
            filter.put("_iField", entry.getKey()._iField);
            filter.put("_iOperation", entry.getKey()._iOperation);
            filter.put("_iType", entry.getKey()._iType);
            filter.put("_inValues", entry.getKey()._inValues);
            filters.add(filter);
        }

        // array.add(getLoanList.getMap());
        // array.add(getLoanList.getMap2());
        // array.add(getLoanList.getParams());
        array.add(filters);
        array.add(getLoanList.getPageNumber());
        array.add(getLoanList.getPageSize());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610333");

        JSONArray jsonArray = new JSONArray(array);
        String json = jsonArray.toString();
        System.out.println(json); // Output: ["reading","coding"]
        Gson gson = new Gson();
        String responseBody = sendRequest(new HttpEntity<>(gson.toJson(array), headers));

        System.out.println(responseBody); // Output: ["reading","coding"]
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                    mapper.readValue(responseBody, new TypeReference<>() {
                    })));
            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // endregion

    // region Нээлттэй харилцах дансны жагсаалт Munkh

    public List<Account> getCasaAccountListByStatus(AccountListRequest accountListRequest) {
        // List<Object> arrayInside = getObjects(accountListRequest);
        List<Object> arrayInside = new ArrayList<>();
        GetLoanListCust cust1 = new GetLoanListCust();
        cust1.setField("CUST_CODE");
        cust1.setOperation("=");
        cust1.setType(3);
        cust1.setValue(accountListRequest.getCustCode());
        arrayInside.add(cust1);

        GetLoanListCust cust2 = new GetLoanListCust();
        cust2.setField("ACNT_TYPE");
        cust2.setOperation("=");
        cust2.setType(3);
        cust2.setValue("CA");
        arrayInside.add(cust2);

        GetLoanListParams para = new GetLoanListParams();
        para._iField = "STATUS";
        para._iOperation = "IN";
        para._iType = 3;
        para._inValues = Arrays.asList("O", "N");
        arrayInside.add(para);

        List<Object> array = new ArrayList<>();
        // Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // String jsonOutput = gson.toJson(arrayInside);
        System.out.println(arrayInside);
        array.add(arrayInside);
        array.add(accountListRequest.getPageNumber());
        array.add(accountListRequest.getPageSize());
        Gson gson = new Gson();

        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610333");
        String responseBody = sendRequest(new HttpEntity<>(gson.toJson(array), headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // endregion

    public Td getTdInfo(InfoRequest infoRequest) {
        List<Object> array = new ArrayList<>();
        array.add(infoRequest.getAcntCode());
        array.add(infoRequest.getGetWithSecure());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610100");
        Td td;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            td = mapper.readValue(responseBody, Td.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return td;
    }

    public Statement getTdStatement(StatementRequest statementRequest) {
        List<Object> array = createStatementArray(statementRequest);
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610101");
        return getStatement(array, headers);
    }

    private Statement getStatement(List<Object> array, HttpHeaders headers) {
        Statement statement;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            statement = mapper.readValue(responseBody, Statement.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return statement;
    }

    public List<Object> createStatementArray(StatementRequest statementRequest) {
        List<Object> array = new ArrayList<>();
        array.add(statementRequest.getAcntCode());
        array.add(statementRequest.getStartDate());
        array.add(statementRequest.getEndDate());
        array.add(statementRequest.getStartPagingPosition());
        array.add(statementRequest.getPageRowCount());
        return array;
    }

    public LoanAccount getLoanInfo(InfoRequest infoRequest) {
        List<Object> array = new ArrayList<>();
        array.add(infoRequest.getAcntCode());
        array.add(infoRequest.getGetWithSecure());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610200");
        LoanAccount loanAccount;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            loanAccount = mapper.readValue(responseBody, LoanAccount.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return loanAccount;
    }

    public Statement getLoanStatement(StatementRequest statementRequest) {
        List<Object> array = createStatementArray(statementRequest);
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610201");
        return getStatement(array, headers);
    }

    public List<LoanRepayment> getLoanRepayment(LoanRepaymentRequest loanRepaymentRequest) {
        List<Object> array = new ArrayList<>();
        array.add(loanRepaymentRequest.getAcntCode());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610203");
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public List<DueLoan> getDueLoans() {
        List<Object> array = new ArrayList<>();
        array.add(5);
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13611357");
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public LoanClose getCloseAmount(LoanCloseRequest loanCloseRequest) {
        List<Object> array = new ArrayList<>();
        array.add(loanCloseRequest.getAcntCode());
        array.add(loanCloseRequest.getCloseDate());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610266");
        LoanClose loanClose;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            loanClose = mapper.readValue(responseBody, LoanClose.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return loanClose;
    }

    public LoanRepaymentInfo getRepaymentInfo(String acntCode) {
        List<Object> array = new ArrayList<>();
        array.add(acntCode);
        array.add(null);
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13611370");
        LoanRepaymentInfo loanRepaymentInfo;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            loanRepaymentInfo = mapper.readValue(responseBody, LoanRepaymentInfo.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return loanRepaymentInfo;
    }

    public Retail getRetailInfo(String custCode) {
        List<Object> array = new ArrayList<>();
        array.add(custCode);
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610310");
        Retail retail;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            retail = mapper.readValue(responseBody, Retail.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return retail;
    }

    public void editRetailInfo(Retail retail) {
        List<Object> array = new ArrayList<>();
        array.add(retail.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610315");
        sendRequest(createHttpEntity(array, headers));
    }

    public List<RetailAddress> getRetailAddresses(CustomerCodeRequest customerCodeRequest) {
        List<Object> array = new ArrayList<>();
        array.add(customerCodeRequest.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13619991");
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void createRetailAddress(RetailAddress retailAddress) {
        List<Object> array = new ArrayList<>();
        array.add(retailAddress.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610317");
        sendRequest(createHttpEntity(array, headers));
    }

    public void updateRetailAddress(RetailAddress retailAddress) {
        List<Object> array = new ArrayList<>();
        array.add(retailAddress.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610318");
        sendRequest(createHttpEntity(array, headers));
    }

    public String sendRequest(HttpEntity<String> entity) {
        PolarisDao polarisDao = new PolarisDao();
        polarisDao.setRequest(entity.toString());
        polarisDao.setRequestDate(new Date());
        PolarisDao savedPolarisDao = polarisDaoRepository.save(polarisDao);
        try {
            RestTemplate restTemplate = new RestTemplate();
            log.info("entity: {}", entity.toString());
            ResponseEntity<String> response = restTemplate.exchange(getPolarisUrl(), HttpMethod.POST, entity,
                    String.class);
            String responseBody = response.getBody();
            log.info(responseBody);
            savedPolarisDao.setResponse(responseBody);
            savedPolarisDao.setResponseDate(new Date());
            polarisDaoRepository.save(savedPolarisDao);
            return responseBody;
        } catch (HttpClientErrorException http) {
            log.error("httpClient: " + http);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, http.getMessage());
        } catch (HttpServerErrorException httpServer) {
            log.error("httpServer: " + httpServer);
            savedPolarisDao.setResponse(httpServer.getMessage());
            savedPolarisDao.setResponseDate(new Date());
            polarisDaoRepository.save(savedPolarisDao);
            if (httpServer.getStatusCode() == HttpStatus.NOT_IMPLEMENTED) {
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, httpServer.getMessage());
            }
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, httpServer.getMessage());
        } catch (Exception e) {
            log.error("error: " + e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private HttpEntity<String> createHttpEntity(List<Object> array, HttpHeaders headers) {
        return new HttpEntity<>(array.toString(), headers);
    }

    public CustomerInfo getCustomerInfoByRegistrationId(String registrationId) {
        List<Object> array = new ArrayList<>();
        array.add(registrationId);
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610335");
        CustomerInfo customerInfo;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            customerInfo = mapper.readValue(responseBody, CustomerInfo.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return customerInfo;
    }

    public CustomerCIF createRetail(RetailNew retailNew) {
        List<Object> array = new ArrayList<>();
        array.add(retailNew.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610313");
        return getCustomerCIF(array, headers);
    }

    public CustomerCIF createCorporate(CorporateNew corporateNew) {
        List<Object> array = new ArrayList<>();
        array.add(corporateNew.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610314");
        return getCustomerCIF(array, headers);
    }

    private CustomerCIF getCustomerCIF(List<Object> array, HttpHeaders headers) {
        CustomerCIF customerCIF;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            customerCIF = mapper.readValue(responseBody, CustomerCIF.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return customerCIF;
    }

    public void createCustomerProfession(CustomerProfession customerProfession) {
        List<Object> array = new ArrayList<>();
        array.add(customerProfession.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610328");
        sendRequest(createHttpEntity(array, headers));
    }

    public void createCustomerContact(CustomerContact customerContact) {
        List<Object> array = new ArrayList<>();
        array.add(new HashMap<>());
        array.add(customerContact.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13611351");
        sendRequest(createHttpEntity(array, headers));
    }

    public AccountNo createAccount(AccountCreateRequest accountCreateRequest) {
        List<Object> array = new ArrayList<>();
        array.add(accountCreateRequest.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610020");
        return getAccountNo(array, headers);
    }

    public void changeAccountStatus(String acntCode) {
        List<Object> array = new ArrayList<>();
        array.add(acntCode);
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610063");
        sendRequest(createHttpEntity(array, headers));
    }

    public AccountNo createTdAccount(AccountTdCreateRequest accountTdCreateRequest) {
        List<Object> array = new ArrayList<>();
        array.add(accountTdCreateRequest.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610120");
        return getAccountNo(array, headers);
    }

    public void changeTdAccountStatus(ChangeTdAccountStatus changeTdAccountStatus) {
        List<Object> array = new ArrayList<>();
        array.add(changeTdAccountStatus.getAcntCode());
        array.add(changeTdAccountStatus.getDescription());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610122");
        sendRequest(createHttpEntity(array, headers));
    }

    private AccountNo getAccountNo(List<Object> array, HttpHeaders headers) {
        AccountNo accountNo;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            accountNo = mapper.readValue(responseBody, AccountNo.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return accountNo;
    }

    public PledgeInfo getInfoByAccount(PledgeInfoByAccountRequest pledgeInfoByAccountRequest) {
        List<Object> array = new ArrayList<>();
        array.add(pledgeInfoByAccountRequest.getSysNo());
        array.add(pledgeInfoByAccountRequest.getAcntCode());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610907");
        PledgeInfo pledgeInfo;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            pledgeInfo = mapper.readValue(responseBody, PledgeInfo.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return pledgeInfo;
    }

    public String getSystemDate() {
        List<Object> array = new ArrayList<>();
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13619000");
        return sendRequest(createHttpEntity(array, headers));
    }

    public DepositTdAccountResponseDto depositTdAccount(DepositTdAccountRequest depositTdAccountRequest) {
        List<Object> array = new ArrayList<>();
        array.add(depositTdAccountRequest.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610022");
        return getDepositTdAccountResponseDto(array, headers);
    }

    public DepositTdAccountResponseDto closeTdAccount(CloseTdAccountRequest closeTdAccountRequest) {
        List<Object> array = new ArrayList<>();
        array.add(closeTdAccountRequest.toJsonStringSelf());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610285");
        return getDepositTdAccountResponseDto(array, headers);
    }

    private DepositTdAccountResponseDto getDepositTdAccountResponseDto(List<Object> array, HttpHeaders headers) {
        DepositTdAccountResponseDto depositTdAccountResponseDto;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            depositTdAccountResponseDto = mapper.readValue(responseBody, DepositTdAccountResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return depositTdAccountResponseDto;
    }

    public DepositTdAccountResponseDto payLoan(PayLoanRequest payLoanRequest) {
        List<Object> array = new ArrayList<>();
        array.add(payLoanRequest.toJsonStringSelf());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610250");
        return getDepositTdAccountResponseDto(array, headers);
    }

    public DepositTdAccountResponseDto closeLoan(CloseLoanRequest closeLoanRequest) {
        List<Object> array = new ArrayList<>();
        array.add(closeLoanRequest.toJsonStringSelf());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610267");
        return getDepositTdAccountResponseDto(array, headers);
    }

    // region ПОЛАРИСРУУ ЗЭЭЛ ҮҮСГЭХ ХҮСЭЛТ

    public DepositTdAccountResponseDto createTdLoan(TdLoanRequest tdLoanRequest) {
        List<Object> array = new ArrayList<>();
        array.add(tdLoanRequest.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610265");
        return getDepositTdAccountResponseDto(array, headers);
    }
    // endregion

    public static String extractValue(String response) {
        // Remove the "(String)" prefix and ";" suffix
        String cleaned = response.replace("(String)", "").replace(";", "").trim();
        // Remove surrounding quotes to get the inner string
        if (cleaned.startsWith("\"") && cleaned.endsWith("\"")) {
            return cleaned.substring(1, cleaned.length() - 1);
        }
        return cleaned; // Fallback
    }

    // region ЦАХИМ ЗЭЭЛ ҮҮСГЭХ ХҮСЭЛТ

    public LoanAccountResponse createLoan(LoanRequest loanRequest) {
        List<Object> array = new ArrayList<>();
        array.add(loanRequest.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610253");
        return getLoanAccountResponse(array, headers);
    }

    private LoanAccountResponse getLoanAccountResponse(List<Object> array, HttpHeaders headers) {
        LoanAccountResponse loanAccountResponse;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        // String json = "{" + "\"acntCode\": \" " + responseBody + "}";
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("acntCode", extractValue(responseBody));
        // Serialize to JSON
        Gson gson = new Gson();
        String json = gson.toJson(jsonMap);
        ObjectMapper mapper = new ObjectMapper();
        try {
            loanAccountResponse = mapper.readValue(
                    json,
                    LoanAccountResponse.class);
            // loanAccountResponse.setCaAcntCode(extractedId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return loanAccountResponse;
    }
    // endregion

    // region ЗЭЭЛИЙН ХУГАЦАА СУНГАХ ХҮСЭЛТ

    public LoanExtendPResponse extendLoan(LoanExtensionRequest loanExtensionRequest) {
        LoanExtendPResponse loanExtendPResponse = new LoanExtendPResponse();
        List<Object> array = new ArrayList<>();
        // Create a map and use put for key-value pairs
        Map<String, Object> map = new HashMap<>();
        map.put("operCode", "13610271");
        map.put("txnAcntCode", loanExtensionRequest.getAcntCode());
        map.put("isPreview", 0);
        map.put("txnDesc", loanExtensionRequest.getTxnDesc());
        map.put("isPreviewFee", 0);
        map.put("isTmw", 1);
        List<Object> aspParam = new ArrayList<>();
        List<Object> aspParamBody = new ArrayList<>();
        Map<String, Object> aspParamChild = new HashMap<>();
        aspParamChild.put("acntCode", loanExtensionRequest.getAcntCode());
        aspParamChild.put("acntType", "EXPENSE");
        aspParamBody.add(aspParamChild);
        aspParam.add(aspParamBody);
        aspParam.add(13610271);
        List<Object> addParams = new ArrayList<>();
        Map<String, Object> addParamChild = new HashMap<>();
        addParamChild.put("TERMLEN", loanExtensionRequest.getTermLen());
        addParamChild.put("ENDDATE", loanExtensionRequest.getEndDate());
        addParams.add(addParamChild);
        map.put("aspParam", aspParam);
        map.put("addParams", addParams);
        map.put("identityType", "");
        map.put("scrCode", "OI");

        // Add the map to the list
        array.add(map);

        // array.add(loanExtensionRequest.toJsonString());

        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610271");
        Gson gson1 = new Gson();
        String responseBody = sendRequest(new HttpEntity<>(gson1.toJson(array), headers));
        // String json = "{" + "\"acntCode\": \" " + responseBody + "}";
        // Map<String, Object> jsonMap = new HashMap<>();
        // jsonMap.put("isSupervisor", extractValue(responseBody));
        // Serialize to JSON
        Gson gson = new Gson();
        String json = gson.toJson(responseBody);
        ObjectMapper mapper = new ObjectMapper();
        try {
            loanExtendPResponse = mapper.readValue(
                    json,
                    LoanExtendPResponse.class);
            // loanAccountResponse.setCaAcntCode(extractedId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return loanExtendPResponse;
    }

    // endregion

    public TempAccount getTempAccountInfo(InfoRequest infoRequest) {
        List<Object> array = new ArrayList<>();
        array.add(infoRequest.getAcntCode());
        array.add(infoRequest.getGetWithSecure());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610000");
        TempAccount tempAccount;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            tempAccount = mapper.readValue(responseBody, TempAccount.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return tempAccount;
    }

    public DepositTdAccountResponseDto transferFromTempAccToInternalAcc(TransferRequest transferRequest) {
        List<Object> array = new ArrayList<>();
        array.add(transferRequest.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610053");
        return getDepositTdAccountResponseDto(array, headers);
    }

    public DepositTdAccountResponseDto closeTempAccount(CloseTempAccountRequest closeTempAccountRequest) {
        List<Object> array = new ArrayList<>();
        array.add(closeTempAccountRequest.toJsonStringSelf());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610056");
        return getDepositTdAccountResponseDto(array, headers);
    }

    public DepositTdAccountResponseDto withdrawFromTdAccount(WithdrawTdAccountRequest withdrawTdAccountRequest) {
        List<Object> array = new ArrayList<>();
        array.add(withdrawTdAccountRequest.toJsonStringSelf());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610289");
        return getDepositTdAccountResponseDto(array, headers);
    }

    public List<ParameterResponse> getParamList(ParameterRequest parameterRequest) {
        List<List<String>> array = new ArrayList<>();
        array.add(parameterRequest.getDictList());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13619002");
        List<ParameterResponse> parameterResponseList;
        Gson gson = new Gson();
        String responseBody = sendRequest(new HttpEntity<>(gson.toJson(array), headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            parameterResponseList = mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return parameterResponseList;
    }

    // region ПОЛАРИС Харилцагчийн дансны жагсаалт /төлөвөөр/

    public List<Account> getAccountListByStatus(AccountListRequest accountListRequest) {
        List<Object> arrayInside = getObjects(accountListRequest);

        List<Object> array = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(arrayInside);

        array.add(jsonOutput);
        array.add(accountListRequest.getPageNumber());
        array.add(accountListRequest.getPageSize());

        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610333");
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // endregion

    private static List<Object> getObjects(AccountListRequest accountListRequest) {
        Map<String, Object> firstObject = new HashMap<>();
        firstObject.put("field", "CUST_CODE");
        firstObject.put("operation", "=");
        firstObject.put("type", 3);
        firstObject.put("value", accountListRequest.getCustCode());

        Map<String, Object> secondObject = new HashMap<>();
        secondObject.put("_iField", "STATUS");
        secondObject.put("_iOperation", "IN");
        secondObject.put("_iType", 3);

        List<String> inValues = new ArrayList<>();
        inValues.add("C");
        secondObject.put("_inValues", inValues);

        List<Object> arrayInside = new ArrayList<>();
        arrayInside.add(firstObject);
        arrayInside.add(secondObject);
        return arrayInside;
    }

    public DepositTdAccountResponseDto betweenAccounts(BetweenAccountsRequest betweenAccountsRequest) {
        List<Object> array = new ArrayList<>();
        array.add(betweenAccountsRequest.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610650");
        return getDepositTdAccountResponseDto(array, headers);
    }

    public void editLoanAccountRepayment(EditRepaymentRequest editRepaymentRequest) {
        List<Object> array = new ArrayList<>();
        array.add(editRepaymentRequest.toJsonStringSelf());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13611332");
        sendRequest(createHttpEntity(array, headers));
    }

    // region Зээлийн эргэн төлөлтийн хуваарь үүсгэх
    public void createLoanAccountRepayment(CreateRepaymentRequestPol createRepaymentRequestPol) {
        List<Object> array = new ArrayList<>();
        array.add(createRepaymentRequestPol.getAcntCode());
        array.add(createRepaymentRequestPol.getStartDate());
        array.add(createRepaymentRequestPol.getCalcAmt());
        array.add(createRepaymentRequestPol.getPayType());
        array.add(createRepaymentRequestPol.getPayFreq());
        array.add(createRepaymentRequestPol.getPayMonth());
        array.add(createRepaymentRequestPol.getPayDay1());
        array.add(createRepaymentRequestPol.getPayDay2());
        array.add(createRepaymentRequestPol.getHolidayOption());
        array.add(createRepaymentRequestPol.getShiftPartialPay());
        array.add(createRepaymentRequestPol.getShiftType());
        array.add(createRepaymentRequestPol.getTermFreeTimes());
        array.add(createRepaymentRequestPol.getIntTypeCode());
        array.add(createRepaymentRequestPol.getEndDate());
        array.add(createRepaymentRequestPol.getAdvDate());
        array.add(createRepaymentRequestPol.getDescription());
        array.add(createRepaymentRequestPol.getEscapeMonths());
        array.add(createRepaymentRequestPol.getListNrs());

        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610258");
        sendRequest(createHttpEntity(array, headers));
    }

    // endregion
    public NRSListResponseDto calculateLoanAccountRepayment(CalculateRepaymentRequest calculateRepaymentRequest) {
        List<Object> array = new ArrayList<>();
        array.add(calculateRepaymentRequest.getAcntCode());
        array.add(calculateRepaymentRequest.getStartDate());
        array.add(calculateRepaymentRequest.getCalcAmt());
        array.add(calculateRepaymentRequest.getPayType());
        array.add(calculateRepaymentRequest.getPayFreq());
        array.add(calculateRepaymentRequest.getPayMonth());
        array.add(calculateRepaymentRequest.getPayDay1());
        array.add(calculateRepaymentRequest.getPayDay2());
        array.add(calculateRepaymentRequest.getHolidayOption());
        array.add(calculateRepaymentRequest.getShiftPartialPay());
        array.add(calculateRepaymentRequest.getShiftType());
        array.add(calculateRepaymentRequest.getTermFreeTimes());
        array.add(calculateRepaymentRequest.getIntTypeCode());
        array.add(calculateRepaymentRequest.getEndDate());
        array.add(calculateRepaymentRequest.getAdvDate());
        array.add(calculateRepaymentRequest.getEscapeMonths());
        array.add(calculateRepaymentRequest.getListNrs());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610260");
        NRSListResponseDto nrsListResponseDto;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            nrsListResponseDto = mapper.readValue(responseBody, NRSListResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return nrsListResponseDto;
    }

    public DepositTdAccountResponseDto loanNonCash(LoanNonCashRequest loanNonCashRequest) {
        List<Object> array = new ArrayList<>();
        array.add(loanNonCashRequest.toJsonString());
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610262");
        return getDepositTdAccountResponseDto(array, headers);
    }

    public List<PledgeResponse> getPledgeListFromLoanAccount(String accountNo) {
        List<Object> array = new ArrayList<>();
        array.add(accountNo);
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610904");
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Pledge getPledge(String accountNo) {
        List<Object> array = new ArrayList<>();
        array.add(accountNo);
        HttpHeaders headers = setPolarisHeaders();
        headers.add("op", "13610906");
        Pledge pledge;
        String responseBody = sendRequest(createHttpEntity(array, headers));
        ObjectMapper mapper = new ObjectMapper();
        try {
            pledge = mapper.readValue(responseBody, Pledge.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return pledge;
    }

}
