package org.ingresosYgastos.services;


import lombok.RequiredArgsConstructor;
import org.ingresosYgastos.IngresosGastosRepository;
import org.ingresosYgastos.dto.RegistrarRequestDTO;
import org.ingresosYgastos.entity.IngresosGastos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class IngresosGastosService {

    private final IngresosGastosRepository ingresosGastosRepository;

    @Transactional
    public void registrar(RegistrarRequestDTO request){

        IngresosGastos ingresosGastosExistentes = ingresosGastosRepository.findByFechaRegistro(request.fechaRegistro());

        if(ingresosGastosExistentes == null){

            IngresosGastos ingresosGastosNew = new IngresosGastos.RegistroBuilder()
                    .ingreso(request.ingreso())
                    .gasto(request.gasto())
                    .detalleIngreso(request.detalleIngreso())
                    .detalleGasto(request.detalleGasto())
                    .registroBuild();


            ingresosGastosRepository.save(ingresosGastosNew);


        }else {

            if (request.ingreso() != null) ingresosGastosExistentes.sumarIngreso(request.ingreso());
            if (request.gasto() != null) ingresosGastosExistentes.sumarGasto(request.gasto());

        }



    }
}
