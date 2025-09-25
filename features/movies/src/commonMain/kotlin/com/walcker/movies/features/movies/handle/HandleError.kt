package com.walcker.movies.features.movies.handle

import com.walcker.movies.features.movies.features.domain.mapper.ExceptionMapper.toExceptionPresentation
import com.walcker.movies.features.movies.features.domain.models.exception.ExceptionPresentation
import com.walcker.movies.features.movies.features.domain.models.exception.NetworkException
import kotlinx.coroutines.flow.MutableStateFlow

private fun handleError(
    exception: Throwable,
    emitError: (String) -> Unit
) {
    val error = if (exception is NetworkException) {
        exception.toExceptionPresentation()
    } else {
        ExceptionPresentation.UnknownError
    }
    emitError(error.message)
}

internal fun handleMessageError(exception: Throwable): String {
    val errorMessage: MutableStateFlow<String> = MutableStateFlow("")
    handleError(
        exception = exception,
        emitError = {
            if (exception is NetworkException.ApiException) {
                errorMessage.value = when (exception.statusCode) {
                    300 -> "O recurso requisitado foi redirecionado."
                    400 -> "Requisição inválida. Confira os dados fornecidos."
                    401 -> "Não autorizado. Por favor, faça login novamente."
                    403 -> "Você não tem permissão para acessar este recurso."
                    404 -> "Recurso não encontrado. Tente novamente mais tarde."
                    409 -> "Conflito de dados. Verifique possíveis duplicações."
                    500 -> "Erro interno do servidor. Por favor, tente novamente mais tarde."
                    502 -> "Bad Gateway. O servidor recebeu uma resposta inválida."
                    503 -> "Serviço indisponível. O servidor está temporariamente fora do ar."
                    504 -> "Tempo de resposta excedido. Verifique sua conexão de internet."
                    else -> "Um erro inesperado ocorreu. Tente novamente."
                }
            } else {
                errorMessage.value = "Algo deu errado. Tente novamente mais tarde."
            }
        }
    )
    return errorMessage.value
}