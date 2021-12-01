package com.bijesh.challengeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bijesh.challengeapplication.adapters.MainAdapter
import com.bijesh.challengeapplication.adapters.SelectedAdapter
import com.bijesh.challengeapplication.adapters.listeners.RecyclerItemSelectedListener
import com.bijesh.challengeapplication.models.DogBreeds
import com.bijesh.challengeapplication.models.desmodels.SelectedBreedItem
import com.bijesh.challengeapplication.repos.MainRepository
import com.bijesh.challengeapplication.services.RetrofitService
import com.bijesh.challengeapplication.viewmodel.AppViewModelFactory
import com.bijesh.challengeapplication.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, RecyclerItemSelectedListener {

    private val retrofitService : RetrofitService = RetrofitService.getInstance()
    lateinit var viewModel: MainActivityViewModel
    private val TAG = "MainActivity"
    private val adapter = MainAdapter(this)
    private lateinit var lstDogBreeds: List<DogBreeds>
    private lateinit var  recyclerView: RecyclerView
    private var initSpinner = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinnerSelectBreed)
//        spinner.setSelection(Adapter.NO_SELECTION,true)
        spinner.onItemSelectedListener = this

        viewModel = ViewModelProvider(this,AppViewModelFactory(MainRepository(retrofitService))).get(MainActivityViewModel::class.java)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewBreeds)

        recyclerView.adapter = adapter
        var gridLayoutManager = GridLayoutManager(applicationContext,3)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = gridLayoutManager

        recyclerView.setHasFixedSize(true)
        viewModel.dogList.observe(this,{
                if(spinner != null) {
                    val adapter =
                        ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, it.map { r -> r.name })
                    spinner.adapter = adapter
                }
            adapter.setDogBreedList(it)
            lstDogBreeds = it
        })
        viewModel.errorMessage.observe(this,{
            Log.e(TAG,"Error while fetching data...")
        })


        viewModel.selectedBreedList.observe(this,{
            Log.d(TAG,"size is ${it.size}")
            initRecyclerView(it)
        })

        viewModel.getAllDogBreeds()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        Toast.makeText(this,parent!!.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show()
        if(!initSpinner){
            initSpinner = true
            return
        }
        viewModel.getSelectedDogBreedsById(lstDogBreeds.get(position).id)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onRecyclerViewItemSelected(dogBreed: DogBreeds) {
        Toast.makeText(this,dogBreed.name,Toast.LENGTH_LONG).show()
//        viewModel.getSelectedDogBreeds(dogBreed.name)
//        viewModel.getSelectedDogBreedsById(dogBreed.id)
    }

    private fun initRecyclerView(listOfSelected: List<SelectedBreedItem>){
        var selectedAdapter = SelectedAdapter()
        recyclerView.adapter = selectedAdapter
        var gridLayoutManager = GridLayoutManager(applicationContext,3)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.setHasFixedSize(true)
        selectedAdapter.setSelectedDogBreedList(listOfSelected)
    }

}