package com.example.seminar_1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.seminar_1.presentation.data.WordsRepositoryImpl
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class MainActivityViewModel : ViewModel() {
    val repository = WordsRepositoryImpl()
    val error = PublishSubject.create<String>()

    fun search(query: String): Observable<List<String>> = Observable.create {
        val result = repository.getAllWords()
            .filter { word -> word.contains(query) }
        if (result.isEmpty()) error.onNext("Ничего не найдено") else it.onNext(result)
    }
        .subscribeOn(Schedulers.io())
}