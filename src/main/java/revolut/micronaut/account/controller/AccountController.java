package revolut.micronaut.account.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import revolut.micronaut.account.model.request.*;
import revolut.micronaut.account.service.AccountService;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

@Controller("/account")
public class AccountController {

    private AccountService accountService;

    @Inject
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Post("/create")
    public HttpResponse createAccount(@Body CreateRequestDto createRequestDto) {
        try {
            return HttpResponse
                    .ok(accountService.createAccount(createRequestDto));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }

    @Put("/withdraw")
    public HttpResponse withdraw(@Body WithdrawRequestDto withdrawRequestDto) {
        try {
            return HttpResponse.ok(this.accountService.withdraw(withdrawRequestDto));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }

    @Put("/deposit")
    public HttpResponse deposit(@Body DepositRequestDto depositRequestDto) {
        try {
            return HttpResponse
                    .ok(this.accountService.deposit(depositRequestDto));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }

    @Get("/balance/{id}")
    public HttpResponse accountBalance(@NotBlank String id) {
        BalanceRequestDto balanceRequestDto = new BalanceRequestDto(id);
        try {
            return HttpResponse
                    .ok(this.accountService.getBalance(balanceRequestDto));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }

    @Put("/transfer")
    public HttpResponse transfer(@Body TransferRequestDto transferRequestDto) {
        try {
            return HttpResponse
                    .ok(this.accountService.transfer(transferRequestDto));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }
}
