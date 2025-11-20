package model;

import java.time.LocalDate;

/**
 * Classe que representa um check-in de humor e estresse do usuário.
 * Permite monitorar o bem-estar ao longo do tempo.
 */
public class CheckinHumor {
    private int id;
    private int idUsuario;
    private LocalDate data;
    private int nivelHumor;      // Escala de 1 a 5
    private int nivelEstresse;   // Escala de 1 a 5


    public CheckinHumor(int id, int idUsuario, LocalDate data, int nivelHumor, int nivelEstresse) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.data = data;
        this.nivelHumor = validarNivel(nivelHumor);
        this.nivelEstresse = validarNivel(nivelEstresse);
    }


    private int validarNivel(int nivel) {
        if (nivel < 1) return 1;
        if (nivel > 5) return 5;
        return nivel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getNivelHumor() {
        return nivelHumor;
    }

    public void setNivelHumor(int nivelHumor) {
        this.nivelHumor = validarNivel(nivelHumor);
    }

    public int getNivelEstresse() {
        return nivelEstresse;
    }

    public void setNivelEstresse(int nivelEstresse) {
        this.nivelEstresse = validarNivel(nivelEstresse);
    }

    @Override
    public String toString() {
        return "CheckinHumor{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", data=" + data +
                ", nivelHumor=" + nivelHumor +
                ", nivelEstresse=" + nivelEstresse +
                '}';
    }
}
