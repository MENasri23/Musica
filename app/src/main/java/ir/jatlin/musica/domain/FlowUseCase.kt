package ir.jatlin.musica.domain

import ir.jatlin.musica.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class FlowUseCase<P, R> {

    operator fun invoke(params: P): Flow<Resource<R>> = flow {
        emit(Resource.loading())
        emit(Resource.success(execute(params = params)))
    }.catch { cause ->
        emit(Resource.error(cause))
    }

    abstract suspend fun execute(params: P): R
}