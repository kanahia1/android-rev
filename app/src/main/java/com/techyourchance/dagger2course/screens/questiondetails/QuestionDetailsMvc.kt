package com.techyourchance.dagger2course.screens.questiondetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.mvc.BaseViewMVC
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar

class QuestionDetailsMvc (
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMVC<QuestionDetailsMvc.Listener>(layoutInflater, parent, R.layout.layout_question_details) {
    private var toolbar: MyToolbar
    private var swipeRefresh: SwipeRefreshLayout
    var txtQuestionBody: TextView

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