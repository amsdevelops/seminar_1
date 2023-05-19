package com.example.seminar_1.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.seminar_1.R
import com.example.seminar_1.databinding.ActivityMainBinding
import com.example.seminar_1.presentation.viewmodel.MainActivityViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val compositeDisposable = CompositeDisposable()
    private val viewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.search(binding.search.text.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { binding.result.text = it.toString() },
                    { Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show() }
                ).also { compositeDisposable.add(it) }
        }

        viewModel.error
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }.also { compositeDisposable.add(it) }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}