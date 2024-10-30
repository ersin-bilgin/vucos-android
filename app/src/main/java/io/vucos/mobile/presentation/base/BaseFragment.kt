package io.vucos.mobile.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import io.vucos.mobile.R
import io.vucos.mobile.util.LifecycleRecyclerView
import io.vucos.mobile.util.LifecycleViewPager
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction0

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutId: Int) : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    abstract val viewModel: BaseViewModel?

    private var loadingView: View? = null
    private var isLoadingAdded = false

    protected var mediator: TabLayoutMediator? = null
    private var snackbar: Snackbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoadingObserver()
    }

    private fun setupLoadingObserver() {
        viewModel?.isLoading?.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                safeShowLoading()
            } else {
                safeHideLoading()
            }
        }
    }

    private fun showLoading() {
        if (loadingView == null) {
            loadingView = createLoadingView()
        }
        (binding.root as? ViewGroup)?.let { rootView ->
            if (!isLoadingAdded) {
                rootView.addView(loadingView)
                isLoadingAdded = true
            }
            loadingView?.visibility = View.VISIBLE
        }
    }

    private fun createLoadingView(): View {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_loading, null).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    private fun hideLoading() {
        loadingView?.visibility = View.GONE
        (binding.root as? ViewGroup)?.let { rootView ->
            if (isLoadingAdded) {
                rootView.removeView(loadingView)
                isLoadingAdded = false
            }
        }
    }

    protected fun manageRecyclerViewAdapterLifecycle(vararg recyclerViews: RecyclerView) {
        recyclerViews.forEach { recyclerView ->
            viewLifecycleOwner.lifecycle.addObserver(LifecycleRecyclerView(recyclerView))
        }
    }

    protected fun manageViewPagerAdapterLifecycle(viewPager: ViewPager2, autoSlide: Boolean) {
        viewLifecycleOwner.lifecycle.addObserver(LifecycleViewPager(viewPager, autoSlide))
    }

    protected fun collectFlows(collectors: List<KSuspendFunction0<Unit>>) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectors.forEach { collector ->
                    launch {
                        collector()
                    }
                }
            }
        }
    }

    protected fun showSnackbar(
        message: String,
        indefinite: Boolean = true,
        actionText: String? = null,
        anchor: Boolean = false,
        action: (() -> Unit)? = null
    ) {
        val view = activity?.window?.decorView?.rootView
        val length = if (indefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
        val snackbar = view?.let { Snackbar.make(it, message, length) }

        if (action != null) snackbar?.setAction(actionText) { action() }

        this.snackbar = snackbar
        this.snackbar?.show()
    }

    override fun onPause() {
        super.onPause()
        snackbar?.dismiss()
    }
    private fun safeHideLoading() {
        view?.post {
            if (isLoadingAdded) {
                hideLoading()
            }
        }
    }
    private fun safeShowLoading() {
        view?.post {
            if (!isLoadingAdded) {
                showLoading()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        safeHideLoading()
        loadingView = null
        mediator?.detach()
        mediator = null
        _binding = null
    }
}