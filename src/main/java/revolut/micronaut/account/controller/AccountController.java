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
    public HttpResponse createAccount(@Body CreateRequest createRequest) {
        try {
            return HttpResponse
                    .ok(accountService.createAccount(createRequest));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }

    @Put("/withdraw")
    public HttpResponse withdraw(@Body WithdrawRequest withdrawRequest) {
        try {
            return HttpResponse.ok(this.accountService.withdraw(withdrawRequest));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }

    @Put("/deposit")
    public HttpResponse deposit(@Body DepositRequest depositRequest) {
        try {
            return HttpResponse
                    .ok(this.accountService.deposit(depositRequest));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }

    @Get("/balance/{id}")
    public HttpResponse accountBalance(@NotBlank String id) {
        BalanceRequest balanceRequest = new BalanceRequest(id);
        try {
            return HttpResponse
                    .ok(this.accountService.getBalance(balanceRequest));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }

    @Put("/transfer")
    public HttpResponse transfer(@Body TransferRequest transferRequest) {
        try {
            return HttpResponse
                    .ok(this.accountService.transfer(transferRequest));
        } catch (IllegalArgumentException e) {
            return HttpResponse.badRequest(e.getMessage());
        }
    }
}
