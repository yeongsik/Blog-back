package com.qdev.domain.post.service;

import com.qdev.domain.post.dto.request.CreatePostFileRequest;
import com.qdev.domain.post.util.PostFileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostFileServiceImpl implements PostFileService {

    @Override
    public void saveFiles(MultipartFile[] attachFiles) {
        for (MultipartFile attachFile : attachFiles) {
            PostFileUtils.saveFile(attachFile);
            saveFile(PostFileUtils.toPostFileDto(attachFile));
        }
    }

    @Override
    public void saveFile(CreatePostFileRequest postFileRequests) {

    }
}
