package com.example.cargo.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cargo.R
import com.example.cargo.databinding.QueryDetailFragmentBinding
import com.example.cargo.recycle.detailfragmentRecycle.DetailAdaptor
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QueryDetailFragment : Fragment(R.layout.query_detail_fragment) {
    private lateinit var binding: QueryDetailFragmentBinding
    private val args: QueryDetailFragmentArgs by navArgs()
    private lateinit var detailAdaptor: DetailAdaptor
    private val popExitAnim by lazy {
        AnimationUtils.loadAnimation(activity, R.anim.pop_up_anim)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QueryDetailFragmentBinding.bind(view)
        binding.selectedRecycle.setOnLongClickListener {
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(null, dragShadowBuilder, it, 0)
            it.visibility = View.INVISIBLE
            true
        }
        binding.selectedRecycle.apply {
            setHasFixedSize(true)
            detailAdaptor = DetailAdaptor(requireActivity()) {
                this.startAnimation(popExitAnim)
                popExitAnim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationEnd(animation: Animation?) {
                        findNavController().popBackStack()
                    }
                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
            adapter = detailAdaptor
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                findNavController().popBackStack()
            }

        }).attachToRecyclerView(binding.selectedRecycle)

        detailAdaptor.submitList(listOf(args.query))
    }

}