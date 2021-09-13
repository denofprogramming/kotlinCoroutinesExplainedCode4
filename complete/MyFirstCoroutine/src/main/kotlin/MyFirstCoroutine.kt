import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext


fun main() = runBlocking {

    logContext("runBlocking")

    launch {
        logContext("launch")
    }

    logMessage("Hello")
    logMessage("World")


    //Thread.sleep(5000)

}


fun logMessage(msg: String) {
    println("Running on: [${Thread.currentThread().name}] | $msg")
}


fun CoroutineScope.logContext(id: String) {
    coroutineContext.logDetails(id)
}


fun CoroutineContext.logDetails(id: String) {
    sequenceOf(
        Job,
        ContinuationInterceptor,
        CoroutineExceptionHandler,
        CoroutineName
    )
        .mapNotNull { key -> this[key] }
        .forEach { logMessage("id: $id ${it.key} = ${it}") }
}