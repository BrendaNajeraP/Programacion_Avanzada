package model;

public class Book {
    private String titulo;
    private String autor;
    private String isbn;
    private String anioPublicacion;
    private String idCategoria;
    private String categoria;

    public Book() {}

    public Book(String titulo, String autor, String isbn, String anioPublicacion, String idCategoria, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public String getAnioPublicacion() { return anioPublicacion; }
    public String getIdCategoria() { return idCategoria; }
    public String getCategoria() { return categoria; }

    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setAnioPublicacion(String anioPublicacion) { this.anioPublicacion = anioPublicacion; }
    public void setIdCategoria(String idCategoria) { this.idCategoria = idCategoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
