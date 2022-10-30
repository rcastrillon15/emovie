package com.test.common

sealed class Either<out L, out R> {

    data class Left<out L>(val l: L) : Either<L, Nothing>()

    data class Right<out R>(val r: R) : Either<Nothing, R>()

    fun <T> fold(fnL: (L) -> T, fnR: (R) -> T): T {
        return when (this) {
            is Left -> fnL(l)
            is Right -> fnR(r)
        }
    }
}

/*sealed class Either<out ErrorType, out ResultType> {
    class Left<ErrorType>(val error: ErrorType) : Either<ErrorType, Nothing>()
    class Right<ResultType>(val value: ResultType) : Either<Nothing, ResultType>()

    fun <T> fold(functionLeft: (ErrorType) -> T, functionRight: (ResultType) -> T): T {
        return when (this) {
            is Left -> functionLeft(error)
            is Right -> functionRight(value)
        }
    }

    suspend fun <T> foldSuspendable(
        functionLeft: suspend (ErrorType) -> T,
        functionRight: suspend (ResultType) -> T
    ): T {
        return when (this) {
            is Left -> functionLeft(error)
            is Right -> functionRight(value)
        }
    }

    @JvmName("mapRight")
    fun <NewResultType> map(functionRight: (ResultType) -> NewResultType): Either<ErrorType, NewResultType> {
        return when (this) {
            is Left -> this
            is Right -> Right(functionRight(this.value))
        }
    }

    suspend fun <NewResultType> mapSuspendable(functionRight: suspend (ResultType) -> NewResultType): Either<ErrorType, NewResultType> {
        return when (this) {
            is Left -> this
            is Right -> Right(functionRight(this.value))
        }
    }

    @JvmName("mapLeft")
    fun <NewErrorType> map(
        functionLeft: (ErrorType) -> NewErrorType
    ): Either<NewErrorType, ResultType> {
        return when (this) {
            is Left -> Left(functionLeft(this.error))
            is Right -> this
        }
    }

    suspend fun <NewErrorType, NewResultType> concat(
        function: suspend (ResultType) -> Either<NewErrorType, NewResultType>,
        errorTransformation: (error: ErrorType) -> Either<NewErrorType, Nothing>
    ): Either<NewErrorType, NewResultType> {
        return when (this) {
            is Right -> function(this.value)
            is Left -> errorTransformation(error)
        }
    }
}
*/