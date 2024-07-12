package com.aluracursos.forohub.infra.excepciones;

public class ObjetoInexistenteException extends RuntimeException{
    private String message;

    public ObjetoInexistenteException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
