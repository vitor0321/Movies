package com.walcker.movies.features.movies.features.domain.mapper

import com.walcker.movies.features.movies.features.domain.models.exception.ExceptionPresentation
import com.walcker.movies.features.movies.features.domain.models.exception.NetworkException

internal object ExceptionMapper {

    fun NetworkException.toExceptionPresentation(): ExceptionPresentation =
        when (this) {
            is NetworkException.NotFoundException -> ExceptionPresentation.NotFound
            is NetworkException.ConflictException -> ExceptionPresentation.Conflict
            is NetworkException.BadRequestException -> ExceptionPresentation.BadRequest
            is NetworkException.UnauthorizedException -> ExceptionPresentation.Unauthorized
            is NetworkException.ServerErrorException -> ExceptionPresentation.ServerError
            is NetworkException.UnprocessableEntityException -> ExceptionPresentation.UnprocessableEntity
            else -> ExceptionPresentation.UnknownError
        }
    }