package br.com.awm.pantherproducts.kafka.enums;

public enum ErrorCode {
    //Tech errors
    BAD_REQUEST("Parâmetro(s) em formato incorreto."),
    SERVICE_UNAVAILABLE("O serviço está indisponível no momento, tente novamente mais tarde."),
    UNEXPECTED_ERROR("Ocorreu um erro inesperado"),
    GATEWAY_TIMEOUT("Gateway timed out."),

    //Service Errors
    KAFKA_TIMED_OUT("Reply timed out");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
