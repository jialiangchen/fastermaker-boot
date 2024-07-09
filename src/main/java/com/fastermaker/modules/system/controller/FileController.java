package com.fastermaker.modules.system.controller;

import com.fastermaker.common.result.Result;
import com.fastermaker.modules.system.model.dto.FileInfo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制层
 *
 *
 *
 */
@Tag(name = "文件接口")
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    //private final OssService ossService;

    @PostMapping
    @Operation(summary = "文件上传")
    public Result<FileInfo> uploadFile(
            @Parameter(description = "表单文件对象") @RequestParam(value = "file") MultipartFile file
    ) {
      //  FileInfo fileInfo = ossService.uploadFile(file);
        return Result.success();//fileInfo
    }

    @DeleteMapping
    @Operation(summary = "文件删除")
    @SneakyThrows
    public Result deleteFile(
            @Parameter(description = "文件路径") @RequestParam String filePath
    ) {
       // boolean result = ossService.deleteFile(filePath);
        return Result.judge(true);//result
    }
}
