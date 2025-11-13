package com.example.bookstoredemo.ViewModel
import androidx.lifecycle.ViewModel
import com.example.bookstoredemo.Model.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.lang.Exception

open class SuggestViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance()
    private val suggest = database.getReference("san_pham")

    private val _suggestState = MutableStateFlow(ProductStateUi())
    val suggestState: StateFlow<ProductStateUi> = _suggestState.asStateFlow()

    private var valueEventListener: ValueEventListener? = null

    init {
        observeProduct()
    }

    private fun observeProduct() {
        _suggestState.update { it.copy(isLoading = true) }
        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val products = mutableListOf<Product>()
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        product?.let { products.add(it) }
                    }
                    _suggestState.update {
                        it.copy(
                            product = products,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                } catch (e: Exception) {

                }
            }

            override fun onCancelled(error: DatabaseError) {
                _suggestState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            }
        }
        suggest.addValueEventListener(valueEventListener!!)
    }

    override fun onCleared() {
        super.onCleared()
        valueEventListener?.let {
            suggest.removeEventListener(it)
        }
    }
}
data class ProductStateUi(
    val product: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)