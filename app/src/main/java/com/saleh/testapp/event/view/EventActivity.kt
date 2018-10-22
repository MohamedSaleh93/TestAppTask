package com.saleh.testapp.event.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.saleh.testapp.R
import com.saleh.testapp.event.adapter.EventAdapter
import com.saleh.testapp.event.viewmodel.EventViewModel
import com.saleh.testapp.model.EventModel
import kotlinx.android.synthetic.main.activity_account_details.*
import android.support.v7.widget.RecyclerView
import android.graphics.drawable.Drawable



class EventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)
        val model = ViewModelProviders.of(this).get(EventViewModel::class.java)
        model.getEventDetails().observe(this, Observer { t ->  setDataToUi(t)})
        model.getEventsFromDatabase()
        progressView.visibility = View.VISIBLE
    }

    private fun setDataToUi(eventData: List<EventModel>?) {
        if (eventData != null) {
            val adapter = EventAdapter(this, eventData)
            eventsRecyclerView.layoutManager = LinearLayoutManager(this)
            eventsRecyclerView.addItemDecoration(dividerItemDecoration(this))
            eventsRecyclerView.adapter = adapter
        }
        progressView.visibility = View.GONE
    }

    internal inner class dividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
        private val mDivider: Drawable

        init {
            mDivider = context.resources.getDrawable(R.drawable.line_divider)
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)

                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = child.bottom + params.bottomMargin
                val bottom = top + mDivider.intrinsicHeight

                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }
}
