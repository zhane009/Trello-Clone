package com.zhane.Trello.Clone.Controllers;

import com.zhane.Trello.Clone.Models.Label;
import com.zhane.Trello.Clone.Repositories.LabelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("label")
public class LabelController {

    @Autowired
    LabelRepository labelRepository;

    @GetMapping
    public List<Label> getAll(){
        return labelRepository.findAll();
    }

    @GetMapping("{id}")
    public Label getOne(@PathVariable long id){
        return labelRepository.getOne(id);
    }

    @PostMapping
    public Label save(@RequestBody Label label){
        return labelRepository.saveAndFlush(label);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Label update(@RequestBody Label label){
        Label oldLabel = labelRepository.getOne(label.getId());
        BeanUtils.copyProperties(label, oldLabel, "id");
        return labelRepository.saveAndFlush(oldLabel);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteById(@PathVariable long id){
        labelRepository.deleteById(id);
    }
}
