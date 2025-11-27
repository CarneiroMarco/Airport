package br.dev.marcocarneiro.airports.service;

import br.dev.marcocarneiro.airports.DTO.AirportMinDTO;
import br.dev.marcocarneiro.airports.entities.Airport;
import br.dev.marcocarneiro.airports.repositories.AirportRepository;
import java.util.List;
import static org.hibernate.internal.util.PropertiesHelper.map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    @Autowired private AirportRepository airportRepository;
    
    public List<Airport> findAll(){
        
        List<Airport> result = airportRepository.findAll();
        return result;
    }
    /**
     * Retorna DTO Airports filtrado por cidade
     * @param city
     * @return
     */
    
    public List<Airport> findByCity (String city){
        List<Airport> result = airportRepository.findByCityIgnoreCase(city);
        return result;
       
    }
    public List<AirportMinDTO> findByCountry (String country){
        List<Airport> resultAirport = airportRepository.findByCountryIgnoreCase(country);
        
        List<AirportMinDTO> resultDTO = resultAirport.stream()
            .map(x -> new AirportMinDTO(x)).toList();
        return resultDTO;
    }
    
}
