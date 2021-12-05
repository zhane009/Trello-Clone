package com.zhane.Trello.Clone.Controllers;

import com.zhane.Trello.Clone.Models.Checklist;
import com.zhane.Trello.Clone.Repositories.ChecklistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @PostMapping("{cardId}")
//    public List<Checklist> getByCardId(@PathVariable long cardId){
//        return checklistRepository.findByCardId(cardId);
//    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{cardId}")
    public void deleteByCardId(@PathVariable long cardId){
        checklistRepository.deleteByCardId(cardId);
    }

    @PostMapping("{cardId}")
    public List<Checklist> saveAll(@RequestBody List<Checklist> checklists){
        return checklistRepository.saveAll(checklists);
    }

}
