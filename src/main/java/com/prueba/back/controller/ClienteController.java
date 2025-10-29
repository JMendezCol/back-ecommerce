package com.prueba.back.controller;

import com.prueba.back.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(value = "http://localhost:4200")
public class ClienteController {

    /**
     * Este metodo se encarga de buscar un cliente en especifico pasandole
     * como parametros tipoDocumento y numeroDocumento
     * @param tipoDocumento tipo de documento de la persona a consultar "C"
     * @param numeroDocumento: numero documento a consultar 23445322
     * @return
     */
    @GetMapping("/buscar")
    public ResponseEntity<?> obtenerCliente(
            @RequestParam("tipoDocumento") String tipoDocumento,
            @RequestParam("numeroDocumento") String numeroDocumento) {

        if (tipoDocumento == null || numeroDocumento == null || tipoDocumento.isEmpty() || numeroDocumento.isEmpty()) {
            return new ResponseEntity<>("El tipo y numero de documento son obligatorios.", HttpStatus.BAD_REQUEST);
        }
        if (!tipoDocumento.equals("C") && !tipoDocumento.equals("P")) {
            return new ResponseEntity<>("El tipo de documento no es valido. Debe ser 'C' o 'P'.", HttpStatus.BAD_REQUEST);
        }
        if (tipoDocumento.equals("C") && numeroDocumento.equals("23445322")) {
            Cliente cliente = new Cliente(
                    "Jhonatan", "Yesid", "Mendez", "Perez",
                    "1234567890", "Casa", "Bucaramanga"
            );
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>("Cliente no encontrado.", HttpStatus.NOT_FOUND);
    }
}
