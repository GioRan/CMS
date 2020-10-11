package com.cms.content.service;

import com.cms.content.dao.IContentDao;
import com.cms.content.dto.ContentDTO;
import com.cms.content.dto.ElementDTO;
import com.cms.content.entity.ContentEntity;
import com.cms.content.entity.ElementEntity;
import com.cms.content.implementation.IContentService;
import com.cms.content.rest.response.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.content.utilities.Constants;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("contentService")
public class ContentService implements IContentService {

    @Autowired
    private IContentDao contentDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseBuilder responseBuilder;

    @Override
    @Transactional
    public ResponseBuilder addContent(ContentDTO contentDTO){
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setElements(new ArrayList<>());

        try {
            for (ElementDTO elementDTO: contentDTO.getElements()) {
                ElementEntity elementEntity = modelMapper.map(elementDTO, ElementEntity.class);
                elementEntity.setElementUuid(UUID.randomUUID().toString());
                elementEntity.setContent(contentEntity);
                contentEntity.getElements().add(elementEntity);
            }

            contentEntity = contentDao.save(contentEntity);

            contentDTO = modelMapper.map(contentEntity, ContentDTO.class);

            responseBuilder.set(Constants.CONTENT_ADDED, contentDTO);
        } catch (Exception ex){
            ex.printStackTrace();
            responseBuilder.set(Constants.SOMETHING_WENT_WRONG, null);
        }

        return responseBuilder;
    }

    @Override
    @Transactional
    public ResponseBuilder getContent(String uuid){
        try {
            ContentEntity contentEntity = contentDao.findByContentUuid(uuid);

            if (contentEntity != null){
                ContentDTO contentDTO = modelMapper.map(contentEntity, ContentDTO.class);

                responseBuilder.set(Constants.CONTENT_GET, contentDTO);
            } else {
                responseBuilder.set(Constants.CONTENT_GET_NOT_FOUND, null);
            }
        } catch (Exception ex){
            ex.printStackTrace();
            responseBuilder.set(Constants.SOMETHING_WENT_WRONG, null);
        }

        return responseBuilder;
    }

    @Override
    @Transactional
    public ResponseBuilder updateContent(String uuid, ContentDTO contentDTO){
        try{
            ContentEntity contentEntity = contentDao.findByContentUuid(uuid);

            if (contentEntity != null){
                Integer elementDTOSize = contentDTO.getElements().size();
                Integer elementEntitySize = contentEntity.getElements().size();

                if (elementDTOSize == 0){
                    contentEntity.getElements().clear();
                } else {
                    if (elementEntitySize > elementDTOSize){
                        List<String> uniqueIds = contentDTO.getElements().stream().map(elementDTO -> elementDTO.getElementUuid()).collect(Collectors.toList());

                        contentEntity.getElements().removeIf(elementEntity -> !uniqueIds.contains(elementEntity.getElementUuid()));
                    }

                    if (elementDTOSize > elementEntitySize){
                        for (ElementDTO elementDTO: contentDTO.getElements()){
                            if (elementDTO.getElementUuid() == null){
                                ElementEntity elementEntity = modelMapper.map(elementDTO, ElementEntity.class);
                                elementEntity.setElementUuid(UUID.randomUUID().toString());
                                elementEntity.setContent(contentEntity);
                                contentEntity.getElements().add(elementEntity);
                            }
                        }
                    }

                    for (ElementEntity elementEntity: contentEntity.getElements()){
                        for (ElementDTO elementDTO: contentDTO.getElements()){
                            if (elementEntity.getElementUuid() != null && elementDTO.getElementUuid() != null){
                                if (elementEntity.getElementUuid().equals(elementDTO.getElementUuid())){
                                    elementEntity.setType(elementDTO.getType());
                                    elementEntity.setValue(elementDTO.getValue());
                                    elementEntity.setClassName(elementDTO.getClassName());
                                    elementEntity.setIdName(elementDTO.getIdName());
                                    elementEntity.setX(elementDTO.getX());
                                    elementEntity.setY(elementDTO.getY());
                                }
                            }
                        }
                    }
                }

                contentEntity = contentDao.saveAndFlush(contentEntity);

                contentDTO = modelMapper.map(contentEntity, ContentDTO.class);

                responseBuilder.set(Constants.CONTENT_UPDATE, contentDTO);
            } else {
                responseBuilder.set(Constants.CONTENT_GET_NOT_FOUND, null);
            }
        } catch (Exception ex){
            ex.printStackTrace();
            responseBuilder.set(Constants.SOMETHING_WENT_WRONG, null);
        }

        return responseBuilder;
    }

    @Override
    @Transactional
    public ResponseBuilder deleteContent(String uuid){
        try {
            ContentEntity contentEntity = contentDao.findByContentUuid(uuid);

            if(contentEntity != null){
                contentDao.delete(contentEntity);
                responseBuilder.set(Constants.CONTENT_DELETE, null);
            } else {
                responseBuilder.set(Constants.CONTENT_GET_NOT_FOUND, null);
            }
        } catch (Exception ex){
            ex.printStackTrace();
            responseBuilder.set(Constants.SOMETHING_WENT_WRONG, null);
        }

        return responseBuilder;
    }
}
