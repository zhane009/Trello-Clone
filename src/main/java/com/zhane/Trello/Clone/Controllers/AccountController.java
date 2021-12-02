package com.zhane.Trello.Clone.Controllers;

import com.zhane.Trello.Clone.Models.Account;
import com.zhane.Trello.Clone.Repositories.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping
    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    @GetMapping("{id}")
    public Account getById(@PathVariable long id){
        return accountRepository.getOne(id);
    }

    @PostMapping
    public Account save(@RequestBody Account account){
        return accountRepository.saveAndFlush(account);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Account update(@RequestBody Account account){
        Account oldAccount = accountRepository.getOne(account.getId());
        BeanUtils.copyProperties(account, oldAccount, "verified");
        return accountRepository.saveAndFlush(oldAccount);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteById(@PathVariable long id){
        accountRepository.deleteById(id);
    }
}
