package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.Hotel;
import com.example.healthtourismapplication.database.entity.HotelReservation;
import com.example.healthtourismapplication.database.repository.IHotelRepository;
import com.example.healthtourismapplication.database.repository.IHotelReservationRepository;
import com.example.healthtourismapplication.mapper.HotelMapper;
import com.example.healthtourismapplication.model.requestDTO.HotelRequest;
import com.example.healthtourismapplication.model.responseDTO.HotelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final IHotelRepository hotelRepository;
    private final IHotelReservationRepository hotelReservationRepository;

    @Autowired
    HotelMapper mapper;

    public ResponseEntity<HotelResponse> createHotel(HotelRequest hotelRequest) {
        Hotel hotel = mapper.requestToHotel(hotelRequest);
         hotel = hotelRepository.save(hotel);
         return new ResponseEntity<>(mapper.hotelToHotelResponse(hotel), HttpStatus.CREATED);
    }

    public ResponseEntity<HotelResponse> getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        return new ResponseEntity<>(mapper.hotelToHotelResponse(hotel), HttpStatus.FOUND);
    }

    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return new ResponseEntity<>(mapper.hotelListToHotelResponse(hotelList), HttpStatus.FOUND);
    }

    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);

        if(hotel != null) {
            List<HotelReservation> hotelReservations = hotel.getHotelReservations();

            for(HotelReservation h : hotelReservations) {
                h.setHotel(null);
                hotelReservationRepository.save(h);
            }
            hotelRepository.deleteById(id);
        }
    }

    public ResponseEntity<List<HotelResponse>> findHotelsByLocation(String location) {
        List<Hotel> hotels = hotelRepository.findByLocation(location);
        return new ResponseEntity<>(mapper.hotelListToHotelResponse(hotels),HttpStatus.OK);
    }
}
