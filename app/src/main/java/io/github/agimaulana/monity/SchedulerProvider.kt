package io.github.agimaulana.monity

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {
    val io: Scheduler
    val ui: Scheduler
}