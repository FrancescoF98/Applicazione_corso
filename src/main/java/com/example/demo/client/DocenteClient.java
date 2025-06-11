package com.example.demo.client;

import com.example.demo.data.dto.DocenteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "docenteClient", url = "${docente.service.url}")
public interface DocenteClient {

    @GetMapping("/docenti/{id}")
    DocenteDTO getDocenteById(@PathVariable("id") Long id);
}

