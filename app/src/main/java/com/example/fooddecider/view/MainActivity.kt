package com.example.fooddecider.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.fooddecider.R
import com.example.fooddecider.controller.FoodController
import com.example.fooddecider.model.Food
import com.example.fooddecider.utils.Preference
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var foodController: FoodController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        food_editText.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
               return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        addFood_button.setOnClickListener {
            val foodToAdd = Food(food_editText.text.toString())

            val mylamda = Thread{
                if (foodToAdd.name != "") {
                    foodController.addFoodToArrayList(foodToAdd)
                }
            }
            mylamda.start()
            food_editText.text.clear()
            hideKeyboard()
        }

        deleteFood_button.setOnClickListener {
            val foodToDelete = Food(food_textView.text.toString())

            val mylamda = Thread{
                foodController.deleteFoodFromArrayList(foodToDelete)
            }
            mylamda.start()
            food_textView.text = "What eat today?"
        }

        randomFood_button.setOnClickListener {
            randomFood()
        }

        foodController  = FoodController(this)

        val mylamda = Thread{
            foodController.loadListOfFood()
        }
        mylamda.start()
    }

    private fun randomFood(){
        if (Preference.listFoodData.isEmpty().not()){
            food_textView.text = Preference.listFoodData[Random.nextInt(Preference.listFoodData.size)].toString().toUpperCase()
        }
    }

    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}