package br.edu.up.rgm34508741.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.edu.up.rgm34508741.dados.Item
import br.edu.up.rgm34508741.dados.ItemRepositorio
import java.text.NumberFormat


class ItemAdicionarViewModel(private val itemRepositorio: ItemRepositorio) : ViewModel() {


    var itemUiStato by mutableStateOf(ItemUiStato())
        private set


    fun atualizarUiStato(itemDetails: ItemDetalhe) {
        itemUiStato =
            ItemUiStato(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }


    suspend fun salvarItem() {
        if (validateInput()) {
            itemRepositorio.novoItemRe(itemUiStato.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: ItemDetalhe = itemUiStato.itemDetails): Boolean {
        return with(uiState) {
            nome.isNotBlank() && preco.isNotBlank() && quantidade.isNotBlank()
        }
    }
}


data class ItemUiStato(
    val itemDetails: ItemDetalhe = ItemDetalhe(),
    val isEntryValid: Boolean = false
)

data class ItemDetalhe(
    val id: Int = 0,
    val nome: String = "",
    val preco: String = "",
    val quantidade: String = "",
)

/**
 * Extension function to convert [ItemUiState] to [Item]. If the value of [ItemDetails.preco] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [ItemUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun ItemDetalhe.toItem(): Item = Item(
    id = id,
    nome = nome,
    preco = preco.toDoubleOrNull() ?: 0.0,
    quantidade = quantidade.toIntOrNull() ?: 0
)

fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(preco)
}

/**
 * Extension function to convert [Item] to [ItemUiState]
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiStato = ItemUiStato(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun Item.toItemDetails(): ItemDetalhe = ItemDetalhe(
    id = id,
    nome = nome,
    preco = preco.toString(),
    quantidade = quantidade.toString()
)
