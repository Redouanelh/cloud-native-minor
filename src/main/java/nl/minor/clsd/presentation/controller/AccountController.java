package nl.minor.clsd.presentation.controller;

import nl.minor.clsd.application.AccountService;
import nl.minor.clsd.application.error.NotFoundException;
import nl.minor.clsd.application.error.MissingParameterException;
import nl.minor.clsd.presentation.AccountDto;
import nl.minor.clsd.presentation.AccountMapper;
import nl.minor.clsd.presentation.requests.CreateAccountRequestDto;
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

    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("{id}") //TODO: later iban van maken?
    public ResponseEntity<AccountDto> getAccountHolder(@PathVariable int id) {
        AccountDto accountDto = this.accountMapper.entityToAccountDto(this.accountService.findById(id));
        if (accountDto == null) throw new NotFoundException("Accountholder with this id was not found.");

        return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        List<AccountDto> accounts = this.accountService.findAll().stream().map(this.accountMapper::entityToAccountDto).collect(Collectors.toList());
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

    // als user aan account toevoeg check van tevoren of die user er al nie in zit

    // DELETE en doe met requestbody.ok 200 dus account/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable int id) {
        return null;
    }

    // Kunnen blokkeren van account

    // Toevoegen van nog een rekeninghouder aan een account
}
