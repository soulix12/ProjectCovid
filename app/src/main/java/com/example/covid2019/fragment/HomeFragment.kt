package com.example.covid2019.fragment

import ai.api.AIServiceContext
import ai.api.AIServiceContextBuilder
import ai.api.android.AIConfiguration
import ai.api.android.AIDataService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.covid2019.R
import com.example.covid2019.RequestTask
import com.example.covid2019.databinding.FragmentHomeBinding
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback
import java.util.*
import java.util.logging.Logger


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val USER = 10001
    private val BOT = 10002

    private val uuid = UUID.randomUUID().toString()

    // Android client
    private var aiRequest: AIRequest? = null
    private var aiDataService: AIDataService? = null
    private var customAIServiceContext: AIServiceContext? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        binding.chatScrollView.post { binding.chatScrollView.fullScroll(ScrollView.FOCUS_DOWN) }

        binding.sendBtn.setOnClickListener {sendMessage(it)}

        binding.queryEditText.setOnKeyListener(View.OnKeyListener { view: View?, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                        sendMessage(binding.sendBtn)
                        return@OnKeyListener true
                    }
                    else -> {
                    }
                }
            }
            false
        })
        // Android client
        initChatbot()

        return binding.root
    }

    private fun initChatbot() {
        val config = AIConfiguration(
            "6e93549d4613426eaaf21c11c8821ef8",
            ai.api.AIConfiguration.SupportedLanguages.English,
            AIConfiguration.RecognitionEngine.System
        )
        aiDataService = context?.let { AIDataService(it, config) }
        customAIServiceContext =
            AIServiceContextBuilder.buildFromSessionId(uuid) // helps to create new session whenever app restarts
        aiRequest = AIRequest()
    }

    private fun sendMessage(view: View) {
        val msg: String = binding.queryEditText.text.toString()
        if (msg.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(context, "Please enter your query!", Toast.LENGTH_LONG).show()
        } else {
            showTextView(msg, USER)
            binding.queryEditText.setText("")
            // Android client
            aiRequest?.setQuery(msg)
            val requestTask =
                RequestTask(this, aiDataService, customAIServiceContext)
            requestTask.execute(aiRequest)
            // Java V2
//            QueryInput queryInput = QueryInput.newBuilder().setText(TextInput.newBuilder().setText(msg).setLanguageCode("en-US")).build();
//            new RequestJavaV2Task(MainActivity.this, session, sessionsClient, queryInput).execute();
        }
    }

    fun getUserLayout(): RelativeLayout {
        val inflater = LayoutInflater.from(context)
        return inflater.inflate(R.layout.user_msg_layout, null) as RelativeLayout
    }

    fun getBotLayout(): RelativeLayout {
        val inflater = LayoutInflater.from(context)
        return inflater.inflate(R.layout.bot_msg_layout, null) as RelativeLayout
    }

    private fun showTextView(message: String, type: Int) {
        val layout: RelativeLayout
        when (type) {
            USER -> layout = getUserLayout()
            BOT -> layout = getBotLayout()
            else -> layout = getBotLayout()
        }
        layout.isFocusableInTouchMode = true
        binding.chatLayout.addView(layout) // move focus to text view to automatically make it scroll up if softfocus
        val tv = layout.findViewById<TextView>(R.id.chatMsg)
        tv.text = message
        layout.requestFocus()
        binding.queryEditText.requestFocus() // change focus back to edit text to continue typing
    }


    fun callback(aiResponse: AIResponse?) {
        if (aiResponse != null) { // process aiResponse here
            Log.d("HomeFragment", aiResponse.result.toString())
            val botReply = aiResponse.result.fulfillment.speech
            showTextView(botReply, BOT)
        } else {
            showTextView("There was some communication issue. Please Try again!", BOT)
        }
    }

    private fun openTableau() {
        val customTabsIntent = CustomTabsIntent.Builder()
            .addDefaultShareMenuItem()
            .setToolbarColor(this.resources.getColor(R.color.colorPrimary))
            .setShowTitle(true)
            .build()

        CustomTabsHelper.addKeepAliveExtra(context, customTabsIntent.intent)
        CustomTabsHelper.openCustomTab(
            context, customTabsIntent,
            Uri.parse("https://public.tableau.com/profile/selina.yang7401#!/vizhome/COVID-19FinancialImpacts/Dashboard"),
            WebViewFallback()
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
