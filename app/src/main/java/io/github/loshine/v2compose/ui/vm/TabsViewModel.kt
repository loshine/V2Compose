package io.github.loshine.v2compose.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.loshine.v2compose.data.bean.HtmlTopic
import io.github.loshine.v2compose.data.bean.Topic
import io.github.loshine.v2compose.data.repository.V2exRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabsViewModel
@Inject constructor(private val v2exRepository: V2exRepository) : ViewModel() {

    private val _list = MutableStateFlow(listOf<HtmlTopic>())
    val list: StateFlow<List<HtmlTopic>> by this::_list

    private val _refreshing = MutableStateFlow(false)
    val refreshing: StateFlow<Boolean> by this::_refreshing

    fun refresh() {
        viewModelScope.launch {
            viewModelScope.launch {
                _refreshing.emit(true)
                runCatching {
                    v2exRepository.getTopicsByTab("all")
                }.onSuccess {
                    _list.emit(it)
                }
                _refreshing.emit(false)
            }
        }
    }
}