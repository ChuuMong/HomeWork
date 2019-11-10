package space.chuumong.domain.usecase

import io.reactivex.Completable

abstract class CompletableUseCase<in Params> {

    internal abstract fun run(params: Params): Completable

    fun execute(params: Params): Completable {
        return run(params)
    }
}