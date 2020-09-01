package br.com.awm.pantherproducts.part01_products.enums;

public enum ErrorCode {
    //Tech errors
    BAD_REQUEST("Parâmetro(s) em formato incorreto."),
    SERVICE_UNAVAILABLE("O serviço está indisponível no momento, tente novamente mais tarde."),
    UNEXPECTED_ERROR("Ocorreu um erro inesperado"),
    GATEWAY_TIMEOUT("Gateway timed out."),

    //Service Errors
    KAFKA_TIMED_OUT("Reply timed out"),
    UNABLE_TO_GET_KEY_LIST_PRODUCT("Não foi possível obter a chave de identificação da lista de produtos requisitada"),
    EXECUTION_NOT_AUTHORIZED("Execução não autorizada. Tente novamente mais tarde."),

    METHOD_NOT_SUPPORTED("Não é possível usar esta ação neste endpoint"),
    INVALID_PATH_VARIABLE_TYPE("O tipo de dado da variavel do path é inválido"),
    INVALID_PARAMETER("Um ou mais parâmetros são inválidos"),
    VALIDATION_FAILED_FOR_PARAMETER("Um ou mais parâmetros são inválidos"),
    MISSING_PARAMETER("Parametro obrigatório não informado");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
