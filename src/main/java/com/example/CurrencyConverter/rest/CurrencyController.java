package com.example.CurrencyConverter.rest;

import com.example.CurrencyConverter.entity.Info;
import com.example.CurrencyConverter.entity.Query;
import com.example.CurrencyConverter.entity.Root;
import com.example.CurrencyConverter.entity.Transaction;
import com.example.CurrencyConverter.helpers.FormInput;
import com.example.CurrencyConverter.service.TransactionService;
import com.example.CurrencyConverter.xml.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping("/conversion")
public class CurrencyController {


    TransactionService transactionService;

    @Autowired
    public CurrencyController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/result")
    public String result(@ModelAttribute("transaction") Transaction transaction, Model model) {
        Date startingDate = new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime();
        Date transactionDate = transaction.getDate();

        //validating the date

        if (transactionDate
                    .before(startingDate) || transactionDate.after(Timestamp.valueOf(LocalDateTime.now()))) {

            return "redirect:/conversion/form";
        }

        ObjectMapper mapper = new ObjectMapper();

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            String dateFormatted = formatter.format(transaction
                                                            .getDate());


            String code = transaction.getCurrencyCode();
            Double costCurrency = transaction.getCostCurrency();


            OkHttpClient client = new OkHttpClient().newBuilder()
                                                    .build();

            Request request = new Request.Builder()
                    .url("https://api.apilayer.com/exchangerates_data/convert?to=PLN&from=" + code + "&amount=" + costCurrency + "&date=" + dateFormatted)
                    .addHeader("apikey", "1L5PHfSr6Er2niN8rUb16SkRYA2uuBxU")
                    .build();

            Response response = client.newCall(request)
                                      .execute();


            Root root = new Root();
            Info info = new Info();
            Query query = new Query();


            String json = response.body()
                                  .string();

            root = mapper.readValue(json, Root.class);


            info = root.getInfo();
            query = root.getQuery();


            transaction.setBid(info.getRate());
            transaction.setCostPln(root.getResult());

            transactionService.save(transaction);


            model.addAttribute("transaction", transaction);


        } catch (
                IOException e) {
            e.printStackTrace();
        }


        return "redirect:/conversion/list";
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
        if(formInput.getDate() == null){
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
