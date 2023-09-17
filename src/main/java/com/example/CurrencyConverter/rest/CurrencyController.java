package com.example.CurrencyConverter.rest;

import com.example.CurrencyConverter.entity.Info;
import com.example.CurrencyConverter.entity.Root;
import com.example.CurrencyConverter.entity.Transaction;
import com.example.CurrencyConverter.helpers.FormInput;
import com.example.CurrencyConverter.service.TransactionService;
import com.example.CurrencyConverter.xml.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/conversion")
public class CurrencyController {


    TransactionService transactionService;
    private static final String API_BASE_URL = "https://api.apilayer.com/exchangerates_data/convert?to=PLN&from=%s&amount=%.2f&date=%s";
    private static final String API_KEY = "1L5PHfSr6Er2niN8rUb16SkRYA2uuBxU";


    @Autowired
    public CurrencyController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/result")
    public String result(@ModelAttribute("transaction") Transaction transaction, Model model) {
        if (!isValidTransactionDate(transaction.getDate())) {
            return "redirect:/conversion/form";
        }

        try {
            String dateFormatted = formatDate(transaction.getDate());
            String apiEndpoint = buildApiEndpoint(transaction.getCurrencyCode(), transaction.getCostCurrency(),
                    dateFormatted);
            Root root = fetchExchangeRate(apiEndpoint);

            processExchangeRateResponse(transaction, root);
            transactionService.save(transaction);
            model.addAttribute("transaction", transaction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/conversion/list";
    }

    private void processExchangeRateResponse(Transaction transaction, Root root) {
        Info info = root.getInfo();
        transaction.setBid(info.getRate());
        transaction.setCostPln(root.getResult());
    }

    private Root fetchExchangeRate(String apiEndpoint) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(apiEndpoint)
                .addHeader("apikey", API_KEY)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Root.class);
        }
    }

    private boolean isValidTransactionDate(Date transactionDate) {
        return transactionDate.before(Timestamp.valueOf(LocalDateTime.now()));
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    private String buildApiEndpoint(String currencyCode, Double costCurrency, String dateFormatted) {
        return String.format(API_BASE_URL,
                currencyCode, costCurrency, dateFormatted);
    }


    @GetMapping("/form")
    public String convert(Model model) {

        Transaction transaction = new Transaction();

        model.addAttribute("transaction", transaction);

        return "conversion-form";
    }


    @GetMapping("/list")
    public String showList(Model model) {
        List<Transaction> transactions = transactionService.findAll();

        model.addAttribute("transactions", transactions);

        //handling potential input
        model.addAttribute("formInput", new FormInput());
        return "purchases-list";
    }

    @PostMapping("/list")
    public String searchPurchases(@ModelAttribute FormInput formInput, Model model) {

        List<Transaction> transactions = transactionService.findByItemNameContaining(formInput.getContent());

        model.addAttribute("transactions", transactions);

        return "purchases-list";

    }

    @PostMapping("/searchDate")
    public String searchPurchasesByDate(@ModelAttribute FormInput formInput, Model model) {

        //if no date was input then redirect to all purchases
        if (formInput.getDate() == null) {
            List<Transaction> transactions = transactionService.findAll();
            return "redirect:/conversion/list";
        }

        List<Transaction> transactions = transactionService.findAllByDate(formInput.getDate());

        model.addAttribute("transactions", transactions);

        return "purchases-list";

    }

    @GetMapping("/invoice")
    public @ResponseBody Invoice getInvoice(@RequestParam int transactionId) {

        Transaction transaction = transactionService.findById(transactionId);

        Invoice invoice = new Invoice();
        invoice.setItemName(transaction.getItemName());
        invoice.setDate(transaction.getDate());
        invoice.setId(transaction.getId());
        invoice.setCostPln(transaction.getCostPln());
        invoice.setCurrencyCode(transaction.getCurrencyCode());
        invoice.setCostCurrency(transaction.getCostCurrency());


        return invoice;
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("transactionId") int id) {

        transactionService.deleteById(id);

        return "redirect:/conversion/list";
    }


}
