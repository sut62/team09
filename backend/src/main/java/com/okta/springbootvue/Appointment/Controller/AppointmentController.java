package com.okta.springbootvue.Appointment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Appointment.Entity.*;
import com.okta.springbootvue.Appointment.Repository.*;
import com.okta.springbootvue.Diagnose.entity.Diagnose;
import com.okta.springbootvue.Diagnose.entity.Doctor;
import com.okta.springbootvue.Diagnose.repository.DiagnoseRepository;
import com.okta.springbootvue.Diagnose.repository.DoctorRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AppointmentController {

    @Autowired
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private DemeanorRepository demeanorRepository;
    @Autowired
    private ReasonRepository reasonRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DiagnoseRepository diagnoseRepository;

    @Autowired
    AppointmentController(AppointmentRepository appointmentRepository, DiagnoseRepository diagnoseRepository,
            DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.diagnoseRepository = diagnoseRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/appointments")
    public Collection<Appointment> Appointments() {
        return appointmentRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/appointment/{clinicId}/{appointDate}/{appointTime}/{demeanorId}/{reasonId}/{diagnoseId}/{doctorId}")
    public Appointment newAppointment(@PathVariable final long diagnoseId, @PathVariable final long clinicId,
            @PathVariable final long demeanorId, @PathVariable final long reasonId, @PathVariable final long doctorId,
            @PathVariable final String appointDate, @PathVariable final String appointTime) {

        Appointment newAppointment = new Appointment();

        // findById
        Diagnose diagnose = diagnoseRepository.findById(diagnoseId);
        Clinic c = clinicRepository.findById(clinicId);
        Demeanor d = demeanorRepository.findById(demeanorId);
        Reason r = reasonRepository.findById(reasonId);
        Doctor doctor = doctorRepository.findById(doctorId);

        // set
        newAppointment.setDiagnose(diagnose);
        newAppointment.setClinic(c);
        newAppointment.setDemeanor(d);
        newAppointment.setReason(r);
        newAppointment.setDoctor(doctor);
        newAppointment.setAppointDate(appointDate);
        newAppointment.setAppointTime(appointTime);

        return appointmentRepository.save(newAppointment); // save

    }
}