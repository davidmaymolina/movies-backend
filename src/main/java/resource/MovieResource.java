package resource;


import model.Movie;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import repository.MovieRepository;

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MovieResource {

    @Inject
    MovieRepository movieRepository;

    @GET
    public Response getAll(){
        return Response.ok(movieRepository.listAll()).build();
    }

    @GET
    @Path("/name/{title}")
    public Response getByTitle(@PathParam("title") String title){
        Movie movie = movieRepository.findByTitle(title);
        return  Response.ok(movie).build();
    }

    @GET
    @Path("/name")
    public Response getByTitleParam(@QueryParam("title") String title){
        Movie movie = movieRepository.findByTitle(title);
        return  Response.ok(movie).build();
    }
    @GET
    @Path("/id/{id}")
    public Response getByID(@PathParam("id") Long id){
        Movie movie = movieRepository.findById(id);
        return  Response.ok(movie).build();
    }

    @GET
    @Path("/id")
    public Response getByIDParam(@QueryParam("id") Long id) {
        Movie movie = movieRepository.findById(id);
        return Response.ok(movie).build();
    }

    @GET
    @Path("/author/{author}")
    public Response getByAuthor(@PathParam("author") String author){
        Movie movie = movieRepository.findByAuthor(author);
        return  Response.ok(movie).build();
    }

    @GET
    @Path("/author")
    public Response getByAuthorParam(@QueryParam("author") String author) {
        Movie movie = movieRepository.findByAuthor(author);
        return Response.ok(movie).build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{title}")
    public  void deleteMovieByID(@PathParam("title") String title){
        Movie movie = movieRepository.findByTitle(title);
        movieRepository.delete(movie);
    }

    @DELETE
    @Transactional
    @Path("/delete")
    public  void deleteMovieByIDParam(@QueryParam("id") Long id){
        Movie movie = movieRepository.findById(id);
        movieRepository.delete(movie);
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public  void deleteMovieByID(@PathParam("id") Long id){
        Movie movie = movieRepository.findById(id);
        movieRepository.delete(movie);
    }




    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createMovie(@QueryParam("author") String author, @QueryParam("title") String title){
        Movie newMovie = new Movie(author, title);
        movieRepository.persist(newMovie);
        return  Response.created(URI.create("/movies" + newMovie.getId())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createMovie(Movie newMovie){
        movieRepository.persist(newMovie);
        return  Response.created(URI.create("/movies" + newMovie.getId())).build();
    }

    @PUT
    @Path("/put")
    @Transactional
    public Response updateMovie(@QueryParam("id") Long id, @QueryParam("author") String author, @QueryParam("title") String title ){
    Movie updateMovie = movieRepository.findById(id);
    updateMovie.setTitle(title);
    updateMovie.setAuthor(author);
    movieRepository.persist(updateMovie);
        return Response.ok(updateMovie).build();
    }
    @PUT
    @Path("/put/{id}")
    @Transactional
    public Response updateMovie(@PathParam("id") Long id, Movie updateMovie){
        Movie movie = movieRepository.findById(id);
        movie.setTitle(updateMovie.getTitle());
        movie.setAuthor(updateMovie.getAuthor());
        movieRepository.persist(movie);
        return Response.ok(movie).build();
    }


}


