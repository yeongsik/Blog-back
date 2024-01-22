package com.qdev.domain.post.service;

import com.qdev.domain.post.dto.request.CreatePostFileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostFileService {
    void saveFile(CreatePostFileRequest postFileRequest);

    void saveFiles(MultipartFile[] attachFiles);
}
