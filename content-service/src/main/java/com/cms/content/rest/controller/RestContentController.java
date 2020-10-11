package com.cms.content.rest.controller;

import com.cms.content.dto.ContentDTO;

import com.cms.content.implementation.IContentService;
import com.cms.content.rest.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/content")
public class RestContentController {

    @Autowired
    private IContentService contentService;

    @PostMapping
    public ResponseEntity<ResponseBuilder> addContent(@RequestBody ContentDTO contentDTO){
        ResponseBuilder responseBuilder = contentService.addContent(contentDTO);

        return ResponseEntity.ok(responseBuilder);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ResponseBuilder> getContent(@PathVariable("uuid") String uuid){
        ResponseBuilder responseBuilder = contentService.getContent(uuid);

        return ResponseEntity.ok(responseBuilder);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ResponseBuilder> getContent(@PathVariable("uuid") String uuid, @RequestBody ContentDTO contentDTO){
        ResponseBuilder responseBuilder = contentService.updateContent(uuid, contentDTO);

        return ResponseEntity.ok(responseBuilder);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ResponseBuilder> deleteContent(@PathVariable("uuid") String uuid){
        ResponseBuilder responseBuilder = contentService.deleteContent(uuid);

        return ResponseEntity.ok(responseBuilder);
    }
}
