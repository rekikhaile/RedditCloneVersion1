package com.riri.redditclone;

import org.springframework.data.repository.CrudRepository;

public interface RedditCloneRepository extends CrudRepository<RedditClone, Long> {
    Iterable <RedditClone> findByTitleContainingIgnoreCase(String search);
}
