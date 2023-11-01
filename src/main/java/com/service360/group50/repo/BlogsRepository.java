package com.service360.group50.repo;

import com.service360.group50.entity.Blogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogsRepository extends CrudRepository<Blogs,Long> {
    @Query("SELECT b FROM Blogs b where b.serviceproviders.userid = :serviceProviderId")
    List<Blogs> findAllByServiceProviderId(@Param("serviceProviderId") Long serviceProviderId);

    @Query("SELECT b.blogimages FROM Blogs b where b.blogid = :blogId")
    String findBlogImagesByBlogId(@Param("blogId") Long blogId);
}
