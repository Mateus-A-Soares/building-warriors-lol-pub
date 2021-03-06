package br.com.iupp.buildingwarriors.entrypoint.controller.handler

import br.com.iupp.buildingwarriors.entrypoint.controller.response.DefaultErrorResponse
import io.micronaut.context.annotation.Replaces
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus.UNPROCESSABLE_ENTITY
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton
import javax.validation.ConstraintViolationException

@Produces
@Singleton
@Requires(classes = [ConstraintViolationException::class, ExceptionHandler::class])
@Replaces(io.micronaut.validation.exceptions.ConstraintExceptionHandler::class)
class ConstraintExceptionHandler : ExceptionHandler<ConstraintViolationException, HttpResponse<DefaultErrorResponse?>> {

    override fun handle(
        request: HttpRequest<*>,
        exception: ConstraintViolationException
    ): HttpResponse<DefaultErrorResponse?> {
        val messages = exception.constraintViolations.map {
            "${it.propertyPath.last()}: ${it.message}"
        }
        return HttpResponse.unprocessableEntity<DefaultErrorResponse>().body(
            DefaultErrorResponse(
                statusCode = UNPROCESSABLE_ENTITY.code,
                messages = messages,
                path = request.path,
                error = UNPROCESSABLE_ENTITY.reason
            )
        )
    }
}