package nl.minor.clsd.presentation.controller;

import nl.minor.clsd.application.AccountHolderService;
import nl.minor.clsd.domain.entity.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class AccountHolderController {

    private final AccountHolderService accountHolderService;

    public AccountHolderController(AccountHolderService accountHolderService) { this.accountHolderService = accountHolderService; }
}
