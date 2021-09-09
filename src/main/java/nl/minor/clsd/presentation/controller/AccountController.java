package nl.minor.clsd.presentation.controller;

import nl.minor.clsd.application.AccountService;
import nl.minor.clsd.presentation.AccountDto;
import nl.minor.clsd.presentation.AccountMapper;
import nl.minor.clsd.presentation.requests.CreateAccountRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    // POST en doe met requestbody.Created(hierin de gemaakte object)
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequestDto request) {
        // kan pas account maken als er een user is die bestaat, dus hierin de accountservice aanroepen en dan daarbinnen
        // de accoundholder service doen om te kijken of user bestaat zonee dan throw exception en anders maak account aan
        // met de meegegeven accountholder id
        // dan bij het aanmaken van account de iban genereren
        return null;
    }

    // DELETE en doe met requestbody.ok 200 dus account/{id}
    @DeleteMapping("{id}")
    public ResponseEntity deleteAccount(@PathVariable int id) {
        return null;
    }

    // Kunnen blokkeren van account

    // Toevoegen van nog een rekeninghouder aan een account
}
