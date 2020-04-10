package com.example.covid2019.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.databinding.DataBindingUtil

import com.example.covid2019.R
import com.example.covid2019.databinding.FragmentLoginBinding
import com.example.covid2019.databinding.FragmentTableauBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TableauFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TableauFragment : Fragment() {

    private lateinit var binding: FragmentTableauBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tableau, container, false)

        if (Build.VERSION.SDK_INT >= 19) {
            binding.tableauWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        binding.tableauWebView.loadUrl("https://public.tableau.com/profile/selina.yang7401#!/vizhome/COVID-19FinancialImpacts/Dashboard")
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TableauFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TableauFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
