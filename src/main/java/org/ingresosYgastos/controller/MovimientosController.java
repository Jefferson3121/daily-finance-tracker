package org.ingresosYgastos.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ingresosYgastos.dto.EditarMovimientoRequestDTO;
import org.ingresosYgastos.dto.RegistrarRequestDTO;
import org.ingresosYgastos.dto.ResumenResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(("/movimientos  "))
public class MovimientosController {

    private final org.ingresosYgastos.services.MovimientosService movimientosService;


    @PostMapping
    public void registrar(@Valid @RequestBody  RegistrarRequestDTO requestDTO){
        movimientosService.registrar(requestDTO);
    }


    @GetMapping("/resumen")
    public ResponseEntity<ResumenResponseDTO> obtenerResumen(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin){

        ResumenResponseDTO resumen = movimientosService.ontenerResumen(fechaInicio, fechaFin);

        return ResponseEntity.status(HttpStatus.OK).body(resumen);
    }


//    @PutMapping("/{id}")
//    public void editar(@PathVariable Long id, @RequestBody EditarMovimientoRequestDTO request) {
//        movimientosService.editar(id, request);
//    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        movimientosService.eliminar(id);
    }


}
