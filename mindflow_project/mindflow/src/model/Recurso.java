package model;

/**
 * Classe que representa um recurso de bem-estar.
 * Pode ser um artigo, vídeo, técnica de respiração, etc.
 */
public class Recurso {
    private int id;
    private String titulo;
    private String tipo;        // Artigo, Vídeo, Técnica, etc.
    private String url;         // Link de acesso ao recurso
    private String foco;        // Área de foco: Estresse, Foco, Produtividade, etc.

    public Recurso(int id, String titulo, String tipo, String url, String foco) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.url = url;
        this.foco = foco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFoco() {
        return foco;
    }

    public void setFoco(String foco) {
        this.foco = foco;
    }

    @Override
    public String toString() {
        return "Recurso{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", url='" + url + '\'' +
                ", foco='" + foco + '\'' +
                '}';
    }
}
