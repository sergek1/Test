package com.example.delivery.presentation.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delivery.R
import com.example.delivery.databinding.FragmentMenuBinding
import com.example.delivery.domain.model.Category
import com.example.delivery.domain.model.Meal
import com.example.delivery.domain.model.Result
import com.example.delivery.presentation.menu.adapter.BannersAdapter
import com.example.delivery.presentation.menu.adapter.CategoriesAdapter
import com.example.delivery.presentation.menu.adapter.MealsByCategoryAdapter
import com.example.delivery.presentation.menu.viewmodel.CategoriesViewModel
import com.example.delivery.presentation.menu.viewmodel.MealsByCategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var bannersAdapter: BannersAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var mealsAdapter: MealsByCategoryAdapter
    private val categoriesViewModel by viewModel<CategoriesViewModel>()
    private val mealsViewModel by viewModel<MealsByCategoryViewModel>()
    private val imageList: MutableList<Int> = ArrayList()
    private var isFirst: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        setBanners()
        setCategoryAdapter()
        observeCategories()
        setMealsAdapter()
        observeMeals()
    }

    private fun observeMeals() {
        mealsViewModel.mealsResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    binding.layoutMeals.visibility = View.VISIBLE

                    val meals = result.data
                    if (meals != null) {
                        mealsAdapter.updateData(meals)
                    }
                }

                is Result.Failure -> {
                    Log.d("Error", result.message.toString())
                }

                is Result.Loading -> {
//                    loadingMealsUI()
                }
            }
        }

    }

    private fun setMealsAdapter() {
        mealsAdapter = MealsByCategoryAdapter()
        binding.rvMeals.apply {
            adapter = mealsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setCategoryAdapter() {
        categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.onItemClickListener { teg ->
            mealsViewModel.getMealsByCategory(teg)
        }

        binding.rvCategories.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

    }

    private fun observeCategories() {
        categoriesViewModel.categories.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    result.data?.let { data -> successCategoriesUI(data) }
                }

                is Result.Failure -> {
                    failureCategoriesUI(result.message)
                }

                is Result.Loading -> {
                    loadingCategoriesUI()
                }
            }
        }
    }

    private fun loadingCategoriesUI() {
        binding.layoutCategories.visibility = View.GONE
        binding.progressBarCategories.visibility = View.VISIBLE
        binding.tvErrorCategories.visibility = View.GONE
    }

    private fun failureCategoriesUI(message: String?) {
        binding.layoutCategories.visibility = View.GONE
        binding.progressBarCategories.visibility = View.GONE
        binding.tvErrorCategories.visibility = View.VISIBLE
        binding.tvErrorCategories.text = message
    }

    private fun successCategoriesUI(categories: List<Category>) {
        binding.layoutCategories.visibility = View.VISIBLE
        categoriesAdapter.updateData(categories)
        if (isFirst) {
            categories[0].categoryName?.let { mealsViewModel.getMealsByCategory(it) }
            isFirst = false
        }
        binding.progressBarCategories.visibility = View.GONE
        binding.tvErrorCategories.visibility = View.GONE

    }

    private fun setBanners() {
        imageList.add(R.drawable.banner2)
        imageList.add(R.drawable.banner2)
        imageList.add(R.drawable.banner3)
        bannersAdapter = BannersAdapter()
        binding.rvBanners.apply {
            adapter = bannersAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        bannersAdapter.updateData(imageList)

    }

    private fun loadingMealsUI() {
        binding.layoutMeals.visibility = View.GONE
        binding.progressBarMeals.visibility = View.VISIBLE
        binding.tvErrorMeals.visibility = View.GONE
    }

    private fun failureMealsUI(message: String?) {
        binding.layoutMeals.visibility = View.GONE
        binding.progressBarMeals.visibility = View.GONE
        binding.tvErrorMeals.visibility = View.VISIBLE
        binding.tvErrorMeals.text = message
    }

    private fun successMealsUI(meals: List<Meal>) {
        mealsAdapter.updateData(meals)
        binding.layoutMeals.visibility = View.VISIBLE
        binding.progressBarCategories.visibility = View.GONE
        binding.tvErrorCategories.visibility = View.GONE
    }
}