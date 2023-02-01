package hotel.project.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hotel.project.core.models.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

	@Query(" select g from Guest g left join fetch g.reserves where g.id = :id ")
	Guest findGuestFetchReserve( @Param("id") Long id );

	
}
