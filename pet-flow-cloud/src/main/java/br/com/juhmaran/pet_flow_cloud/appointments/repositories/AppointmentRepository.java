package br.com.juhmaran.pet_flow_cloud.appointments.repositories;

import br.com.juhmaran.pet_flow_cloud.appointments.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
