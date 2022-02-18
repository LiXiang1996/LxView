package com.example.baselib.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import com.example.baselib.utils.StartActivityUtils.createIntent
import com.example.baselib.utils.StartActivityUtils.internalStartActivity
import com.example.baselib.utils.StartActivityUtils.internalStartActivityForResult


/**
 * app内部跳转方法
 */

inline fun <reified T : Activity> Context.startAct(vararg params: Pair<String, Any?>) = internalStartActivity(this, T::class.java, params)

fun Context.startAct(jumpUri: String, vararg params: Pair<String, Any?>) = internalStartActivity(this, jumpUri, params)

fun Context.startActAddFlags(jumpUri: String, flags: Int, vararg params: Pair<String, Any?>) = internalStartActivity(this, jumpUri, flags, params)

inline fun <reified T : Activity> Activity.startActForResult(requestCode: Int, vararg params: Pair<String, Any?>) = internalStartActivityForResult(this, T::class.java, requestCode, params)

fun Activity.startActForResult(jumpUri: String, requestCode: Int, vararg params: Pair<String, Any?>) = internalStartActivityForResult(this, jumpUri, requestCode, params)

inline fun <reified T : Activity> Fragment.startAct(vararg params: Pair<String, Any?>) {
    internalStartActivity(requireActivity(), T::class.java, params)
}

fun Fragment.startAct(jumpUri: String, vararg params: Pair<String, Any?>) {
    internalStartActivity(requireActivity(), jumpUri, params)
}

inline fun <reified T : Activity> Fragment.startActForResult(requestCode: Int, vararg params: Pair<String, Any?>) {
    startActivityForResult(createIntent(requireActivity(), T::class.java, params), requestCode)
}

fun Fragment.startActForResult(jumpUri: String, requestCode: Int, vararg params: Pair<String, Any?>) {
    startActivityForResult(createIntent(requireActivity(), jumpUri, params), requestCode)
}

inline fun <reified T : Any> Fragment.intentFor(vararg params: Pair<String, Any?>): Intent = createIntent(requireActivity(), T::class.java, params)

fun Fragment.intentFor(jumpUri: String, vararg params: Pair<String, Any?>): Intent = createIntent(requireActivity(), jumpUri, params)

inline fun <reified T : Any> Context.intentFor(vararg params: Pair<String, Any?>): Intent = createIntent(this, T::class.java, params)

fun Context.intentFor(jumpUri: String, vararg params: Pair<String, Any?>): Intent = createIntent(this, jumpUri, params)

inline fun <reified T : Activity> Context.generateContract(): ActivityResultContract<List<Any?>, Intent?> {
    return object : ActivityResultContract<List<Any?>, Intent?>() {

        override fun createIntent(context: Context, input: List<Any?>?): Intent {
            val array = input?.mapIndexed { index, item -> "data$index" to item }?.toTypedArray() ?: emptyArray()
            return createIntent(this@generateContract, T::class.java, array)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Intent? {
            if (intent != null && resultCode == Activity.RESULT_OK) {
                return intent
            }
            return null
        }
    }
}

inline fun <reified T : Activity, M> ComponentActivity.registerForActivityResult(crossinline action: (M?) -> Unit): ActivityResultLauncher<List<Any?>> {

    return registerForActivityResult(generateContract<T>()) {
        if (it != null) {
            action(it.parseIntent())
        }
    }
}

inline fun <reified T : Activity, M1, M2> ComponentActivity.registerForActivityResult2(crossinline action: (TwoModel<M1?, M2?>) -> Unit): ActivityResultLauncher<List<Any?>> {

    return registerForActivityResult(generateContract<T>()) {
        if (it != null) {
            action(it.parseIntent2())
        }
    }
}

fun Activity.finishWithResult(vararg list: Any?) {
    StartActivityUtils.finishWithResult(this, list.mapIndexed { index, item -> "data$index" to item }.toTypedArray())
}

inline fun <reified T : Activity> Context.startWith(vararg list: Any?) {
    val array = list.mapIndexed { index, item -> "data$index" to item }.toTypedArray()
    internalStartActivity(this, T::class.java, array)
}

fun <M> Intent.parseIntent(): M? {
    return extras?.get("data0") as? M
}

fun <M1, M2> Intent.parseIntent2(): TwoModel<M1?, M2?> {
    return TwoModel(extras?.get("data0") as? M1, extras?.get("data1") as? M2)
}

fun <M1, M2, M3> Intent.parseIntent3(): ThreeModel<M1?, M2?, M3?> {
    return ThreeModel(
        extras?.get("data0") as? M1,
        extras?.get("data1") as? M2,
        extras?.get("data2") as? M3,
    )
}

fun <M1, M2, M3, M4> Intent.parseIntent4(): FourModel<M1?, M2?, M3?, M4?> {
    return FourModel(
        extras?.get("data0") as? M1,
        extras?.get("data1") as? M2,
        extras?.get("data2") as? M3,
        extras?.get("data3") as? M4,
    )
}

data class TwoModel<M1, M2>(
    val model1: M1,
    val model2: M2,
)

data class ThreeModel<M1, M2, M3>(
    val model1: M1,
    val model2: M2,
    val model3: M3,
)

data class FourModel<M1, M2, M3, M4>(
    val model1: M1,
    val model2: M2,
    val model3: M3,
    val model4: M4,
)