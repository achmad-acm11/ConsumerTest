package com.example.javasalttest.controllers;

import com.example.javasalttest.requests.ConsumerRequest;
import com.example.javasalttest.entity.Consumer;
import com.example.javasalttest.requests.DatatableRequest;
import com.example.javasalttest.service.ConsumerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consumer")
public class ConsumerController {
    private final ConsumerService service;
    @GetMapping
    public String indexView() {
        return "index";
    }

    @GetMapping("/add")
    public String addView() {
        return "createForm";
    }

    @PostMapping ("/create")
    public ResponseEntity<Object> createHandler(@RequestBody ConsumerRequest req){
        service.createConsumer(req);

        Map<String, String> res = new HashMap<>();
        res.put("message", "Success create data");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/edit/{id}")
    public String editView(Model model, @PathVariable("id") Long id) {
        Consumer consumer = new Consumer();
        try {
             consumer = service.detailConsumer(id);
        }catch (RuntimeException e){
            return "404";
        }

        model.addAttribute("consumer", consumer);
        return "updateForm";
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateHandler(@RequestBody ConsumerRequest req, @PathVariable("id") Long id) {
        service.updateConsumer(req, id);

        Map<String, String> res = new HashMap<>();
        res.put("message", "Success update data");
        return ResponseEntity.ok(res);
    }

    @PostMapping ("/delete/{id}")
    public ResponseEntity<Object> deleteHandler(@PathVariable("id") Long id){
        service.deleteConsumer(id);

        Map<String, String> res = new HashMap<>();
        res.put("message", "Success delete data");
        return ResponseEntity.ok(res);
    }

    @PostMapping ("/datatable")
    public ResponseEntity<Object> datatableHandler(HttpServletRequest request){
        var reqMapping = DatatableRequest.builder()
                .draw(request.getParameter("draw"))
                .start(Integer.valueOf(request.getParameter("start")))
                .length(Integer.valueOf(request.getParameter("length")))
                .search(request.getParameter("search[value]"))
                .build();
        var res = service.getDatatableConsumer(reqMapping);

//        List<Consumer> list = new ArrayList<>();
//        list.add(Consumer.builder().id(1L)
//                .name("Budi")
//                .address("Jl. Sejahtera")
//                .city("Jakarta")
//                .province("Jakarta")
//                .registration_date("2023-02-25 01:29:45")
//                .status(Status.AKTIF)
//                .build());

        return ResponseEntity.ok(res);
    }
}
