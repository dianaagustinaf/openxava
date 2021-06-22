package com.tuempresa.facturacion.modelo;


import java.time.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

import com.tuempresa.facturacion.calculadores.*; // SE AGREGA AL LLAMARLO EN NUMERO

import lombok.*;
/*
import java.time.*;
import javax.persistence.*;
import org.hibernate.annotations.*;  // LE FALTA EL GENERICGENERATOR, entity quedaba en rojo
import org.openxava.annotations.*;
import org.openxava.calculators.*;
import lombok.*;
*/

@Entity @Getter @Setter
public class Factura {

    @Id
    @GeneratedValue(generator="system-uuid")
    @Hidden
    @GenericGenerator(name="system-uuid", strategy="uuid")
    @Column(length=32)
    String oid;
 
    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class) // Año actual
    int anyo;
 
    @Column(length=6)
    @DefaultValueCalculator(value=CalculadorSiguienteNumeroParaAnyo.class,
    properties=@PropertyValue(name="anyo") // Para inyectar el valor de anyo de Factura
                                           // en el calculador antes de llamar a calculate()
    		)
    int numero;
 
    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    LocalDate fecha;
 
    @Stereotype("MEMO")
    String observaciones;
 
}