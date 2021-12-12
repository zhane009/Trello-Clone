package com.zhane.Trello.Clone.Repositories;

import com.zhane.Trello.Clone.Models.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
}
