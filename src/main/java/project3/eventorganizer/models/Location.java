package project3.eventorganizer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Location name cannot be empty !")
    @Column(name = "location_name")
    private String name;

    @Column(name = "city_name")
    private String city;

    @Column(name = "country_name")
    private String country;

    @Pattern(regexp = "^[A-Za-z0-9, ]+$", message = "Address can only contain letters, numbers, and commas")
    @Column(name = "location_address")
    private String address;

    @PastOrPresent(message = "Date cannot be in the future !")
    @Column(name = "active_since")
    private LocalDate activeSince;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "image_url")
    private String imageUrl;

    public Location(String name, String city, String country, String address, LocalDate activeSince, String imageUrl) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.address = address;
        this.activeSince = activeSince;
        this.imageUrl = imageUrl;
    }
}
