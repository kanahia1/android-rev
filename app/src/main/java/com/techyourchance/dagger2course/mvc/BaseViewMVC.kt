package com.techyourchance.dagger2course.mvc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.techyourchance.dagger2course.R

open class BaseViewMVC <LISTENER_TYPE>(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    @LayoutRes layoutQuestionDetails: Int
) {

    protected var listeners = HashSet<LISTENER_TYPE>()

    protected var rootView: View = layoutInflater.inflate(layoutQuestionDetails, parent, false)

    protected var context: Context = rootView.context

    fun <T : View> findViewById(id : Int) : T {
        return rootView.findViewById<T>(id)
    }

    fun attachListener(listener: LISTENER_TYPE){
        listeners.add(listener)
    }

    fun deattachListener(listener: LISTENER_TYPE){
        listeners.remove(listener)
    }

}