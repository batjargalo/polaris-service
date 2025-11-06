package mn.io.polaris.service;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import mn.io.polaris.model.polaris.AccountNo;
import mn.io.polaris.model.polaris.Pledge;
import mn.io.polaris.model.polaris.PledgeInfo;
import mn.io.polaris.model.polaris.PledgeResponse;
import mn.io.polaris.model.request.PledgeInfoByAccountRequest;
import mn.io.polaris.remote.PolarisClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PledgeService {

    @Resource
    private PolarisClient polarisClient;

    public PledgeInfo getInfoByAccount(PledgeInfoByAccountRequest pledgeInfoByAccountRequest) {
        return polarisClient.getInfoByAccount(pledgeInfoByAccountRequest);
    }

    public List<PledgeResponse> getPledgeListFromLoanAccount(AccountNo accountNo) {
        return polarisClient.getPledgeListFromLoanAccount(accountNo.getAcntCode());
    }

    public Pledge getPledge(AccountNo accountNo) {
        return polarisClient.getPledge(accountNo.getAcntCode());
    }

}
