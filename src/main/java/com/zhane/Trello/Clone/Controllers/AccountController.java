package com.zhane.Trello.Clone.Controllers;

import com.zhane.Trello.Clone.Models.Account;
import com.zhane.Trello.Clone.Repositories.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

    @GetMapping("{username}")
    public Account getById(@PathVariable String username){
        return accountRepository.findByUsername(username);
    }

    @PostMapping
    public Account save(@RequestBody Account account){
        return accountRepository.saveAndFlush(account);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Account update(@RequestBody Account account){
        Account oldAccount = accountRepository.findByUsername(account.getUsername());
        BeanUtils.copyProperties(account, oldAccount, "verified");
        return accountRepository.saveAndFlush(oldAccount);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "{username}")
    public void deleteById(@PathVariable String username){
        accountRepository.deleteByUsername(username);
    }
}
