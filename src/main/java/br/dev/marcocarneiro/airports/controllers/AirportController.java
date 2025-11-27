package br.dev.marcocarneiro.airports.controllers;

import br.dev.marcocarneiro.airports.DTO.AirportMinDTO;
import br.dev.marcocarneiro.airports.entities.Airport;
import br.dev.marcocarneiro.airports.service.AirportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DIT2B
 */
//Anuncia ao Spring que essa classe vai ser um Controller – e que haverá
//Endpoints nela.
@RestController
public class AirportController {

    //Já explicado. Vai injetar a dependência automaticamente.
    @Autowired
    private AirportService airportService;

    /**
     * Endpoint /airports/airport Retorna TODOS os aeroportos da base de dados
     *
     * @return
     */
    //Indica que o método logo abaixo responde ao endpoint /airports/airport.
    @GetMapping("/airport")
    public List<Airport> findAll() {
        List<Airport> result = airportService.findAll();
        return result;
    }

    /**
     * Endpoint /airports/city {cityName} padronização REST
     *
     * @param cityName
     * @return
     */
    @GetMapping("/country/{countryName}")
    public ResponseEntity<List<AirportMinDTO>> findByCountryIgnoreCase(@PathVariable String countryName) {

        List<AirportMinDTO> result = airportService.findByCountry(countryName);

        if (result.isEmpty()) {
            //Ops.. lista vazia...
            //notfound devolve 404
            return ResponseEntity.notFound().build();

        } else {
            //Eba!Tem dados!
            //ok devolve 200
            return ResponseEntity.ok(result);
        }

    }

    @GetMapping("/iatacode/{iataCode}")
    public ResponseEntity<Airport> findByIataCode(@PathVariable String iataCode) {
        Airport result = airportService.findByIataCode(iataCode);

        if (result == null) {
            //Ops.. Aeroporto vazio...
            //notfound devolve 404
            return ResponseEntity.notFound().build();

        } else {
            //Eba!Tem dados!
            //ok devolve 200
            return ResponseEntity.ok(result);
        }

    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity<List<Airport>> findByCityIgnoreCase(@PathVariable String cityName) {
        List<Airport> result = airportService.findByCity(cityName);

        if (result.isEmpty()) {
            //Ops.. lista vazia...
            //notfound devolve 404
            return ResponseEntity.notFound().build();

        } else {
            //Eba!Tem dados!
            //ok devolve 200
            return ResponseEntity.ok(result);
        }

    }
}
