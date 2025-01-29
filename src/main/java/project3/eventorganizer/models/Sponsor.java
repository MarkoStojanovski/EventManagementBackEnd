package project3.eventorganizer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import project3.eventorganizer.models.enumerations.SponsorStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sponsors")
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Pattern(regexp = "^[A-Za-z,. ]+$", message = "Address can only contain letters, commas, dot !")
    @Column(name = "sponsor_name")
    private String name;

    @PastOrPresent(message = "Cannot be in the future !")
    private LocalDate foundedIn;

    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;


    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sponsorship_amount")
    @Min(0)
    private Double amountGiven;

    @Column(name = "money_handed_date")
    private LocalDate moneyGivenDate;

    @Enumerated(EnumType.STRING)
    public SponsorStatus sponsorStatus;

    @ManyToMany
    @JoinTable(
            name = "event_sponsors",
            joinColumns = @JoinColumn(name= "sponsor_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<EventSponsor> eventSponsors;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @PrePersist
    protected void onCreate() {
        this.moneyGivenDate = LocalDate.now();
    }

    public Sponsor(String name, LocalDate foundedIn, String email, String imageUrl, Double amountGiven, SponsorStatus sponsorStatus) {
        this.name = name;
        this.foundedIn = foundedIn;
        this.email = email;
        this.imageUrl = imageUrl;
        this.amountGiven = amountGiven;
        this.sponsorStatus = sponsorStatus;
    }
}
