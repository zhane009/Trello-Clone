package com.zhane.Trello.Clone.Controllers;

import com.zhane.Trello.Clone.Models.Card;
import com.zhane.Trello.Clone.Repositories.CardRepository;
import com.zhane.Trello.Clone.Repositories.ListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("card")
public class CardController {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    ListRepository listRepository;

    @GetMapping
    public List<Card> getAll(){
        return cardRepository.findAll();
    }

    @GetMapping("{id}")
    public Card getById(@PathVariable long id){
        return cardRepository.getOne(id);
    }

    @PostMapping
    public Card save(@RequestBody Card card){
        return cardRepository.save(card);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Card update(@RequestBody Card card){
        Card oldCard = cardRepository.getOne(card.getId());
        BeanUtils.copyProperties(card, oldCard, "status", "id", "position");
        return cardRepository.saveAndFlush(oldCard);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable long id){
        cardRepository.deleteById(id);
    }

}
