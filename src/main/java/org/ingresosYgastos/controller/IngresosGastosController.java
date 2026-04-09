package org.ingresosYgastos.controller;


import lombok.RequiredArgsConstructor;
import org.ingresosYgastos.dto.RegistrarRequestDTO;
import org.ingresosYgastos.services.IngresosGastosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/registrar")
public class IngresosGastosController {

    private IngresosGastosService ingresosGastosService;


    @PostMapping
    public void registrar(@RequestBody  RegistrarRequestDTO requestDTO){

        ingresosGastosService.registrar(requestDTO);


    }


}
