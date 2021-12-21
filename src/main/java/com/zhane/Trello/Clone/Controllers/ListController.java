package com.zhane.Trello.Clone.Controllers;

import com.zhane.Trello.Clone.Models.Card;
import com.zhane.Trello.Clone.Models.List;
import com.zhane.Trello.Clone.Repositories.CardRepository;
import com.zhane.Trello.Clone.Repositories.ListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("list")
public class ListController {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    ListRepository listRepository;

    @GetMapping
    public java.util.List<List> findAll(){
        return listRepository.findAll();
    }

    @GetMapping("{id}")
    public List getOne(@PathVariable long id){
        return listRepository.getOne(id);
    }

    @PostMapping
    public List save(@RequestBody List list){
        return listRepository.saveAndFlush(list);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public List update(@RequestBody List list){
        List oldList = listRepository.getById(list.getId());
        BeanUtils.copyProperties(list, oldList, "position", "status");
        return listRepository.saveAndFlush(oldList);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable long id){
        listRepository.deleteById(id);
    }

    @PostMapping(value = "change-status")
    public List changeStatus(@RequestBody Map<String,Long> payload){
        List list = listRepository.getOne(payload.get("listId"));
        list.setStatus(Integer.parseInt(payload.get("status").toString()));
        return listRepository.saveAndFlush(list);
    }


}
