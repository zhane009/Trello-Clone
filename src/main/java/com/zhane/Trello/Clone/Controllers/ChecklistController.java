package com.zhane.Trello.Clone.Controllers;

import com.zhane.Trello.Clone.Models.Card;
import com.zhane.Trello.Clone.Models.Checklist;
import com.zhane.Trello.Clone.Repositories.ChecklistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("checklist")
public class ChecklistController {
    @Autowired
    ChecklistRepository checklistRepository;

    @PostMapping
    public Checklist save(@RequestBody Checklist checklist){
        return checklistRepository.saveAndFlush(checklist);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Checklist update(@RequestBody Checklist checklist){
        Checklist oldChecklist = checklistRepository.getOne(checklist.getId());
        BeanUtils.copyProperties(checklist, oldChecklist, "id", "cardId", "position", "title");
        return checklistRepository.saveAndFlush(oldChecklist);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteById(@PathVariable long id){
        checklistRepository.deleteById(id);
    }

    @GetMapping("{cardId}")
    public List<Checklist> getByCardId(@PathVariable long cardId){
        return checklistRepository.findByCardId(cardId);
    }

    @PostMapping("{cardId}")
    public List<Checklist> saveAll(@RequestBody List<Checklist> checklists){
        return checklistRepository.saveAll(checklists);
    }

    @PostMapping(value = "checked")
    public Checklist changeChecked(@RequestBody Map<String, Long> payload){
        Checklist checklist = checklistRepository.getOne(payload.get("checklistId"));
        Short checked = Short.parseShort(payload.get("checked").toString());
        checklist.setChecked(checked);
        return checklistRepository.saveAndFlush(checklist);
    }



}
