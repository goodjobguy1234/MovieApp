package com.example.movieproject


import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.movieproject.data.data.entity.MovieData
import com.example.movieproject.presentation.DataViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlin.math.abs


class DetailFragment : BaseFragment() {
    override var layoutRes = R.layout.fragment_detail
    lateinit var toolbar: Toolbar
    lateinit var appBar: AppBarLayout
    lateinit var tvDescription: TextView
    lateinit var ivPic: AppCompatImageView
    var movieData: MovieData? = null
    lateinit var collapsingToolbar: CollapsingToolbarLayout
    private var backCallback: OnBackPressedCallback? = null

    private val model by activityViewModels<DataViewModel> {
        Injection.viewModelFactory
    }

    override fun setViewId(view: View) {
        with(view) {
            toolbar = findViewById(R.id.toolbar2)
            appBar = findViewById(R.id.appBar2)
            tvDescription = findViewById(R.id.tvDescription)
            ivPic = findViewById(R.id.ivHeader)
            collapsingToolbar = findViewById(R.id.toolbar_layout)
        }

        (requireActivity() as? MainActivity)?.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        collapsingToolbar.title = "Description"
    }

    override fun observeView() {

    }

    override fun initView() {
        movieData = model.selectedData.value

        movieData?.apply {
            tvDescription.text = description

            Glide.with(requireContext()).asBitmap()
                .load(url).into(object : BitmapImageViewTarget(ivPic) {
                    override fun setResource(resource: Bitmap?) {
                        super.setResource(resource)
                        getPalette(resource)
                    }
                })
        }

    }

    private fun getPalette(bitmap: Bitmap?) {

        bitmap?.let {
            Palette.from(it).generate { palette ->

                palette?.let {
                    val mutedColor = palette.getMutedColor(R.attr.colorPrimary)
                    collapsingToolbar.setContentScrimColor(mutedColor)
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        handle when click back btn
        backCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            remove()
            model.repository.selectedData.value = null
            findNavController().popBackStack()

        }
    }

    companion object {
        fun newInstance() = DetailFragment()
    }


}