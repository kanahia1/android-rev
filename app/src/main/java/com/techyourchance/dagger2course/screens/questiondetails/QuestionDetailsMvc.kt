package com.techyourchance.dagger2course.screens.questiondetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar

class QuestionDetailsMvc (
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) {
    private var toolbar: MyToolbar
    private var swipeRefresh: SwipeRefreshLayout
    var txtQuestionBody: TextView
    var rootView: View = layoutInflater.inflate(R.layout.layout_question_details, parent, false)
    private var listeners = HashSet<Listener>()
    
    init {
        txtQuestionBody = findViewById(R.id.txt_question_body)

        // init toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigateUpListener {
            for (listener in listeners){
                listener.backPressed()
            }
        }

        // init pull-down-to-refresh (used as a progress indicator)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.isEnabled = false
    }

    private fun <T : View> findViewById(id : Int) : T {
        return rootView.findViewById<T>(id)
    }
        
    fun attachListener(listener: Listener){
        listeners.add(listener)
    }
    
    fun deattachListener(listener: Listener){
        listeners.remove(listener)
    }
    
    interface Listener{
        fun backPressed()
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }
}