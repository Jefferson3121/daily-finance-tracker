package org.ingresosYgastos.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ingresosYgastos.dto.RegistrarRequestDTO;
import org.ingresosYgastos.entity.IngresosGastos;
import org.ingresosYgastos.repository.IngresosGastosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class IngresosGastosService {

    private final IngresosGastosRepository ingresosGastosRepository;

    @Transactional
    public void registrar(RegistrarRequestDTO request){

        IngresosGastos ingresosGastosExistentes = ingresosGastosRepository.findByFechaRegistro(request.fechaRegistro());

        log.info("{}",request.gasto() );

        if(ingresosGastosExistentes == null){

            IngresosGastos ingresosGastosNew = new IngresosGastos.RegistroBuilder()
                    .ingreso(request.ingreso())
                    .gasto(request.gasto())
                    .detalleIngreso(request.detalleIngreso())
                    .detalleGasto(request.detalleGasto())
                    .fechaRegistro(request.fechaRegistro())
                    .registroBuild();


            ingresosGastosRepository.save(ingresosGastosNew);


        }else {

            if (request.ingreso() != null) ingresosGastosExistentes.sumarIngreso(request.ingreso());
            if (request.gasto() != null) ingresosGastosExistentes.sumarGasto(request.gasto());

        }



    }
}
