package com.cms.content.dao;

import com.cms.content.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("contentDao")
public interface IContentDao extends JpaRepository<ContentEntity, Integer> {
    ContentEntity findByContentUuid(String uuid);
}
