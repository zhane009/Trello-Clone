package com.zhane.Trello.Clone.Repositories;

import com.zhane.Trello.Clone.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
