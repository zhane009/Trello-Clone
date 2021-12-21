package com.zhane.Trello.Clone.Controllers;

import com.zhane.Trello.Clone.Models.Account;
import com.zhane.Trello.Clone.Models.Card;
import com.zhane.Trello.Clone.Models.Checklist;
import com.zhane.Trello.Clone.Models.Label;
import com.zhane.Trello.Clone.Repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.ObjectName;
import javax.transaction.Transactional;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("card")
public class CardController {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    ListRepository listRepository;

    @Autowired
    ChecklistRepository checklistRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LabelRepository labelRepository;

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

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "{cardId}/checklist")
    public void deleteByCardId(@PathVariable long cardId){
        checklistRepository.deleteByCardId(cardId);
    }

    @PostMapping(value = "member")
    public Card addMember(@RequestBody Map<String, Object> payload){
        Card card = cardRepository.getOne(Long.parseLong(payload.get("cardId").toString()));
        Set<Account> members = card.getMembers();
        if (members == null) {
            members = new HashSet<>();
        }
        members.add(accountRepository.findByUsername(payload.get("accountUsername").toString()));
        card.setMembers(members);
        return cardRepository.saveAndFlush(card);
    }

    @PostMapping(value = "label")
    public Card addLabel(@RequestBody Map<String, Object> payload){
        Card card = cardRepository.getOne(Long.parseLong(payload.get("cardId").toString()));
        Set<Label> labels = card.getLabels();
        if (labels == null){
            labels = new HashSet<>();
        }
        labels.add(labelRepository.getOne(Long.parseLong(payload.get("labelId").toString())));
        card.setLabels(labels);
        return cardRepository.saveAndFlush(card);
    }

    @DeleteMapping(value = "member")
    public Card deleteMember(@RequestBody Map<String, Object> payload){
        Card card = cardRepository.getOne(Long.parseLong(payload.get("cardId").toString()));
        Set<Account> members = card.getMembers();
        if (members == null) {
            members = new HashSet<>();
        }
        members.removeIf(account -> account.getUsername().equals(payload.get("accountUsername").toString()));
        card.setMembers(members);
        return cardRepository.saveAndFlush(card);
    }

    @DeleteMapping(value = "label")
    public Card deleteLabel(@RequestBody Map<String, Long> payload){
        Card card = cardRepository.getOne(payload.get("cardId"));
        Set<Label> labels = card.getLabels();
        if (labels == null){
            labels = new HashSet<>();
        }
        labels.removeIf(label -> label.getId() == payload.get("labelId"));
        card.setLabels(labels);
        return cardRepository.saveAndFlush(card);
    }

    @PostMapping(value = "change-status")
    public Card changeStatus(@RequestBody Map<String,Long> payload){
         Card card = cardRepository.getOne(payload.get("cardId"));
         card.setStatus(Integer.parseInt(payload.get("status").toString()));
         return cardRepository.saveAndFlush(card);
    }

    @PostMapping(value = "reorder-checklist")
    public Card reorderChecklist(@RequestBody Map<String, Long> payload){
        //not working yet
        Card card = cardRepository.getOne(payload.get("cardId"));
        List<Long> checklistIds = Collections.singletonList(payload.get("checklistIds"));
        List<Long> positions = Collections.singletonList(payload.get("checklistPositions"));
        List<Checklist> checklists = new ArrayList<>();

        for (int i = 0; i <= checklistIds.size(); i++) {
            checklists.add(checklistRepository.getOne((checklistIds.get(i))));
            Checklist chck = checklists.get(i);
            chck.setPosition(Short.parseShort(positions.get(i).toString()));
        }
        card.setChecklists(checklists);
        return cardRepository.saveAndFlush(card);
    }

    @PostMapping(value = "change-list")
    public Card changeList(@RequestBody Map<String, Long> payload){
        Card card = cardRepository.getOne(payload.get("cardId"));
        com.zhane.Trello.Clone.Models.List list = listRepository.getOne(payload.get("listId"));
        card.setList(list);
        return cardRepository.saveAndFlush(card);
    }

}
