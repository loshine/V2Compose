package io.github.loshine.v2compose.ui.vm

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.loshine.v2compose.data.dto.TopicItem
import io.github.loshine.v2compose.data.dto.TopicTab
import io.github.loshine.v2compose.data.repository.V2exRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabTopicsViewModel @Inject constructor(
    private val v2exRepository: V2exRepository
) : ViewModel() {

    fun refresh(
        tab: TopicTab,
        list: MutableStateFlow<List<TopicItem>>,
        refreshing: MutableStateFlow<Boolean>
    ) {
        viewModelScope.launch {
            viewModelScope.launch {
                refreshing.emit(true)
                runCatching {
                    v2exRepository.getTabTopics(tab.value)
                }.onSuccess {
                    list.emit(it)
                }.onFailure {
                    it.printStackTrace()
                }
                refreshing.emit(false)
            }
        }
    }
}

class TabTopicsKeyedViewModel(private val provider: TabTopicsViewModel) : ViewModel() {

    private val _list = MutableStateFlow(listOf<TopicItem>())
    val list: StateFlow<List<TopicItem>> by this::_list

    private val _refreshing = MutableStateFlow(false)
    val refreshing: StateFlow<Boolean> by this::_refreshing

    fun refresh(tab: TopicTab) = provider.refresh(tab, _list, _refreshing)
}

@Composable
fun keyedTabTopicsViewModel(key: String): TabTopicsKeyedViewModel {
    val provider = hiltViewModel<TabTopicsViewModel>()
    return viewModel(
        key = key,
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TabTopicsKeyedViewModel(provider) as T
            }
        }
    )
}