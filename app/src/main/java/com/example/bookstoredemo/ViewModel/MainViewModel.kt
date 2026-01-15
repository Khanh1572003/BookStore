package com.example.bookstoredemo.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bookstoredemo.Model.Slider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    private val firebase = FirebaseDatabase.getInstance()
    private val banner = firebase.getReference("banner")

    private val _uiState = MutableStateFlow(BannerUiState())
    val uiState: StateFlow<BannerUiState> = _uiState.asStateFlow()

    private var valueEventListener: ValueEventListener? = null

    init {
        observeBanner()
    }

    private fun observeBanner() {
        _uiState.update { it.copy(isLoading = true) }

        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(child in snapshot.children){
                    Log.d("Debug","Key: ${child.key}, value: ${child.value}")
                }
                val bannerList = snapshot.children.mapNotNull { childSnapshot ->
                    childSnapshot.getValue(Slider::class.java)?.copy(
                        id = childSnapshot.key ?: ""
                    )
                }

                _uiState.update {
                    it.copy(
                        slider = bannerList,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            }
        }

        banner.addValueEventListener(valueEventListener!!)
    }
    override fun onCleared() {
        super.onCleared()
        valueEventListener?.let {
            banner.removeEventListener(it)
        }
    }
}


data class BannerUiState( // Chữ hoa đầu
    val slider: List<Slider> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
