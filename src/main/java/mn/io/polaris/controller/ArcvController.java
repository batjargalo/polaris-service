package mn.io.polaris.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mn.io.polaris.model.request.*;
import mn.io.polaris.model.response.ArcvNonCashResponse;
import mn.io.polaris.service.ArcvService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Arcv", description = "Авлага")
@RequestMapping(path = "/arcv", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArcvController {
    @Resource
    private ArcvService arcvService;

    @PostMapping(path = "/payment/noncash")
    @Operation(summary = "Авлагын модуль гүйлгээ бэлэн бус гүйлгээ")
    public ArcvNonCashResponse payArcvTransaction(@RequestBody @Valid ArcvNonCashPayRequest arcvNonCashPayRequest) {
        return arcvService.payArcvTransaction(arcvNonCashPayRequest);
    }
}
