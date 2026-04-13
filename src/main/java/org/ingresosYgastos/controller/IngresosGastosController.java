package org.ingresosYgastos.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ingresosYgastos.dto.RegistrarRequestDTO;
import org.ingresosYgastos.dto.ResumenResponseDTO;
import org.ingresosYgastos.services.IngresosGastosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(("/movimientos  "))
public class IngresosGastosController {

    private final IngresosGastosService ingresosGastosService;


    @PostMapping
    public void registrar(@Valid @RequestBody  RegistrarRequestDTO requestDTO){
        ingresosGastosService.registrar(requestDTO);
    }


    @GetMapping("/resumen")
    public ResponseEntity<ResumenResponseDTO> obtenerResumen(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin){

        ResumenResponseDTO resumen = ingresosGastosService.ontenerResumen(fechaInicio, fechaFin);

        return ResponseEntity.status(HttpStatus.OK).body(resumen);
    }


}
