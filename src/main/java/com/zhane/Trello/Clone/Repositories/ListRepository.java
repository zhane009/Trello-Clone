package com.zhane.Trello.Clone.Repositories;

import com.zhane.Trello.Clone.Models.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {
}
