package com.cms.content.implementation;

import com.cms.content.dto.ContentDTO;
import com.cms.content.rest.response.ResponseBuilder;

import javax.transaction.Transactional;

public interface IContentService {
    @Transactional
    ResponseBuilder addContent(ContentDTO contentDTO);

    @Transactional
    ResponseBuilder getContent(String uuid);

    @Transactional
    ResponseBuilder updateContent(String uuid, ContentDTO contentDTO);

    @Transactional
    ResponseBuilder deleteContent(String uuid);
}
