package sia.hackathon.idea.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sia.hackathon.idea.model.FlightBooking;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, String>{

}
