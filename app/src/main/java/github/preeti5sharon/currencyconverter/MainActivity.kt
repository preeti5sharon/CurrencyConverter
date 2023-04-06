package github.preeti5sharon.currencyconverter

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.preeti5sharon.currencyconverter.databinding.ActivityMainBinding
import github.preeti5sharon.currencyconverter.main.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(_binding?.root)

        _binding?.btnConvert?.setOnClickListener {
            viewModel.convert(
                _binding?.etFrom?.text.toString(),
                _binding?.spFromCurrency?.selectedItem.toString(),
                _binding?.spToCurrency?.selectedItem.toString(),
            )
        }

        lifecycleScope.launchWhenCreated {
            viewModel.conversion.collectLatest { event ->
                when (event) {
                    is MainViewModel.CurrencyEvent.Success -> {
                        _binding?.progressBar?.isVisible = false
                        _binding?.tvResult?.setTextColor(Color.GREEN)
                        _binding?.tvResult?.text = event.resultText
                    }
                    is MainViewModel.CurrencyEvent.Error -> {
                        _binding?.progressBar?.isVisible = false
                        _binding?.tvResult?.setTextColor(Color.RED)
                        _binding?.tvResult?.text = event.errorText
                    }
                    is MainViewModel.CurrencyEvent.Loading -> {
                        _binding?.progressBar?.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }
}
