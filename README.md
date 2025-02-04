# EventManagementBackEnd

## Ongoing Project !!!
## Java Spring Boot
### The idea is to manage and organize events from different categories and actually make money from it. People can browse events, buy tickets, and attend whatever interests them. The goal is to make it super easy for both event organizers and attendees.


### Full Code is on master branch.

## Models
### User – Manages user data and roles.
### Events – Stores event details.
### Locations – Defines event locations.
### Tickets – Tracks ticket availability and sales.
### Payments – Handles transactions.
### Sponsors – Manages event sponsors.
### Feedbacks – Collects user reviews and ratings.
### SponsorEvents – Links sponsors to events.

## Enumerations
### Category – Defines the type of event.
### Role – Defines user roles (e.g., Admin, Organizer, Attendee).
### SponsorStatus – Tracks sponsor status (Active, Inactive, Expired).
### TicketType – Indicates ticket availability (Sold, Available).

## Repositories
### JpaRepository with custom queries for database operations.

## REST Controllers
### Provides RESTful APIs for front-end integration with a React application.
