package mn.io.polaris.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import mn.io.polaris.model.dao.CodeDao;
import mn.io.polaris.model.request.CodeRequest;
import mn.io.polaris.service.CodeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@Tag(name = "Code", description = "Кодын лавлах")
@RequestMapping(path = "/code", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodeController {

    @Resource
    private CodeService codeService;

    @GetMapping()
    @Operation(summary = "Хүснэгтийн нэрээр хайж бүх идэвхтэй кодын жагсаалтыг лавлах")
    public List<CodeDao> findAllByTableName(@RequestBody @Valid CodeRequest codeRequest) {
        return codeService.findAllByTableName(codeRequest.getTableName());
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Дугаараар кодыг лавлах")
    public CodeDao findById(@PathVariable @Valid BigInteger id) {
        return codeService.findById(id);
    }

}