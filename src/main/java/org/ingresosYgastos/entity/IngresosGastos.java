package org.ingresosYgastos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Slf4j
@Entity
@NoArgsConstructor
public class IngresosGastos {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal ingreso;
    private BigDecimal gasto;
    private String detalleIngreso;
    private String detalleGasto;
    private LocalDate fechaRegistro;



    private IngresosGastos(RegistroBuilder registroBuilder){

        if (registroBuilder.fechaRegistro == null){
            throw new IllegalArgumentException("No se puede registrar sin una fecha");
        }

        log.info("Contructor level 1");

        this.ingreso = registroBuilder.ingreso;
        this.gasto = registroBuilder.gasto;
        this.detalleIngreso = registroBuilder.detalleIngreso;
        this.detalleGasto = registroBuilder.detalleGasto;
        this.fechaRegistro = registroBuilder.fechaRegistro;

        log.info("Constructor level 2");

    }

    public void sumarIngreso(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto del gasto debe ser mayor a cero");
        }
        this.ingreso = this.ingreso.add(monto);
    }

    public void sumarGasto(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto del gasto debe ser mayor a cero");
        }
        this.gasto = this.gasto.add(monto);
    }






    public static class RegistroBuilder {
        private BigDecimal ingreso;
        private BigDecimal gasto;
        private String detalleIngreso;
        private String detalleGasto;
        private LocalDate fechaRegistro;


        public RegistroBuilder(){}

        public RegistroBuilder ingreso(BigDecimal ingreso){
            this.ingreso= ingreso;
            return this;
        }

        public RegistroBuilder gasto(BigDecimal gasto) {
            this.gasto = gasto;
            return this;
        }

        public RegistroBuilder detalleIngreso(String detalleIngreso){
            this.detalleIngreso = detalleIngreso;
            return  this;
        }


        public RegistroBuilder detalleGasto(String detalleGasto){
            this.detalleGasto = detalleGasto;
            return this;
        }

        public RegistroBuilder fechaRegistro(LocalDate fechaRegistro){
            this.fechaRegistro = fechaRegistro;
            return this;
        }


        public IngresosGastos registroBuild(){

            if(this.ingreso == null&& this.gasto == null){
                throw new IllegalArgumentException("Debe registrar un ingreso o gasto");
            }

            return new IngresosGastos(this);
        }

    }


}
