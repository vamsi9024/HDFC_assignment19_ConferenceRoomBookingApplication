package com.example.ConferenceRoomApplication;

import com.example.ConferenceRoomApplication.entity.*;
import com.example.ConferenceRoomApplication.service.BookingManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


public class ConferenceRoomApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ConferenceRoomApplication.class, args);

		BookingManager bookingManager=new BookingManager();

		Building b1=new Building("B1", "Main Building");

		ConferenceRoom r1=new ConferenceRoom("R1", "Ocean", 10, Arrays.asList("TV", "Whiteboard"), "1st Floor");
		ConferenceRoom r2 = new ConferenceRoom("R2", "Sky", 5, Arrays.asList("Whiteboard"), "2nd Floor");
		b1.addRoom(r1);
		b1.addRoom(r2);

		bookingManager.addBuilding(b1);

		User u1 = new User("U1", "Vamsi");
		User u2 = new User("U2", "Krishna");

		// View available rooms
		System.out.println("Available rooms:");
		List<ConferenceRoom> available = bookingManager.viewAvailableRooms(
				LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0), 5, Arrays.asList("Whiteboard"));
		available.forEach(r -> System.out.println("- " + r.getName()));

		//Book Room
		TimeSlot slot=new TimeSlot(LocalDate.now(), LocalTime.of(10,0),LocalTime.of(12,0));
		Booking booking=bookingManager.bookRoom(u1,"R2",slot);
		System.out.println("\nBooking Successful: \n"+booking);

		// Try double booking
		try {
			bookingManager.bookRoom(u2, "R2", slot);
		} catch (Exception e) {
			System.out.println("\nDouble booking attempt:");
			System.out.println("Error: " + e.getMessage());
		}

		//Cancel booking
		boolean cancelled=bookingManager.cancelBooking(booking.getBookingId(),u1);
		System.out.println("Booking cancelled: "+cancelled);

		// Try cancelling again
		boolean cancelledAgain = bookingManager.cancelBooking(booking.getBookingId(), u1);
		System.out.println("Second cancellation attempt: " + cancelledAgain);

		// Book outside working hours
		try {
			TimeSlot lateSlot = new TimeSlot(LocalDate.now(), LocalTime.of(20, 0), LocalTime.of(21, 0));
			bookingManager.bookRoom(u1, "R1", lateSlot);
		} catch (Exception e) {
			System.out.println("\n Booking outside working hours:");
			System.out.println("Error: " + e.getMessage());
		}

		// View user bookings
		System.out.println("\n Bookings for Vamsi:");
		List<Booking> userBookings = bookingManager.getUserBookings(u1);
		if (userBookings.isEmpty()) {
			System.out.println("No active bookings.");
		} else {
			userBookings.forEach(System.out::println);
		}

	}

}
