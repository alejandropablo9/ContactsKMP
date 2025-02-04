package mx.tejate.contactskmp.core

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseMVIViewModel<S : Base.State, A : Base.Action, SE : Base.SideEffect>(
    private val initialValue: S
) : StateScreenModel<S>(initialValue) {

    val actions: Channel<A> = Channel(Channel.BUFFERED)

    protected val sideEffectState: MutableStateFlow<SingleEffect<SE>?> = MutableStateFlow(null)
    public val sideEffect: StateFlow<SingleEffect<SE>?> = sideEffectState.asStateFlow()

    init {
        this.handleAction()
    }

    abstract fun handleAction()

    fun onAction(action: A) {
        execute { actions.send(action) }
    }

    protected fun executeAction(
        action: suspend (A) -> Unit
    ) = execute {
        actions.consumeAsFlow().collect { action(it) }
    }

    protected fun execute(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        action: suspend () -> Unit
    ) = screenModelScope.launch(dispatcher) { action() }

    protected fun updateUi(
        handler: S.() -> S
    ) = execute {
        this.mutableState.update { handler(it) }
    }

    protected fun updateSideEffect(
        effect: SE
    ) = execute {
        this.sideEffectState.update { SingleEffect(effect) }
    }

}