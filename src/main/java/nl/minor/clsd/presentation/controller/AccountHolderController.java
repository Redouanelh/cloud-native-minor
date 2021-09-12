package nl.minor.clsd.presentation.controller;

import nl.minor.clsd.application.AccountHolderService;
import nl.minor.clsd.application.error.NotFoundException;
import nl.minor.clsd.application.error.MissingParameterException;
import nl.minor.clsd.presentation.AccountHolderDto;
import nl.minor.clsd.presentation.AccountHolderMapper;
import nl.minor.clsd.presentation.requests.CreateAccountHolderRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accountholder")
public class AccountHolderController {

    private final AccountHolderService accountHolderService;
    private final AccountHolderMapper accountHolderMapper;

    public AccountHolderController(AccountHolderService accountHolderService, AccountHolderMapper accountHolderMapper) {
        this.accountHolderService = accountHolderService;
        this.accountHolderMapper = accountHolderMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountHolderDto> getAccountHolder(@PathVariable int id) {
        AccountHolderDto accountHolderDto = this.accountHolderMapper.entityToAccountHolderDto(this.accountHolderService.getById(id));
        return ResponseEntity.status(HttpStatus.OK).body(accountHolderDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountHolderDto>> getAll() {
        List<AccountHolderDto> accountHolders = this.accountHolderService.findAll().stream().map(this.accountHolderMapper::entityToAccountHolderDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(accountHolders);
    }

    @PostMapping
    public ResponseEntity<AccountHolderDto> createAccountHolder(@RequestBody CreateAccountHolderRequestDto request) {
        if (request.getFirstName() == null || request.getLastName() == null) throw new MissingParameterException("You are missing a parameter.");

        AccountHolderDto accountHolderDto = this.accountHolderMapper.entityToAccountHolderDto(this.accountHolderService.saveAccountHolder(request.getFirstName(), request.getLastName()));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountHolderDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteAccountHolder(@PathVariable int id) {
        if (this.accountHolderService.findById(id) == null) throw new NotFoundException("Accountholder with this id was not found, no need to delete it.");
        return ResponseEntity.status(HttpStatus.OK).body(this.accountHolderService.deleteById(id));
    }

}
