package com.walcker.movies.features.movies.features.domain.models.exception

internal enum class ExceptionPresentation(val message: String) {
    UnknownError("Ocorreu um erro inesperado. Por favor, tente novamente."),
    Conflict("Ocorreu um erro inesperado. Por favor, tente novamente."),
    Unauthorized("Você não tem permissão para acessar essa funcionalidade."),
    ServerError("Algo deu errado no servidor. Tente novamente mais tarde."),
    NotFound("Oops! O recurso que você está procurando não foi encontrado."),
    BadRequest("Houve um problema com a sua solicitação. Verifique e tente novamente."),
    UnprocessableEntity("Alguns dados enviados estão incorretos. Por favor, revise e tente novamente."),
}