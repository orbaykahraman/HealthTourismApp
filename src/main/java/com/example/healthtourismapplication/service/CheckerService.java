package com.example.healthtourismapplication.service;

import com.example.healthtourismapplication.database.entity.*;
import com.example.healthtourismapplication.database.repository.IAppointmentRepository;
import com.example.healthtourismapplication.database.repository.IFlightInformationRepository;
import com.example.healthtourismapplication.database.repository.IHotelReservationRepository;
import com.example.healthtourismapplication.database.repository.ITravelPlanRepository;
import com.example.healthtourismapplication.model.enums.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckerService {

    private final IAppointmentRepository appointmentRepository;
    private final IHotelReservationRepository hotelReservationRepository;
    private final IFlightInformationRepository flightInformationRepository;
    private final ITravelPlanRepository travelPlanRepository;


    /*
     * 30 saniyede bir çalışan ve appointment,hotelReservation,flightInfo tablolarından
     * Status = Waiting olan kayıtları alıp creationDate'leri 10dakikayı geçmişse bunları tabloda
     * status = cancelled olarak günceller..
     * */
    @Scheduled(fixedRate = 30000)
    public void checkAndCancelAppointments() {
        log.info("Scheduled checkAndCancelAppointments started.");
        List<Appointment> appointments = appointmentRepository.findAllByStatus(Status.WAITING);
        log.info("Number of appointments with status waiting : {}",appointments.size());
        LocalDateTime now = LocalDateTime.now();
        for(Appointment a : appointments) {
            if(a.getCreationDate().plusMinutes(10).isBefore(now)) {
                a.setStatus(Status.CANCELLED);
                appointmentRepository.save(a);
            }
        }
        List<HotelReservation> hotelReservations = hotelReservationRepository.findAllByStatus(Status.WAITING);
        log.info("Number of hotelReservations with status waiting : {}",hotelReservations.size());
        now = LocalDateTime.now();
        for(HotelReservation h : hotelReservations) {
            if(h.getCreationDate().plusMinutes(10).isBefore(now)) {
                h.setStatus(Status.CANCELLED);
                hotelReservationRepository.save(h);
            }
        }
        List<FlightInformation> flightInformations = flightInformationRepository.findAllByStatus(Status.WAITING);
        log.info("Number of flightInformations with status waiting : {}",flightInformations.size());
        now = LocalDateTime.now();
        for(FlightInformation f : flightInformations) {
            if(f.getCreationDate().plusMinutes(10).isBefore(now)) {
                f.setStatus(Status.CANCELLED);
                flightInformationRepository.save(f);
            }
        }
        log.info("Scheduled checkAndCancelAppointments finished.");
    }

    /* 2 saatte bir çalışır.
    Appointment,Flight ve Reservationları travelplan'dan alıp
    kontrol edip eğer date'leri geçtiyse status = finished yapar.
    bu sayede appointment'lar finish olduğu için doktorlar appointment notes girebilir.
     */
    @Scheduled(fixedRate = 7200000)
    public void handleStatus() {
        log.info("Scheduled handleStatus is started.");
        List<TravelPlan> travelPlans = travelPlanRepository.findAllByStatus(Status.APPROVED);
        log.info("There are {} travel plan to handle.",travelPlans.size());
        for(TravelPlan t : travelPlans) {
            Appointment appointment = t.getAppointment();
            HotelReservation reservation = t.getHotelReservation();
            FlightInformation flight = t.getFlightInformation();
            // hepsinin status finished ise, travelplan'da finished yapılır böylece bu scheduled task'ta boş yere çekilmemiş olur.
            if(appointment.getStatus().equals(Status.FINISHED) && reservation.getStatus().equals(Status.FINISHED) && flight.getStatus().equals(Status.FINISHED)) {
                t.setStatus(Status.FINISHED);
                travelPlanRepository.save(t);
            }

            if(appointment.getAppointmentDate().before(new Date())) {
                appointment.setStatus(Status.FINISHED);
                appointmentRepository.save(appointment);
            }
            if(reservation.getReservationDate().before(new Date())) {
                reservation.setStatus(Status.FINISHED);
                hotelReservationRepository.save(reservation);
            }
            if(flight.getFlightDate().before(new Date())) {
                flight.setStatus(Status.FINISHED);
                flightInformationRepository.save(flight);
            }

        }
        log.info("Scheduled handleStatus is finished.");
    }
    /*
     * Eğer appointment tablosunda oluşturulmuş statusu waiting olan ve bu patient'e ait olan kayıt yoksa false döner.
     * Önce appointment oluşturulup sonra hotelReservation oluşturulması gerekir.
     * */
    public boolean checkAppointment(Patient patient) {
        Long patientId = patient.getId();
        Appointment appointment = appointmentRepository.findLastAppointmentByPatientId(patientId);

        if(!Objects.isNull(appointment) && appointment.getId() != null) {
            return true;
        }
        return false;
    }

    /*
     * Appointment olusturulmussa, hotelReservation'u kontrol eder.Uçaktan önce hotelReservation oluşturulması gerekir.
     * */
    public boolean checkHotelReservation(Patient patient) {
        Long patientId = patient.getId();

        return hotelReservationRepository.existsHotelReservationByPatientIdAndStatusOrderByCreationDateDesc(patientId, Status.WAITING);
    }

    /*
    Travel plan oluşturulmadan önce;
    AppointmentId ile tabloda kayıt var mı ve bu kayıttaki patientId ile requesti atanın patientIdsi ayni mi
     ayrıca status = waiting mi diye kontrol yapar.
     aynilarini hotel reservation ve flight için de yapar.
     */
    public Appointment checkAppointmentById(Long appointmentId, Long patientId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);

        if(Objects.isNull(appointment) || appointment.getPatientId().compareTo(patientId) != 0 || !(appointment.getStatus().equals(Status.WAITING))) {
            return null;
        }
        return appointment;
    }

    public HotelReservation checkHotelReservationById(Long hotelReservationId, Long patientId) {
        HotelReservation reservation = hotelReservationRepository.findById(hotelReservationId).orElse(null);

        if(Objects.isNull(reservation) || reservation.getPatientId().compareTo(patientId) != 0 || !(reservation.getStatus().equals(Status.WAITING))) {
            return null;
        }
        return reservation;
    }

    public FlightInformation checkFlightById(Long flightId, Long patientId) {
        FlightInformation flight = flightInformationRepository.findById(flightId).orElse(null);

        if(Objects.isNull(flight) || flight.getPatientId().compareTo(patientId) != 0 || !(flight.getStatus().equals(Status.WAITING))) {
            return null;
        }
        return flight;
    }

    /*
    TravelPlan başarıyla oluşturulduktan sonra o travelId'de bulunan appointment,reservation ve flight kendi tablosunda
    status = approved olarak kaydedilir.
     */
    @Transactional
    public void setStatusToApproved(Appointment appointment, HotelReservation hotelReservation, FlightInformation flight) {
        appointment.setStatus(Status.APPROVED);
        appointmentRepository.save(appointment);
        hotelReservation.setStatus(Status.APPROVED);
        hotelReservationRepository.save(hotelReservation);
        flight.setStatus(Status.APPROVED);
        flightInformationRepository.save(flight);
    }


//    public boolean checkLocations(String hotelLocation) {
//    }
}
