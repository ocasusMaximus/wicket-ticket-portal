package fim.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TICKET")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String movie;
    private String language;
    private int numberOfSeats;
    private String hall;


    public Ticket() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MOVIE")
    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Basic
    @Column(name = "LANGUAGE")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "NUMBER_OF_SEATS")
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Basic
    @Column(name = "HALL")
    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    @Override
    public final String toString() {
        return "Ticket{" +
                "id=" + id +
                ", movie='" + movie + '\'' +
                ", language='" + language + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", hall='" + hall + '\'' +
                '}';
    }
}
