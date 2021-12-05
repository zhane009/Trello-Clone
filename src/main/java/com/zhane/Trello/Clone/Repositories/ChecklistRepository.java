package com.zhane.Trello.Clone.Repositories;

import com.zhane.Trello.Clone.Models.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    public List<Checklist> findByCardId(long cardId);
    public void deleteByCardId(long cardId);
}
