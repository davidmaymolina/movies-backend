package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import model.Movie;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<Movie> {
    public Movie findByTitle(String title){
        return find("title", title).firstResult();
    }
    public List<Movie> allMovies(){
        return  listAll(Sort.by("title"));
    }
    public Movie findByAuthor(String author){
        return find("author", author).firstResult();
    }
}
