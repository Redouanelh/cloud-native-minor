package nl.minor.clsd.presentation.controller;

import nl.minor.clsd.application.AccountHolderService;
import nl.minor.clsd.application.AccountService;
import nl.minor.clsd.application.error.NotFoundException;
import nl.minor.clsd.application.error.MissingParameterException;
import nl.minor.clsd.presentation.AccountDto;
import nl.minor.clsd.presentation.AccountHolderDto;
import nl.minor.clsd.presentation.AccountHolderMapper;
import nl.minor.clsd.presentation.AccountMapper;
import nl.minor.clsd.presentation.requests.CreateAccountRequestDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final AccountHolderService accountHolderService;
    private final AccountHolderMapper accountHolderMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper, AccountHolderService accountHolderService, AccountHolderMapper accountHolderMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.accountHolderService = accountHolderService;
        this.accountHolderMapper = accountHolderMapper;
    }

    @GetMapping("{iban}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable String iban) {
        AccountDto accountDto = this.accountMapper.entityToAccountDto(this.accountService.findByIban(iban));
        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        List<AccountDto> accounts = this.accountService.findAll().stream().map(this.accountMapper::entityToAccountDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @GetMapping("holder/{id}")
    public ResponseEntity<List<AccountDto>> getAllFromHolder(@PathVariable int id) {
        List<AccountDto> accounts = this.accountService.findAllByHolder(id).stream().map(this.accountMapper::entityToAccountDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequestDto request) {
        if (request.getCountryCode() == null) throw new MissingParameterException("Missing parameter countryCode.");
        if (request.getBankCode() == null) throw new MissingParameterException("Missing parameter bankCode.");
        if (request.getAccountNr() == null) throw new MissingParameterException("Missing parameter accountNr.");

        AccountDto accountDto = this.accountMapper.entityToAccountDto(this.accountService.saveAccount(request.getCountryCode(), request.getBankCode(), request.getAccountNr()));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @PutMapping("{iban}/block}")
    public ResponseEntity<AccountDto> blockAccount(@PathVariable String iban) {
        AccountDto accountDto = this.accountMapper.entityToAccountDto(this.accountService.blockAccount(iban));
        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

    @PutMapping("{iban}/holder/add/{id}")
    public ResponseEntity<AccountDto> addAccountHolder(@PathVariable String iban, @PathVariable int id) {
        AccountDto accountDto = this.accountMapper.entityToAccountDto(this.accountService.addAccountHolder(iban, id));
        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

    @PutMapping("{iban}/holder/remove/{id}")
    public ResponseEntity<AccountDto> removeAccountHolder(@PathVariable String iban, @PathVariable int id) {
        AccountDto accountDto = this.accountMapper.entityToAccountDto(this.accountService.removeAccountHolder(iban, id));
        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

    @DeleteMapping("{iban}")
    public ResponseEntity<Integer> deleteAccount(@PathVariable String iban) {
        AccountDto accountDto = this.accountMapper.entityToAccountDto(this.accountService.findByIban(iban));
        if (accountDto == null) throw new NotFoundException(String.format("Account with iban %s was not found, no need to delete it.", iban));
        return ResponseEntity.status(HttpStatus.OK).body(this.accountService.deleteByIban(iban));
    }

}
