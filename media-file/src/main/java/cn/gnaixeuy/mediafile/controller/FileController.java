package cn.gnaixeuy.mediafile.controller;

import cn.gnaixeuy.mediacommon.entity.File;
import cn.gnaixeuy.mediacommon.vo.ResponseResult;
import cn.gnaixeuy.mediafile.dto.request.FileUploadRequest;
import cn.gnaixeuy.mediafile.mapper.FileMapper;
import cn.gnaixeuy.mediafile.mapper.FileUploadMapper;
import cn.gnaixeuy.mediafile.service.FileService;
import cn.gnaixeuy.mediafile.vo.FileVo;
import cn.gnaixeuy.mediafile.vo.UploadTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.AntPathMatcher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * <img src="http://blog.gnaixeuy.cn/wp-content/uploads/2022/09/倒闭.png"/>
 *
 * <p>项目： media-platform </p>
 * 创建日期： 2022/11/30
 *
 * @author GnaixEuy
 * @version 1.0.0
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */

@RestController
@RequestMapping("/file")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
public class FileController {
    private FileService fileService;

    private FileMapper fileMapper;

    private FileUploadMapper fileUploadMapper;

    @PostMapping("/upload_init")
    //TODO  等待优化
    public ResponseResult<HashMap<String, UploadTokenResponse>> initUpload(@Validated @RequestBody FileUploadRequest fileUploadRequest) throws IOException {
        UploadTokenResponse uploadTokenResponse = fileService.initUpload(fileUploadRequest);
        HashMap<String, UploadTokenResponse> stringUploadTokenResponseHashMap = new HashMap<>();
        stringUploadTokenResponseHashMap.put("tokens", uploadTokenResponse);
        return ResponseResult.success(stringUploadTokenResponseHashMap);
    }

    @PostMapping("/{id}/upload_finish")
    public FileVo finishUpload(@PathVariable String id) {
        return fileMapper.toVo(fileService.finishUpload(id));
    }


    @GetMapping(value = {"/getFileInfoByKey/{id}/**"})
    public ResponseResult<File> getFileInfoByKey(HttpServletRequest request, @PathVariable String id) {
        String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        String key = "/" + id + "/" + new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
        return ResponseResult.success(this.fileService.getFileEntityByKey(key));
    }

    @GetMapping(value = {"/getFileInfoByName/{name}"})
    public ResponseResult<File> getFileInfoByName(@PathVariable String name) {
        return ResponseResult.success(this.fileService.getFileEntityByName(name));
    }


    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Autowired
    public void setFileUploadMapper(FileUploadMapper fileUploadMapper) {
        this.fileUploadMapper = fileUploadMapper;
    }

}
