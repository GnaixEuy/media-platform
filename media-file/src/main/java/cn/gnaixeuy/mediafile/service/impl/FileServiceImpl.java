package cn.gnaixeuy.mediafile.service.impl;

import cn.gnaixeuy.mediacommon.entity.File;
import cn.gnaixeuy.mediacommon.entity.User;
import cn.gnaixeuy.mediacommon.enums.ExceptionType;
import cn.gnaixeuy.mediacommon.enums.FileStatus;
import cn.gnaixeuy.mediacommon.enums.Storage;
import cn.gnaixeuy.mediacommon.exception.BizException;
import cn.gnaixeuy.mediacommon.utils.FileTypeTransformer;
import cn.gnaixeuy.mediafile.dto.FileDto;
import cn.gnaixeuy.mediafile.dto.FileUploadDto;
import cn.gnaixeuy.mediafile.dto.request.FileUploadRequest;
import cn.gnaixeuy.mediafile.mapper.FileMapper;
import cn.gnaixeuy.mediafile.repository.FileRepository;
import cn.gnaixeuy.mediafile.service.FileService;
import cn.gnaixeuy.mediafile.service.StorageService;
import cn.gnaixeuy.mediafile.vo.UploadTokenResponse;
import cn.gnaixeuy.mediafile.vo.UploadTokenTokensHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
@Service
public class FileServiceImpl implements FileService {

    private Map<String, StorageService> storageServices;

    private FileRepository repository;

    private FileMapper mapper;

    @Override
    @Transactional
    public UploadTokenResponse initUpload(FileUploadRequest fileUploadRequest) throws IOException {
        User currentUserEntity = this.getCurrentUserEntity();
        fileUploadRequest.setKey("/" + currentUserEntity.getId() + "/" + System.currentTimeMillis() + "/" + fileUploadRequest.getKey() + "." + fileUploadRequest.getExt());
        fileUploadRequest.setName(fileUploadRequest.getName().substring(fileUploadRequest.getName().lastIndexOf("/") + 1));
        // 创建File实体
        File file = mapper.createEntity(fileUploadRequest);
        file.setType(FileTypeTransformer.getFileTypeFromExt(fileUploadRequest.getExt()));
        file.setStorage(getDefaultStorage());
        file.setCreatedBy(currentUserEntity);
        file.setUpdatedBy(currentUserEntity);
        File savedFile = repository.save(file);
        // 通过接口获取STS令牌
        FileUploadDto fileUploadDto = storageServices.get(getDefaultStorage().name()).initFileUpload(file.getKey());

        fileUploadDto.setKey(savedFile.getKey());
        fileUploadDto.setFileId(savedFile.getId());

        UploadTokenResponse uploadTokenResponse = new UploadTokenResponse();
        uploadTokenResponse.setFileId(fileUploadDto.getFileId());
        uploadTokenResponse.setUploadUrl(fileUploadDto.getSignUrl());
        uploadTokenResponse.setEffectUrl(fileUploadDto.getKey());
        uploadTokenResponse.setHeaders(new UploadTokenTokensHeaders());
        uploadTokenResponse.setPhotoResultUrl(this.storageServices.get(getDefaultStorage().name()).getFileUri(fileUploadDto.getKey()));
        return uploadTokenResponse;
    }

    private User getCurrentUserEntity() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public FileDto finishUpload(String id) {
        File file = getFileEntity(id);
        // Todo: 是否是SUPER_ADMIN
        if (!Objects.equals(file.getCreatedBy().getId(), getCurrentUserEntity().getId())) {
            throw new BizException(ExceptionType.FILE_NOT_PERMISSION);
        }

        // Todo: 验证远程文件是否存在

        file.setStatus(FileStatus.UPLOADED);
        return mapper.toDto(repository.save(file));
    }

    // Todo: 后台设置当前Storage
    public Storage getDefaultStorage() {
        return Storage.COS;
    }

    @Override
    public File getFileEntity(String id) {
        Optional<File> fileOptional = repository.findById(id);
        if (fileOptional.isEmpty()) {
            throw new BizException(ExceptionType.FILE_NOT_FOUND);
        }
        return fileOptional.get();
    }

    @Override
    public File getFileEntityByKey(String key) {
        Optional<File> byKey = this.repository.findByKey(key);
        if (byKey.isEmpty()) {
            throw new BizException(ExceptionType.FILE_NOT_FOUND);
        }
        return byKey.get();
    }

    @Override
    public File getFileEntityByName(String name) {
        Optional<File> byKey = this.repository.findByNameContaining(name);
        if (byKey.isEmpty()) {
            throw new BizException(ExceptionType.FILE_NOT_FOUND);
        }
        return byKey.get();
    }


    @Autowired
    public void setStorageServices(Map<String, StorageService> storageServices) {
        this.storageServices = storageServices;
    }

    @Autowired
    public void setRepository(FileRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(FileMapper mapper) {
        this.mapper = mapper;
    }
}
