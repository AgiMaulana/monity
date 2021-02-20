package io.github.agimaulana.monity

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers


class TestSchedulerProvider: SchedulerProvider {
    override val io: Scheduler get() = Schedulers.trampoline()
    override val ui: Scheduler get() = Schedulers.trampoline()
}